import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class User extends JFrame {
    private String firstname;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public User() {
        super("Welcome - User");
        Rectangle device = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(device.getSize());
        /*  This is the beginning of the jTabbedPane  */
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

        JPanel test = new JPanel();
        test.add(new JButton("Test Button"));

        tabbedPane.addTab("Group 1", test);
        tabbedPane.addTab("OOP 2", new JPanel());
        tabbedPane.addTab("Some Random Group", new JPanel());
        tabbedPane.addTab("G4", new JPanel());
        add(tabbedPane);
        /*  This is the end of the jTabbedPane  */

        JPanel typingPanel = new JPanel(new BorderLayout());
        JButton sendBtn = new JButton("Send");
        TextArea type = new TextArea();

        typingPanel.add(type, BorderLayout.CENTER);
        typingPanel.add(sendBtn, BorderLayout.EAST);
        add(typingPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        User u =  new User();
        u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        u.setVisible(true);
    }
}
