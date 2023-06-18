import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

/**
 * Main Class
 */
public class Main {
    public static void main(String[] args) {
        FlatMacLightLaf.registerCustomDefaultsSource("style");
        FlatMacLightLaf.setup();
        SwingUtilities.invokeLater(() -> {
            Login l = new Login();
            l.setSize(500, 500);
            l.setVisible(true);
            l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }
}