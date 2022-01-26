package view;

import controller.ConnectionSQL;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Login extends JFrame implements ActionListener {
    private JLabel lbUser = new JLabel("Username");
    private JTextField tfUser = new JTextField();

    private JLabel lbPass = new JLabel("Password");
    private JTextField tfPass = new JPasswordField();

    private JButton btLogin = new JButton("Login");

    private JLabel lbError = new JLabel("");

    private JPanel pnMain = new JPanel();

    private Connection con;
    private PreparedStatement sm;

    ArrayList<ArrayList<String>> managers = new ArrayList<ArrayList<String>>();

    public Login() {
        setTitle("Login");

        load();

        btLogin.addActionListener(this);

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
    }

    public void load() {
        try {
            String user = tfUser.getText();
            String pass = tfPass.getText();

            String sql = "SELECT * FROM Manager";
            System.out.println(sql);

            con = ConnectionSQL.getConnection();
            sm = con.prepareStatement(sql);

            ResultSet rs = sm.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();

            int column_num = rsmt.getColumnCount();

            while (rs.next()) {
                ArrayList<String> manager = new ArrayList<String>();
                for (int i = 5; i <= column_num; i++) {
                    manager.add(rs.getString(i));
                }
                managers.add(manager);
            }
            rs.close();
            for (int i = 0; i < managers.size(); i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.print(managers.get(i).get(j) + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String user = tfUser.getText();
        String pass = tfPass.getText();
        if (ae.getActionCommand().equals("Login")) {
            try {
                for (int i = 0; i < managers.size(); i++) {
                    if (managers.get(i).get(0).equals(user) && managers.get(i).get(1).equals(pass)) {
                        lbError.setForeground(Color.GREEN);
                        lbError.setText("Login successfully!");
                        dispose();
                        //JOptionPane.showMessageDialog(btOk, "Ban da dang nhap thanh cong");
                        new Home();
                    }
                }
                lbError.setForeground(Color.RED);
                lbError.setText("Login failed!");
                
            } catch (Exception e) {
                lbError.setText(String.valueOf(e));
                lbError.setForeground(Color.RED);
                System.out.println(e);
            }
        }
        if (ae.getActionCommand().equals("Sign up")) {
            System.out.println("This app is made for government, for security reason only government can add/remove management accounts!");
        }
    }
}
