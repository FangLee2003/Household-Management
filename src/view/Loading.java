package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static view.Graphic.createImageIcon;
import static view.Graphic.getLoadImageIcon;

public class Loading extends JFrame {
    private JLabel lbLoading = new JLabel(getLoadImageIcon("../assets/loading3.gif"));
    private JLabel lbTitle = new JLabel("Household Management", JLabel.CENTER);
    private JLabel lbAuthor = new JLabel("Fang Lee", JLabel.CENTER);
    private JLabel lbPercent = new JLabel("0%", JLabel.CENTER);

    private JPanel pnInfo = new JPanel();
    private JPanel pnMain = new JPanel();

    public Loading() {
        try {
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.NONE);

            lbTitle.setFont(new Font("Montserrat", Font.BOLD, 30));
            lbTitle.setForeground(new Color(52, 152, 219));

            lbAuthor.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
            lbAuthor.setForeground(new Color(52, 152, 219));


            lbPercent.setFont(new Font("Montserrat", Font.PLAIN, 20));
            lbPercent.setForeground(new Color(52, 152, 219));

            pnInfo.setLayout(new GridLayout(2, 1));
            pnInfo.add(lbTitle);
            pnInfo.add(lbAuthor);

            pnMain.setLayout(new BorderLayout());
            pnMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            pnMain.add(pnInfo, BorderLayout.NORTH);
            pnMain.add(lbLoading, BorderLayout.CENTER);
            pnMain.add(lbPercent, BorderLayout.SOUTH);

            add(pnMain);

            pack();

            setAlwaysOnTop(true);
            setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./assets/logo.png")));
            setSize(600, 300);
            setLocationRelativeTo(null);
            setVisible(true);

            loading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loading() {
        try {
            for (int i = 0; i <= 100; i++) {
                lbPercent.setText(i + "%");
                Thread.sleep(20);
            }
            dispose();
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
