package controller;

import model.GroupModel;
import model.UserModel;
import view.LoginView;
import view.RegisterView;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {
    private final UserModel userModel;
    private final LoginView loginView;
    private final RegisterView registerView;
    private final GroupController groupController;
    private final GroupModel groupModel;

    private final UserView userView;
    public UserController(){
        this.userModel = new UserModel();
        this.loginView = new LoginView();
        this.registerView = new RegisterView();
        this.userView = new UserView();
        this.groupModel = new GroupModel();
        this.groupController = new GroupController(groupModel);
        loginView.setLoginBtnListener(new LoginBtnInLoginViewListeners());
        loginView.setRegisterBtnListener(new RegisterBtnInLoginViewListeners());
        registerView.setRegisterBtnListener(new RegisterBtnInLoginViewListeners());

    }

    public boolean register(){
        return userModel.register();
    }
    public UserModel login() throws SQLException {
        return userModel.login();
    }

    public void showLogin(){
        this.loginView.showWindow();
    }

    class LoginBtnInLoginViewListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Login".equals(e.getActionCommand())){
                try {
                    userModel.setUsername(loginView.getUsername());
                    userModel.setPassword(loginView.getPassword());
                    UserModel loginResult = userModel.login();
                    if (loginResult != null){
                        loginView.dispose();
                        userView.showWindow();
                    }
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private ArrayList<GroupModel> getUsersGroup(UserModel userModel){
        return null;
    }

    class RegisterBtnInLoginViewListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("Register".equals(e.getActionCommand())){
                loginView.dispose();
                registerView.showWindow();
            }
        }
    }
}
