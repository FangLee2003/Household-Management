package controller;

import model.household;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class HouseholdController {
    private Connection con;
    private Statement sm;
    private ResultSet rs;

    private String sql;

    private List<household> list = new ArrayList<household>();

    private boolean check = true;

    public HouseholdController() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<household> getHousehold() {
        try {
            list.clear();

            sql = "SELECT * FROM Household";
            System.out.println(sql);
            rs = sm.executeQuery(sql);

            while (rs.next()) {
                list.add(new household(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getString(4)
                ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    public void updateHousehold(String title, String hid, String hname, String hiden, String hadd) {
        try {
            if (title.equals("Add Form")) {
                sql = "INSERT INTO Household VALUES (N\'"
                        + hname + "\', "
                        + hiden + ", N\'"
                        + hadd + "\')";
            } else {
                sql = "UPDATE Household SET"
                        + " HouseholderName = N\'" + hname
                        + "\', HouseholderIdentity = " + hiden
                        + ", HAddress = N\'" + hadd
                        + "\' WHERE HID = " + hid;
            }
            System.out.println(sql);
            sm.executeUpdate(sql);
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
            check = false;
        }
    }

    public void deleteHousehold(Object id) {
        try {
            String sql = "DELETE FROM Household WHERE HID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean validateHousehold() {
        return check;
    }
}
