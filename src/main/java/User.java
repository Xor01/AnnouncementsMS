import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;

import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;
import  java.util.Timer;
public class User extends JFrame {

    private final int id;
    private final Connection con;
    private final JTabbedPane groupsTabs;
    private final TextArea typingArea;
    /**
     * to keep track of groups ids if each tab
     */
    private final ArrayList<Integer> group_ids;

    /**
     * parametrized constructor you should pass a database connection and the user information
     * @param con database connection
     * @param id user is
     * @param username user's username
     */
    public User(Connection con, int id, String username) {
        this.con = con;
        this.id = id;
        setTitle("Welcome - " + username);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        group_ids = new ArrayList<>();
        /*  This is the beginning of the jTabbedPane  */
        groupsTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        addGroups();
        add(groupsTabs);
        /*  This is the end of the jTabbedPane  */

        JPanel typingPanel = new JPanel(new BorderLayout());
        JButton sendBtn = new JButton("Send");
        getRootPane().setDefaultButton(sendBtn);
        sendBtn.addActionListener(e -> sendMessage());
        typingArea = new TextArea();
        typingPanel.setPreferredSize(new Dimension(200, 150));
        typingPanel.add(typingArea, BorderLayout.CENTER);
        typingPanel.add(sendBtn, BorderLayout.EAST);
        add(typingPanel, BorderLayout.SOUTH);
        updateMessages updateMessages = new updateMessages();
        updateMessages.start();

        JMenuBar menuBar = new JMenuBar();
        JMenu view = new JMenu("View");
        JMenu group = new JMenu("Groups");

        JMenuItem createGroup = new JMenuItem("Add Group");
        JMenuItem refresh = new JMenuItem("Refresh");

        group.add(createGroup);
        menuBar.add(group);

        menuBar.add(new JSeparator());

        view.add(refresh);
        menuBar.add(view);

        setJMenuBar(menuBar);

        createGroup.addActionListener(e -> createGroup());
        refresh.addActionListener(e -> callUpdateForeachGroup());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (con != null){
                        con.close();
                    }
                }
                catch(SQLException ignore){}
                super.windowClosing(e);
            }
        });
    }

    /**
     * This method will load the group a specific user is registered in
     */
    private void addGroups(){
        try{
            String query = "SELECT agroups.*, groupmembers.group_id, groupmembers.isAdmin " +
                    "FROM agroups " +
                    "JOIN groupmembers ON agroups.id = groupmembers.group_id " +
                    "WHERE groupmembers.member_id = ?";

            PreparedStatement selectGroupsPreparedStatement = con.prepareStatement(query);
            selectGroupsPreparedStatement.setInt(1, this.id);
            ResultSet result = selectGroupsPreparedStatement.executeQuery();

            while (result.next()){
                boolean isGroupAdmin = result.getBoolean("isAdmin");
                JTextPane announcementsPanel = new JTextPane();
                announcementsPanel.setFocusable(false);
                JPanel groupDetails = new JPanel(new BorderLayout());
                groupDetails.add(new JScrollPane(announcementsPanel), BorderLayout.CENTER);
                JButton addUser = new JButton(new FlatSVGIcon("addUser.svg"));
                addUser.setToolTipText("Add users to " + result.getString("gName"));
                /*  creating the three panels to have right and left panels both added to the main panel*/
                JPanel mainTopPanel = new JPanel(new BorderLayout());
                JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                // adding left and right panels to the main panel
                mainTopPanel.add(leftTopPanel, BorderLayout.WEST);
                mainTopPanel.add(rightTopPanel, BorderLayout.EAST);

                JButton updateBtn = new JButton(new FlatSVGIcon("refresh.svg"));
                updateBtn.setToolTipText("Refresh");
                leftTopPanel.add(updateBtn);
                updateBtn.addActionListener(e -> {
                    groupsTabs.removeAll();
                    addGroups();
                });
                leftTopPanel.add(addUser);
                if (!isGroupAdmin){
                    addUser.setEnabled(false);
                    addUser.setToolTipText("Only admins can add users");
                }
                groupDetails.add(mainTopPanel, BorderLayout.NORTH);
                announcementsPanel.setEditable(false);
                groupsTabs.addTab(result.getString("gName"), groupDetails);
                int group_id = result.getInt("group_id");
                group_ids.add(group_id);
                groupDetails.putClientProperty("group_id", group_id);
                groupDetails.putClientProperty("isGroupAdmin", isGroupAdmin);
                // this will create the group member list
                if (isGroupAdmin){
                    ListUsersOfAGroup listUsersOfAGroup = new ListUsersOfAGroup(con, group_id, this);
                    listUsersOfAGroup.doWork();
                    JButton listMembers = new JButton(new FlatSVGIcon("group.svg"));
                    listMembers.setToolTipText("List the members of group");
                    listMembers.addActionListener(e -> listUsersOfAGroup.showPopup());
                    rightTopPanel.add(listMembers);
                }
                addUser.addActionListener(new AddUserToGroup(this, this.con, group_id));
                loadMessages(group_id, announcementsPanel);
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "An error happened");
        }
    }

    /**
     * this method will call loadMessage to fetch new changes from the database
     */
    private void callUpdateForeachGroup(){
        Component [] listOfComponents = groupsTabs.getComponents();
        for (Component comp: listOfComponents){
            if (comp instanceof JPanel){
                for (Component panelComp:  ((JPanel) comp).getComponents()){
                   if (panelComp instanceof JScrollPane){
                       try {
                           JScrollPane scrollPane = (JScrollPane) panelComp;
                           JTextPane textPane = (JTextPane) scrollPane.getViewport().getView();
                           if (((JPanel) comp).getClientProperty("group_id") != null){
                               int group_id = (int) ((JPanel) comp).getClientProperty("group_id");
                               loadMessages(group_id, textPane);
                           }
                       }
                       catch (Exception e){
                           JOptionPane.showMessageDialog(getContentPane(),
                                   "Try again or call support",
                                   "Error", JOptionPane.ERROR_MESSAGE);
                       }
                   }
                }
            }
        }
    }

    /**
     * check if this the user is a group admin
     * @param selectedComp current tab component
     * @return true of admin or false otherwise
     */
    private boolean isAdminInGroup(Component selectedComp){
        if (selectedComp != null){
            if (selectedComp instanceof JPanel){
                try{
                    JPanel tabPanel = (JPanel) selectedComp;
                    return (boolean )tabPanel.getClientProperty("isGroupAdmin");
                }
                catch (Exception e){
                    return false;
                }
            }
        }
        return false;
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
            ResultSet result = con.prepareStatement(String.format(
                    "SELECT  distinct * FROM  messages, users where messages.group_id = %d and users.id = messages.sender_id Order by created_at", group_id
                    )
            ).executeQuery();
            if (panel.getClientProperty("last_id") != null){
                if (getLastMessageId(group_id) <= (int) panel.getClientProperty("last_id")){
                    return;
                }
            }
            panel.setText("");
            while (result.next()){
                String message = String.format(
                        "Sender: %s%nMessage: %s%n created at: %s%n",
                result.getString("username"), result.getString("content"), result.getString("created_at"));
                insertAnnouncement(
                        document,
                        result.getString("username"),
                        result.getString("content"),
                        result.getString("created_at"),
                        senderStyle, timestampStyle);
                panel.add(new JTextField(message, panel.getWidth()));
                panel.putClientProperty("last_id", result.getInt("id"));
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "An error happened");
            System.out.println(e.getMessage());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns the last message id of a group
     * @param group_id the id of a group to get the last message id from
     * @return returns last message id or -1 if non is found
     */
    private int getLastMessageId (int group_id) {

        try{
            String query =
                    "Select id from messages where group_id= ? order by id desc limit 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, group_id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                return result.getInt("id");
            }
        }
        catch (SQLException sqlException){
            JOptionPane.showMessageDialog(getContentPane(),
                    "Try again or call support",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        return -1;
    }

    /**
     * insert a styled text to the JTextPane contains (content, sender name, and timestamp)
     * @param document styles document object
     * @param sender username of the sender
     * @param content the message to be showed
     * @param timestamp the time of the message
     * @param senderStyle the sender text style
     * @param timestampStyle the timestamp style
     * @throws BadLocationException thrown when a location that does not exist
     */
    private void insertAnnouncement(StyledDocument document, String sender,
            String content, String timestamp, Style senderStyle, Style timestampStyle) throws BadLocationException {
        document.insertString(document.getLength(), sender + ": ", senderStyle);
        document.insertString(document.getLength(), content + "\n", null);
        document.insertString(document.getLength(), "  " + timestamp + "\n", timestampStyle);
    }

    /**
     *  This method sends messages to the correct group.
     *  It knows the group id from the getSelectedIndex method
     */
    private void sendMessage(){
        String message = typingArea.getText();
        boolean isThisGroupAdmin = isAdminInGroup(groupsTabs.getSelectedComponent());
        if (isThisGroupAdmin && !message.isEmpty()){
            try {
                String query ="INSERT INTO messages (sender_id, group_id, content, created_at) Values" +
                        "(?, ?, ?, current_timestamp)";

                PreparedStatement sendPreparedStatement = con.prepareStatement(query);
                sendPreparedStatement.setInt(1, id);
                sendPreparedStatement.setInt(2, group_ids.get(groupsTabs.getSelectedIndex()));
                sendPreparedStatement.setString(3, message);
                int r = sendPreparedStatement.executeUpdate();
                if (r == 1){
                    JOptionPane.showMessageDialog(this, "Successfully send the message");
                    callUpdateForeachGroup();
                    typingArea.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(this, "failed to send the message");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        else if (!isThisGroupAdmin) {
            JOptionPane.showMessageDialog(this, "Only Group Admin Can Send");
        }
        else {
            JOptionPane.showMessageDialog(this, "No Empty Announcements are Allowed to Send");
        }
    }

    /**
     * This method creates a group by prompting to user the group name
     * Group creator will always be admin
     */
    private void createGroup(){
        String name = JOptionPane.showInputDialog(this, "Enter Group Name", "Add a New Group", JOptionPane.INFORMATION_MESSAGE);
        try {
            if (!name.isEmpty()) {
                String agroupsQuery = "insert into agroups (gName) values (?)";
                PreparedStatement agroupsPreparedStatement = con.prepareStatement(agroupsQuery);
                agroupsPreparedStatement.setString(1, name);
                int agroupsResult = agroupsPreparedStatement.executeUpdate();

                if (agroupsResult == 1){
                    String getIdQuery = "SELECT LAST_INSERT_ID()";
                    ResultSet getIdResult =
                            con.prepareStatement(getIdQuery).executeQuery();
                    if (getIdResult.next()){
                        int id = getIdResult.getInt("LAST_INSERT_ID()");
                        String groupmembersQuery = String.format(
                                "insert into groupmembers (group_id, member_Id, isAdmin) values ('%d', '%d', true)",
                                id, this.id
                        );
                        int groupmembersQueryResult = con.prepareStatement(groupmembersQuery).executeUpdate();
                        if (groupmembersQueryResult == 1) {
                            JOptionPane.showMessageDialog(this,
                                    "Group has been created",
                                    "Group creation success",
                                    JOptionPane.INFORMATION_MESSAGE);
                                    groupsTabs.removeAll();
                                    addGroups();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this,
                                    "Call administrator",
                                    "Error in adding you to group",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Error", "We could not create your group", JOptionPane.ERROR_MESSAGE);
        }
        catch (NullPointerException ignored){}
    }

    /**
     * This method extends thread, and it creates a timer to run callUpdateForEachGroups
     * Each 0.5 seconds
     */
    private class updateMessages extends Thread{

        @Override
        public void run(){
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    callUpdateForeachGroup();
                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 500);
        }
    }
}
