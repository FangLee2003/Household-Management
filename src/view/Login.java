package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.LoginController;

public class Login extends JFrame implements ActionListener {
    private JLabel lbUser = new JLabel("Username");
    private JTextField tfUser = new JTextField();

    private JLabel lbPass = new JLabel("Password");
    private JTextField tfPass = new JPasswordField();

    private JButton btLogin = new JButton("Login");

    private JLabel lbError = new JLabel("");

    private JPanel pnMain = new JPanel();

    public Login() {
        try {
            setTitle("Login");

            pnMain.setLayout(new GridLayout(3, 2, 5, 5));
            pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            pnMain.add(lbUser);
            pnMain.add(tfUser);

            pnMain.add(lbPass);
            pnMain.add(tfPass);

            pnMain.add(lbError);

            btLogin.addActionListener(this);
            pnMain.add(btLogin);

            this.setContentPane(pnMain);

            this.setSize(400, 200);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
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
                    //JOptionPane.showMessageDialog(btOk, "Ban da dang nhap thanh cong");
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
