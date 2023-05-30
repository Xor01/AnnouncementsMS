import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class ListUsersOfAGroup {

    private final Connection con;
    private final JPanel usersListPanel;
    private final JFrame frame;
    private final int groupId;
    public ListUsersOfAGroup(Connection con, int groupId, JFrame frame){
        this.con = con;
        this.groupId = groupId;
        this.usersListPanel = new JPanel();
        this.frame = frame;
    }

    public void doWork() {
        try  {
            String query = String.format(
                    "SELECT * from users, groupmembers where group_id = %d and users.id = groupmembers.member_id",
                    groupId
            );
            ResultSet resultSet = con.prepareStatement(query).executeQuery();

            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("----- Members List of the Group -----");
            listModel.addElement(String.format("%-15s %-15s %-15s %-15s%n", "First Name", "Last Name", "Username", "email"));
            JList<String> usernamesList = new JList<>(listModel);
            usernamesList.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
            while (resultSet.next()){
                listModel.addElement(String.format(
                        "%-15s %-15s %-15s %-15s%n",
                        resultSet.getString("fname"),
                        resultSet.getString("lname"),
                        resultSet.getString("username"),
                        resultSet.getString("email")
                ));
            }
            this.usersListPanel.add(usernamesList);
        }
        catch (SQLException sqlException){
            JOptionPane.showMessageDialog(frame, "Some errors happened");
        }
    }

    JPanel returnPanel(){
        return this.usersListPanel;
    }
}
