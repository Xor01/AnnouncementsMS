import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        FlatMacLightLaf.registerCustomDefaultsSource("style");
        FlatMacLightLaf.setup();
        Login l = new Login();
        l.setSize(500, 500);
        l.setVisible(true);
        l.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}