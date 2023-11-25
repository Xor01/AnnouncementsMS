import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class Login extends JFrame {

    private final JLabel loginLabel;

    private final JTextField usernameField;
    private final JPasswordField passwordField;

    /**
     * The Login Class
     */
    public Login () {
        setLayout(new GridBagLayout());
        setTitle("Welcome - Login");
        setSize(500, 500);
        setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("assets/icons/addUser.png");
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                PasswordHandler ps = new PasswordHandler(password);
                Connection con = DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
                String query = "Select id, username, pass from Users where username = ? and pass = ? ";
                PreparedStatement loginPreparedStatement = con.prepareStatement(query);
                loginPreparedStatement.setString(1, username);
                loginPreparedStatement.setString(2, ps.getHashedPassword());
                ResultSet re = loginPreparedStatement.executeQuery();

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

               if (re.next()){
                   loginLabel.setText("You are logged in");
                   dispose();
                   int id = re.getInt("id");
                   SwingUtilities.invokeLater(() -> new User(con, id, username));
               }
               else {
                   loginLabel.setText("Wrong username/password");
               }

            }
            catch(com.mysql.cj.jdbc.exceptions.CommunicationsException el ){
                JOptionPane.showMessageDialog(getContentPane(), "Your Connection with the our server could not be established", "Connection issues", JOptionPane.ERROR_MESSAGE);
            }

            catch (SQLException ex) {
                JOptionPane.showMessageDialog(getContentPane(), "Error: Check your credentials or call support", "Connection issues", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerBtn.addActionListener(e -> {
            Register register = new Register();
            SwingUtilities.invokeLater(register.showWindow());
            dispose();
        });
    }
    public boolean performLogin(String username, String password){
        try {
            username = username.trim();
            password = password.trim();
            PasswordHandler ps = new PasswordHandler(password);
            Connection con = DriverManager.getConnection(DBInfo.getURL(), DBInfo.getUSER(), DBInfo.getPASS());
            String query = "Select id, username, pass from Users where username = ? and pass = ? ";
            PreparedStatement loginPreparedStatement = con.prepareStatement(query);
            loginPreparedStatement.setString(1, username);
            loginPreparedStatement.setString(2, ps.getHashedPassword());
            ResultSet re = loginPreparedStatement.executeQuery();

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

            if (re.next()){
                loginLabel.setText("You are logged in");
                dispose();
                int id = re.getInt("id");
               return true;
            }
            else {
                loginLabel.setText("Wrong username/password");
                return false;
            }

        }
        catch(com.mysql.cj.jdbc.exceptions.CommunicationsException el ){
            JOptionPane.showMessageDialog(getContentPane(), "Your Connection with the our server could not be established", "Connection issues", JOptionPane.ERROR_MESSAGE);
        }

        catch (SQLException ex) {
            JOptionPane.showMessageDialog(getContentPane(), "Error: Check your credentials or call support", "Connection issues", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

}