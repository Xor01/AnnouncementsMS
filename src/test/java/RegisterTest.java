import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    @DisplayName("Registering using correct information")
    @Test public void registerCorrectInformation() {
        Register register = new Register();
        UserRecord userRecord = new UserRecord("username123",
                "String firstName",
                " String lastName",
                " String password",
                "email@testmail.com");
        boolean registerValue = false;
        try {
            registerValue = register.performRegistration(userRecord);
        } catch (SQLException e) {
            assertThrows(SQLException.class, () -> register.performRegistration(userRecord));
        }
        // todo: delete the record added for testing
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