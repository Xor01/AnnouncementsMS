import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends JFrame {

    private final int id;
    private String firstname;
    private String lastName;
    private String username;
    private String email;
    private boolean isAdmin;
    private final Connection con;
    private final JTabbedPane groupsTabs;
    private final TextArea typingArea;
    private JButton sendBtn;
    /**
     * to keep track of groups ids if each tab
     */
    private final ArrayList<Integer> group_ids;

    /**
     * parametrized constructor you should pass a connection and the user information
     * @param con connection
     * @param id user is
     * @param firstname user firstname
     * @param lastName user last name
     * @param username user's username
     * @param email email address
     * @param isAdmin boolean value to represent if a use is admin or not
     */
    public User(Connection con, int id, String firstname, String lastName, String username, String email, boolean isAdmin) {
        this.con = con;
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        setTitle("Welcome - " + username);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        group_ids = new ArrayList<>();
        /*  This is the beginning of the jTabbedPane  */
        groupsTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

        addGroups();
        add(groupsTabs);
        /*  This is the end of the jTabbedPane  */

        JPanel typingPanel = new JPanel(new BorderLayout());
        sendBtn = new JButton("Send");
        getRootPane().setDefaultButton(sendBtn);
        sendBtn.addActionListener(e -> sendMessage());
        if (!isAdmin){
            sendBtn.setEnabled(false);
            sendBtn.setToolTipText("Only admin can send");
        }
        typingArea = new TextArea();

        typingPanel.add(typingArea, BorderLayout.CENTER);
        typingPanel.add(sendBtn, BorderLayout.EAST);
        add(typingPanel, BorderLayout.SOUTH);

    }

    /**
     * This method will load the group a specific user is registered in
     */
    private void addGroups(){
        try{
            ResultSet re = con.prepareStatement(
                    String.format(
                            "SELECT  * " +
                                    "FROM  agroups, groupmembers" +
                                    " where groupmembers.member_id = %d " +
                                    "and agroups.id = groupmembers.group_id", this.id
                    )
            ).executeQuery();

            while (re.next()){
                JTextPane panel = new JTextPane();
                panel.setEditable(false);
                groupsTabs.addTab(re.getString("gName"), panel);
                int group_id = re.getInt("group_id");
                group_ids.add(group_id);
                loadMessages(group_id, panel);
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "An error happened");
        }
    }

    /**
     * this method will load each message for each group
     */
    public void loadMessages(int group_id, JTextPane panel) {
        StyledDocument document = panel.getStyledDocument();

        // Create a style for the sender name
        Style senderStyle = panel.addStyle("SenderStyle", null);
        StyleConstants.setForeground(senderStyle, Color.RED);
        StyleConstants.setBold(senderStyle, true);

        // Create a style for the created at timestamp
        Style timestampStyle = panel.addStyle("TimestampStyle", null);
        StyleConstants.setForeground(timestampStyle, Color.GRAY);
        StyleConstants.setItalic(timestampStyle, true);
        try{
            ResultSet re = con.prepareStatement(String.format(
                    "SELECT  distinct * FROM  messages, users where messages.group_id = %d and users.id = messages.sender_id", group_id
                    )
            ).executeQuery();

            while (re.next()){
                String message = String.format(
                        "Sender: %s%nMessage: %s%n created at: %s%n",
                re.getString("username"), re.getString("content"), re.getString("created_at"));
                insertAnnouncement(
                        document,
                        re.getString("username"),
                        re.getString("content"),
                        re.getString("created_at"),
                        senderStyle, timestampStyle);
                panel.add(new JTextField(message, panel.getWidth()));
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "An error happened");
            System.out.println(e.getMessage());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertAnnouncement(StyledDocument document, String sender,
            String content, String timestamp, Style senderStyle, Style timestampStyle) throws BadLocationException {
        document.insertString(document.getLength(), sender + ": ", senderStyle);
        document.insertString(document.getLength(), content + "\n", null);
        document.insertString(document.getLength(), "  " + timestamp + "\n", timestampStyle);
    }

    private void sendMessage(){
        String message = typingArea.getText();
        if (isAdmin){
            try {
                String query = String.format(
                        "INSERT INTO messages (sender_id, group_id, content, created_at) Values" +
                        "(%d, %d, '%s', current_timestamp)",
                id,group_ids.get(groupsTabs.getSelectedIndex()), message);
                int r = con.prepareStatement(query).executeUpdate();
                if (r == 1){
                    JOptionPane.showMessageDialog(this, "Successfully send the message");
                    groupsTabs.removeAll();
                    addGroups();
                }
                else {
                    JOptionPane.showMessageDialog(this, "failed to send the message");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
