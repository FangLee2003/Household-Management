package controller;

import javax.swing.*;
import java.sql.*;

public class LoginController {
    private Connection con;
    private ResultSet rs;
    private Statement sm;
    private Boolean check;

    public Boolean checkLogin(String user, String pass) {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();

            String sql = "SELECT * FROM Manager WHERE MAccount LIKE N\'" + user + "\' and MPass LIKE N\'" + pass + "\'";
            System.out.println(sql);

            rs = sm.executeQuery(sql);

            check = rs.next();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
}