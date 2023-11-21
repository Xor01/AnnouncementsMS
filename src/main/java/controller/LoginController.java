package controller;
import model.UserModel;

import java.sql.*;
public class LoginController {
    private final UserModel userModel;
    public LoginController(UserModel userModel){
        this.userModel = userModel;
    }
    public ResultSet login() throws SQLException {
        return userModel.login();
    }
}
