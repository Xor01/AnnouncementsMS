import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddUserToGroup implements ActionListener {

    private final JFrame frame;
    private final Connection con;
    private final int group_id;
    public AddUserToGroup(JFrame frame, Connection con, int groupId){
        this.frame = frame;
        this.con = con;
        this.group_id = groupId;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox isAdminCheckBox = new JCheckBox("Is Admin ?");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(isAdminCheckBox);

        String usernameToAdd = JOptionPane.showInputDialog(frame.getContentPane(), panel,"Enter a username to add", JOptionPane.INFORMATION_MESSAGE);
        try {
            if (usernameToAdd != null) {
                usernameToAdd = usernameToAdd.trim();

                if (usernameToAdd.isEmpty()){
                    JOptionPane.showMessageDialog(frame.getContentPane(), "Empty username");
                    return;
                }
                String userIdQuery = "select id from users where username = ?";
                PreparedStatement userIdPreparedStatement = con.prepareStatement(userIdQuery);
                userIdPreparedStatement.setString(1, usernameToAdd);
                ResultSet userIdResult = userIdPreparedStatement.executeQuery();
                try {
                    if (userIdResult.next()) {
                        boolean isAdmin = isAdminCheckBox.isSelected();
                        int userId = userIdResult.getInt("id");
                        String query = "insert into groupmembers (group_id, member_id, isAdmin) values ( ? , ?, ?)";
                        PreparedStatement groupMembersPreparedStatement = con.prepareStatement(query);
                        groupMembersPreparedStatement.setInt(1, group_id);
                        groupMembersPreparedStatement.setInt(2, userId);
                        groupMembersPreparedStatement.setBoolean(3, isAdmin);

                        int r = groupMembersPreparedStatement.executeUpdate();
                        if (r == 1) {
                            JOptionPane.showMessageDialog(frame.getContentPane(), "User has been added");
                        } else
                            JOptionPane.showMessageDialog(frame.getContentPane(), "User has adding failed");
                    }
                }
                catch (SQLIntegrityConstraintViolationException userExist){
                    JOptionPane.showMessageDialog(frame.getContentPane(),
                            "This user is already a member of this group");
                }

                catch (SQLException noId) {
                    JOptionPane.showMessageDialog(frame.getContentPane(), "This user does not exit");
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    boolean addUserToGroup(String usernameToAdd, boolean isAdmin) throws SQLIntegrityConstraintViolationException, SQLException {

        if (usernameToAdd != null) {
            usernameToAdd = usernameToAdd.trim();

            if (usernameToAdd.isEmpty()){
                System.out.println("Empty username");
                return false;
            }
            String userIdQuery = "select id from users where username = ?";
            PreparedStatement userIdPreparedStatement = con.prepareStatement(userIdQuery);
            userIdPreparedStatement.setString(1, usernameToAdd);
            ResultSet userIdResult = userIdPreparedStatement.executeQuery();

            if (userIdResult.next()) {
                int userId = userIdResult.getInt("id");
                String query = "insert into groupmembers (group_id, member_id, isAdmin) values ( ? , ?, ?)";
                PreparedStatement groupMembersPreparedStatement = con.prepareStatement(query);
                groupMembersPreparedStatement.setInt(1, group_id);
                groupMembersPreparedStatement.setInt(2, userId);
                groupMembersPreparedStatement.setBoolean(3, isAdmin);

                int r = groupMembersPreparedStatement.executeUpdate();
                return r == 1;
            }
        }
        return false;
    }
}
