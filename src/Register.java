import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame {

    private JLabel loginLabel, usernameLabel, passwordLabel, firstnameLabel, lastNameLabel, emailLabel;

    private JTextField usernameField, firstnameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;


    public Register() {
        setLayout(new GridBagLayout());
        setTitle("Welcome - Register");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        loginLabel = new JLabel("Welcome Create your --OurApplicationName-- Account");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setVerticalAlignment(SwingConstants.CENTER);

        firstnameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        emailLabel = new JLabel("Email Address: ");

        firstnameField = new JTextField(20);
        lastNameField = new JTextField(20);
        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginBtn = new JButton("Have an account ? Login instead");
        registerBtn = new JButton("Register");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
//        login label
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        add(loginLabel, gbc);


//        firstname label
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(firstnameLabel, gbc);

//        username field
        gbc.gridy = 1;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(firstnameField, gbc);

//        lastname label
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(lastNameLabel, gbc);


//        lastname field
        gbc.gridy = 2;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(lastNameField, gbc);


//        username label
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(usernameLabel, gbc);

//        username field
        gbc.gridy = 3;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(usernameField, gbc);

//        email label
        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(emailLabel, gbc);

//        email field
        gbc.gridy = 4;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(emailField, gbc);


        //        password label
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(passwordLabel, gbc);

//        password field
        gbc.gridy = 5;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(passwordField, gbc);

//        register button
        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(registerBtn, gbc);
        getRootPane().setDefaultButton(registerBtn);

//        login button
        gbc.gridy = 6;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(loginBtn, gbc);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = firstnameField.getText().trim();
                String lastname = lastNameField.getText().trim();
                String username = usernameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "All fields are mandatory!!",
                            "Empty Fields",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    try {
                        Connection con = DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
                        String query = String.format(
                                "Insert into Users (fname, lname, username, pass) values ('%s', '%s', '%s', '%s'); ",
                                firstname,lastname, username, password
                        );
                        Statement s = con.createStatement();
                        int resultState = s.executeUpdate(query);

                        if (resultState == 1) {
                            loginLabel.setText("Your Account has been created !!");
                        } else {
                            loginLabel.setText("An Error exist you");
                        }
                    }
                    catch (SQLIntegrityConstraintViolationException ee){
                        JOptionPane.showMessageDialog(getContentPane(),
                                "This Username/email are registered",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


    }

}