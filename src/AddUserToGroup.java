import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
                ResultSet userIdResult = con.prepareStatement(
                        String.format("select id from users where username = '%s'",
                                usernameToAdd)).executeQuery();
                if (usernameToAdd.isEmpty()){
                    JOptionPane.showMessageDialog(frame.getContentPane(), "Empty username");
                    return;
                }
                try {
                    while (userIdResult.next()) {
                        boolean isAdmin = isAdminCheckBox.isSelected();
                        int userId = userIdResult.getInt("id");
                        String query = String.format("insert into groupmembers " +
                                "(group_id, member_id, isAdmin) values ( %d , %d, %b)", group_id, userId, isAdmin);
                        int r = con.prepareStatement(query).executeUpdate();
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
}
