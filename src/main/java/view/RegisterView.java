package view;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame{

    public RegisterView(){
        setLayout(new GridBagLayout());
        setTitle("Welcome - Register");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        JLabel loginLabel = new JLabel("Welcome Create your KFU Announcements Account");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel firstnameLabel = new JLabel("First Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel emailLabel = new JLabel("Email Address: ");

        JTextField firstnameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField usernameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginBtn = new JButton("Have an account ? Login instead");
        JButton registerBtn = new JButton("Register");


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

        loginBtn.addActionListener(e -> {
            dispose();
        });
    }
}
