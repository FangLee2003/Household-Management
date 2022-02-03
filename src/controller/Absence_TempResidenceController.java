package controller;

import model.absence_tempresidence;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class Absence_TempResidenceController {
    private Connection con;
    private Statement sm;
    private ResultSet rs;

    private String sql;

    private List<absence_tempresidence> list = new ArrayList<absence_tempresidence>();

    private boolean check = true;

    public Absence_TempResidenceController() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<absence_tempresidence> getAbsence_TempResidence() {
        try {
            list.clear();

            sql = "SELECT * FROM Absence_TempResidence";
            System.out.println(sql);
            rs = sm.executeQuery(sql);

            while (rs.next()) {
                list.add(new absence_tempresidence(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    public void updateAbsence_TempResidence(String title, String tid, String tname, String tiden, String tdate, String tabsence, String ttemp, String treason) {
        try{
            if (title.equals("Add Form")) {
                sql = "INSERT INTO Absence_TempResidence VALUES (N\'"
                        + tname + "\', "
                        + tiden + ", N\'"
                        + tdate + "\', N\'"
                        + tabsence + "\', N\'"
                        + ttemp + "\', N\'"
                        + treason + "\')";

            } else {
                sql = "UPDATE Absence_TempResidence SET"
                        + " TName = N\'" + tname
                        + "\', TIdentity = " + tiden
                        + ", TDate = N\'" + tdate
                        + "\', AbsenceLocation = N\'" + tabsence
                        + "\', TempResidenceLocation = N\'" + ttemp
                        + "\', TReason = N\'" + treason
                        + "\' WHERE TID = " + String.valueOf(tid);
            }
            System.out.println(sql);
            sm.executeUpdate(sql);
            check = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
            check = false;
        }
    }
    public void deleteHousehold(Object id) {
        try {
            String sql = "DELETE FROM Absence_TempResidence WHERE TID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean validateAbsence_TempResidence() {
        return check;
    }
}

