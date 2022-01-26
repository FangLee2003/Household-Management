package controller;

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
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }
}