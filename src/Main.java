import view.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            /*
            UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
            for (UIManager.LookAndFeelInfo look : looks) {
                System.out.println(look.getClassName());
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel("io.github.vincenzopalazzo:material-ui-swing:1.1.2");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            */
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
