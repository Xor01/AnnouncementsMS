import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class LoginTest {
    @Test
    void LoginCorrectCredentials(){
        Login login = new Login();
        assertNotEquals(false, login.performLogin("admin", "admin"));
    }
    @Test
    void LoginWrongCredentials(){
        Login login = new Login();
        assertFalse(login.performLogin("wrongUsername", "WrongUsername"));
    }

    @Test
    void LoginEmptyUsername(){
        Login login = new Login();
        assertFalse(login.performLogin("", "WrongUsername"));
    }

    @Test
    void LoginEmptyPassword(){
        Login login = new Login();
        assertFalse(login.performLogin("wrongUsername", ""));
    }

    @Test
    void LoginEmptyCredentials(){
        Login login = new Login();
        assertFalse(login.performLogin("", ""));
    }
}