package model;

import controller.LoginController;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserModel {
    private final String username;
    private String firstName;
    private String lastName;
    private String email;
    private final String password;
    private int id;

    public UserModel(String username, String firstname, String lastName, String email, String password) throws NoSuchAlgorithmException {
        this.username = username.trim();
        this.firstName = firstname.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
        this.password = PasswordHandler.encrypt(password.trim());
    }

    public UserModel(String username, String password) throws NoSuchAlgorithmException {
        this.username = username.trim();
        this.password = PasswordHandler.encrypt(password.trim());
    }

    public String getUsername(){
        return this.username;
    }


    public ResultSet login() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "Select id, username, pass from Users where username = ? and pass = ? ";
        PreparedStatement loginPreparedStatement = con.prepareStatement(query);
        loginPreparedStatement.setString(1, username);
        loginPreparedStatement.setString(2, password);
        ResultSet userResult = loginPreparedStatement.executeQuery();

        if (userResult.next()){
            return userResult;
        }
        else {
            System.out.println("Login failed");
        }
        return null;
    }

    public ResultSet register(){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("Atleast one empty field exist");
        }

        else if (!matcher.matches()) {
            System.out.println("Unvaried Email address");
        }
        else {

            try {
                int resultState = getResultState();

                if (resultState == 1) {
                    System.out.println("Successful Registration");
                    return new LoginController(this).login();
                } else {
                    System.out.println("Failure Registration");
                    return null;
                }
            }
            catch (SQLIntegrityConstraintViolationException ee){
                System.out.println("This Username/email are registered");
                return null;
            }
            catch (SQLException ex) {
                System.out.println("Please try again or call support");
                return null;
            }
        }

        return null;
    }

    private int getResultState() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "Insert into Users (fname, lname, username, pass, email) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, email);

        return preparedStatement.executeUpdate();
    }


}
