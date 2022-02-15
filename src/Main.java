import view.Loading;

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
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            new Loading();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
