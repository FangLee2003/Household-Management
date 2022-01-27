package controller;

import javax.swing.*;
import java.sql.*;

public class LoginController {
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pr;
    private Boolean check;

    public Boolean checkLogin(String user, String pass) {
        try {
            con = ConnectionSQL.getConnection();

            String sql = "SELECT * FROM Manager where MAccount = ? and MPass = ?";
            pr = con.prepareStatement(sql);

            pr.setString(1, user);
            pr.setString(2, pass);

            System.out.println(sql);
            rs = pr.executeQuery();

            check = rs.next();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
}