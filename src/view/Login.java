package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.LoginController;

import static view.Graphic.*;

public class Login extends JFrame implements ActionListener {
    private JLabel lbUser = new JLabel("Username", getNameImageIcon(), JLabel.LEFT);
    private JTextField tfUser = new JTextField();

    private JLabel lbPass = new JLabel("Password", createImageIcon("../assets/pass.png"), JLabel.LEFT);
    private JTextField tfPass = new JPasswordField();

    private JButton btLogin = new JButton("Login", createImageIcon("../assets/login.png"));

    private JLabel lbError = new JLabel("");
    private JLabel lbBackground = new JLabel(getLoadImageIcon("../assets/loginbackground.png"));

    private JPanel pnLogin = new JPanel();

    public Login() {
        try {
            setTitle("Login");

            pnLogin.setLayout(new GridLayout(3, 2, 5, 5));
            pnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            pnLogin.add(lbUser);
            pnLogin.add(tfUser);

            pnLogin.add(lbPass);
            pnLogin.add(tfPass);

            pnLogin.add(lbError);

            btLogin.addActionListener(this);
            pnLogin.add(btLogin);

            add(pnLogin);

            pack();

            setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./assets/logo.png")));
            setSize(400, 200);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String user = tfUser.getText();
        String pass = tfPass.getText();

        if (ae.getActionCommand().equals("Login")) {
            try {
                LoginController lc = new LoginController();
                if (lc.checkLogin(user, pass)) {
                    dispose();
                    new Home();
                }
                lbError.setForeground(Color.RED);
                lbError.setText("Wrong account or password!");
            } catch (Exception e) {
                lbError.setText(String.valueOf(e));
                lbError.setForeground(Color.RED);
                e.printStackTrace();
            }
        }
        if (ae.getActionCommand().equals("Sign up")) {
            System.out.println("This app is made for government, for security reason only government can add/remove management accounts!");
        }
    }
}
