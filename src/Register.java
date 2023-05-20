import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

    private JLabel loginLabel, usernameLabel, passwordLabel, firstnameLabel, lastNameLabel, emailLabel;

    private JTextField usernameField, firstnameField, lastNameField, emailField;
    private JPasswordField passwordField;
    private JButton loginBtn, registerBtn;


    public Register() {
        setLayout(new GridBagLayout());
        setTitle("Welcome - Register");


        loginLabel = new JLabel("Welcome Create your --OurApplicationName-- Account");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setVerticalAlignment(SwingConstants.CENTER);

        firstnameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("First Name: ");
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

//        register button
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(registerBtn, gbc);

//        login button
        gbc.gridy = 5;
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
                loginLabel.setText("You are going to signup");
            }
        });

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}