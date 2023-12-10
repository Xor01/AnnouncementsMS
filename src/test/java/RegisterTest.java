import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    @DisplayName("Registering using correct information")
    @Test public void registerCorrectInformation() throws SQLException {
        Register register = new Register();
        UserRecord userRecord = new UserRecord("username123",
                "firstName",
                "lastName",
                "password",
                "email@testmail.com");
        boolean registerValue = false;
        Connection con = null;
        Statement statement = null;
        try {
            registerValue = register.performRegistration(userRecord);

            if (registerValue){
                con = ConnectionManager.getConnection();
                statement = con.createStatement();
                int deleteResult = statement.executeUpdate("delete from users where username = 'username123'");
                assertEquals(1, deleteResult);
            }
        } catch (SQLException e) {
            assertThrows(SQLException.class, () -> register.performRegistration(userRecord));
        }
        finally {
            assert con != null;
            con.close();
            assert statement != null;
            statement.close();
        }
        assertTrue(registerValue);
    }

    @DisplayName("Registering using duplicate username")
    @Test public void registerDuplicateUsername(){
        Register register = new Register();
        UserRecord userRecord = new UserRecord("admin", "String firstName"," String lastName"," String password"," StringEmail@gmail.com");
        boolean registerValue = false;
        try {
            registerValue = register.performRegistration(userRecord);
        }
        catch (SQLIntegrityConstraintViolationException e) {
            assertThrows(SQLIntegrityConstraintViolationException.class, () -> register.performRegistration(userRecord));
        }
        catch (SQLException e) {
            assertThrows(SQLException.class, () -> register.performRegistration(userRecord));
        }
        assertFalse(registerValue);
    }
    @DisplayName("Registering using duplicate email")
    @Test public void registerDuplicateEmail(){
        Register register = new Register();
        UserRecord userRecord = new UserRecord("admin", "String firstName"," String lastName"," String password"," adming@example.com");
        boolean registerValue = false;
        try {
            registerValue = register.performRegistration(userRecord);
        } catch (SQLIntegrityConstraintViolationException e) {
            assertThrows(SQLIntegrityConstraintViolationException.class, () -> register.performRegistration(userRecord));
        }
        catch (SQLException e) {
            assertThrows(SQLException.class, () -> register.performRegistration(userRecord));
        }
        assertFalse(registerValue);
    }
}