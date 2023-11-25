package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int id;

    public UserModel(int id, String username, String firstname, String lastName, String email, String password) throws NoSuchAlgorithmException {
        this.id = id;
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

    public UserModel(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = PasswordHandler.encrypt(password.trim());
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel login() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        String query = "Select id, username, pass, fname, lname, email from Users where username = ? and pass = ? ";
        PreparedStatement loginPreparedStatement = con.prepareStatement(query);
        loginPreparedStatement.setString(1, this.username);
        loginPreparedStatement.setString(2, this.password);
        ResultSet userResult = loginPreparedStatement.executeQuery();

        if (userResult.next()){
            this.id = userResult.getInt("id");
            this.firstName = userResult.getString("fname");
            this.lastName = userResult.getString("lname");
            this.email = userResult.getString("email");
            return this;
        }
        else {
            System.out.println("Login failed");
        }
        return null;
    }

    public boolean register(){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("At least one empty field exist");
            return false;
        }

        else if (!matcher.matches()) {
            System.out.println("Unvaried Email address");
            return false;
        }
        else {

            try {
                int resultState = getResultState();

                if (resultState == 1) {
                    System.out.println("Successful Registration");
                    return true;
                } else {
                    System.out.println("Failure Registration");
                    return false;
                }
            }
            catch (SQLIntegrityConstraintViolationException ee){
                System.out.println("This Username/email are registered");
                return false;
            }
            catch (SQLException ex) {
                System.out.println("Please try again or call support");
                return false;
            }
        }

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
