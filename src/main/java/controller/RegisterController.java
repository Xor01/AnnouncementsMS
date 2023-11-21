package controller;

import model.UserModel;
import java.sql.ResultSet;

public class RegisterController {
    private final UserModel userModel;
    public RegisterController(UserModel userModel){
        this.userModel = userModel;
    }

    public ResultSet register(){
        return userModel.register();
    }

}
