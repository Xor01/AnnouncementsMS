import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class Login extends JFrame {

    private JLabel loginLabel, usernameLabel, passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton registerBtn;

    public Login () {
        setLayout(new GridBagLayout());
        setTitle("Welcome - Login");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginLabel = new JLabel("Welcome Enter your credentials or click register to signup");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setVerticalAlignment(SwingConstants.CENTER);
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
//        login label
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        add(loginLabel, gbc);

//        username label
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(usernameLabel, gbc);

//        username field
        gbc.gridy = 1;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(usernameField, gbc);

//        password label
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(passwordLabel, gbc);


//        password field
        gbc.gridy = 2;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(passwordField, gbc);


//        register button
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(registerBtn, gbc);

//        login button
        gbc.gridy = 3;
        gbc.gridx = 3;
        gbc.gridwidth = 2;
        add(loginBtn, gbc);
        getRootPane().setDefaultButton(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
                    String query = String.format("Select username, pass from Users where username = '%s'" +
                            " and pass = '%s' ", usernameField.getText(), new String(passwordField.getPassword()));
                    ResultSet statement = con.prepareStatement(query).executeQuery();

                   if (statement.next()){
                       loginLabel.setText("You are logged in");
                   }
                   else {
                       loginLabel.setText("Wrong username/password");
                   }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Error: Check your credentials or call support");
                }
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                dispose();
            }
        });
    }

}