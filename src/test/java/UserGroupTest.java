import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

class UserGroupTest {

    @Test
    public void addNotMemberUserToGroup() throws SQLException {
        Connection con = null;
        ResultSet getIdResult = null;
        Statement statement = null;
        try {
            con = ConnectionManager.getConnection();
            User user = new User(con, 149, "demo");
            user.createGroupTest();
            String getIdQuery = "SELECT LAST_INSERT_ID()";
            getIdResult =
                    con.prepareStatement(getIdQuery).executeQuery();
            if (getIdResult.next()){
                int groupId = getIdResult.getInt("LAST_INSERT_ID()");
                AddUserToGroup addUserToGroup = new AddUserToGroup(user, con, groupId);
                boolean isUserAdded = addUserToGroup.addUserToGroup("demo", false);
                assertTrue(isUserAdded);
                statement = con.createStatement();
                int deleteResult = statement.executeUpdate("delete from agroups where gName = 'demo'");
                assertTrue(deleteResult > 0);
                return;
            }



        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert con != null;
            con.close();
            assert getIdResult != null;
            getIdResult.close();
            assert statement != null;
            statement.close();
        }
        fail();
    }

    @Test
    public void addExistedMemberToGroup() throws SQLException {
        Connection con = null;
        ResultSet getIdResult = null;
        try {
            con = ConnectionManager.getConnection();
            User user = new User(con, 149, "demo");
            user.createGroupTest();

            String getIdQuery = "SELECT LAST_INSERT_ID()";
            getIdResult =
                    con.prepareStatement(getIdQuery).executeQuery();

            if (getIdResult.next()){
                int groupId = getIdResult.getInt("LAST_INSERT_ID()");
                AddUserToGroup addUserToGroup = new AddUserToGroup(user, con, groupId);
                boolean isUserAdded = addUserToGroup.addUserToGroup("demo", false);
                assertTrue(isUserAdded);
                assertThrows(SQLIntegrityConstraintViolationException.class, () -> addUserToGroup.addUserToGroup("demo", false));
                return;
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert con != null;
            con.close();
            assert getIdResult != null;
            getIdResult.close();
        }
        fail();
    }
}