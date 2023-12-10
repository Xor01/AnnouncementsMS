import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
public class GroupTest {

    @Test
    public void createGroup(){
        try {
            Connection con = ConnectionManager.getConnection();
            User user = new User(con,149, "demo");
            boolean groupResult = user.createGroupTest();
            assertTrue(groupResult);
            String getIdQuery = "SELECT LAST_INSERT_ID()";
            ResultSet getIdResult =
                    con.prepareStatement(getIdQuery).executeQuery();

            if (getIdResult.next()) {
                Statement statement = con.createStatement();
                int deleteResult = statement.executeUpdate("delete from agroups where gName = 'demo'");
                assertTrue(deleteResult > 0);
            }
        } catch (SQLException ignored) {
        }
    }

}
