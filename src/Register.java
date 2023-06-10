import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends JFrame {

    private final JLabel loginLabel;

    private final JTextField usernameField;
    private final JTextField firstnameField;
    private final JTextField lastNameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;


    /**
     * The Registration Class
     */
    public Register() {
        setLayout(new GridBagLayout());
        setTitle("Welcome - Register");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        loginLabel = new JLabel("Welcome Create your KFU Announcements Account");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setVerticalAlignment(SwingConstants.CENTER);

        JLabel firstnameLabel = new JLabel("First Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel emailLabel = new JLabel("Email Address: ");

        firstnameField = new JTextField(20);
        lastNameField = new JTextField(20);
        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

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
            SwingUtilities.invokeLater(Login::new);
            dispose();
        });

        registerBtn.addActionListener(e -> {
            String firstname = firstnameField.getText().trim();
            String lastname = lastNameField.getText().trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            PasswordHandler ps = new PasswordHandler(password);
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(getContentPane(),
                        "All fields are mandatory!!",
                        "Empty Fields",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (!matcher.matches()) {
                JOptionPane.showMessageDialog(getContentPane(),
                        "Please Enter a valid email address",
                        "Unvaried Email address",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {

                try {
                    Connection con = DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
                    String query = "Insert into Users (fname, lname, username, pass, email) values (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, firstname);
                    preparedStatement.setString(2, lastname);
                    preparedStatement.setString(3, username);
                    preparedStatement.setString(4, ps.getHashedPassword());
                    preparedStatement.setString(5, email);

                    this.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try {
                                con.close();
                            }
                            catch(SQLException ignore){}
                            super.windowClosing(e);
                        }
                    });
                    int resultState = preparedStatement.executeUpdate();

                    if (resultState == 1) {
                        loginLabel.setText("Your Account has been created !!");
                        JOptionPane.showMessageDialog(getContentPane(),
                                "Your Account has been registered successfully",
                                "Successful Registration", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        loginLabel.setText("An Error exist you");
                        JOptionPane.showMessageDialog(getContentPane(),
                                "An error happened we could not register you",
                                "Failure Registration", JOptionPane.ERROR_MESSAGE);
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
        });


    }

}