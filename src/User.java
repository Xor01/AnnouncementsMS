import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends JFrame {

    private int id;
    private String firstname;
    private String lastName;
    private String username;
    private String email;
    private String password;

    private boolean isAdmin;
    private Connection con;
    private JTabbedPane groupsTabs;

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
        /*  This is the beginning of the jTabbedPane  */
        groupsTabs = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

        addGroups();
        add(groupsTabs);
        /*  This is the end of the jTabbedPane  */

        JPanel typingPanel = new JPanel(new BorderLayout());
        JButton sendBtn = new JButton("Send");
        if (!isAdmin){
            sendBtn.setEnabled(false);
            sendBtn.setToolTipText("Only admin can send");
        }
        TextArea type = new TextArea();

        typingPanel.add(type, BorderLayout.CENTER);
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
                loadMessages(group_id, panel);
            }

        }
        catch (SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "An error happened");
        }
    }

    /**
     * this method will load each message for each group
     */
    public void loadMessages(int group_id, JTextPane panel) {
        // Create a StyledDocument to hold the text and styles
        StyledDocument document = panel.getStyledDocument();

        // Create a style for the sender name
        Style senderStyle = panel.addStyle("SenderStyle", null);
        StyleConstants.setForeground(senderStyle, Color.RED);  // Set the text color
        StyleConstants.setBold(senderStyle, true);  // Set the font weight

        // Create a style for the created at timestamp
        Style timestampStyle = panel.addStyle("TimestampStyle", null);
        StyleConstants.setForeground(timestampStyle, Color.GRAY);  // Set the text color
        StyleConstants.setItalic(timestampStyle, true);  // Set the font style
        try{
            ResultSet re = con.prepareStatement(String.format(
                    "SELECT  distinct * FROM  messages, users where messages.group_id = %d and users.id = messages.sender_id", group_id
                    )

            ).executeQuery();

            while (re.next()){
                String message = String.format(
                        "Sender: %s%nMessage: %s%n created at: %s%n",
                re.getString("username"), re.getString("content"), re.getString("created_at"));
                insertChatMessage(document, re.getString("username"), re.getString("content"), re.getString("created_at"), senderStyle, timestampStyle);
                panel.add(new JTextField(message, panel.getWidth()));
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "An error happened");
            System.out.println(e);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertChatMessage(StyledDocument document, String sender, String content, String timestamp, Style senderStyle, Style timestampStyle) throws BadLocationException {


        document.insertString(document.getLength(), sender + ": ", senderStyle);
        document.insertString(document.getLength(), content + "\n", null);
        document.insertString(document.getLength(), "  " + timestamp + "\n", timestampStyle);
    }
    public static void main(String[] args) {
//        User u =  new User(null);
    }
}
