import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {

    private final JLabel loginLabel;

    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public Login () {
        setLayout(new GridBagLayout());
        setTitle("Welcome - Login");
        setSize(500, 500);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("assets/icons/addUser.png");
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginLabel = new JLabel("Welcome Enter your credentials or click register to create your account");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setVerticalAlignment(SwingConstants.CENTER);
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

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
        loginBtn.addActionListener(e -> {
            try {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                Connection con = DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
                String query = String.format("Select * from Users where username = '%s'" +
                        " and pass = '%s' ", username, password);
                ResultSet re = con.prepareStatement(query).executeQuery();
               if (re.next()){
                   loginLabel.setText("You are logged in");
                   dispose();
                   int id = re.getInt("id");
                   boolean isAdmin = re.getBoolean("isAdmin");
                   new User(con, id, username, isAdmin);
               }
               else {
                   loginLabel.setText("Wrong username/password");
               }

            }
            catch(com.mysql.cj.jdbc.exceptions.CommunicationsException el ){
                JOptionPane.showMessageDialog(getContentPane(), "Your Connection with the our server could not be established", "Connection issues", JOptionPane.ERROR_MESSAGE);
            }

            catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error: Check your credentials or call support");
            }
        });

        registerBtn.addActionListener(e -> {
            new Register();
            dispose();
        });
    }

}