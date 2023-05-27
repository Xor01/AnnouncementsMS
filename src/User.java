import javax.swing.*;
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
                    "SELECT  * FROM  agroups, groupmembers where groupmembers.member_id = '1'", this.id
            ).executeQuery();

            while (re.next()){
                groupsTabs.addTab(re.getString("gName"), new JPanel());
            }

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "An error happened");
        }
    }

    /**
     * this method will load each message for each group
     */
    public void loadMessages(ResultSet group) {

    }
    public static void main(String[] args) {
//        User u =  new User(null);
    }
}
