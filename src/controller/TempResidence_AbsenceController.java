package controller;

import model.temp_residence_absence;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class TempResidence_AbsenceController {
    private Connection con;
    private Statement sm;
    private ResultSet rs;

    private String sql;

    private List<temp_residence_absence> list = new ArrayList<temp_residence_absence>();

    public TempResidence_AbsenceController() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<temp_residence_absence> getTempResidence_Absence() {
        try {
            list.clear();

            sql = "SELECT * FROM TempResidence_Absence";
            System.out.println(sql);
            rs = sm.executeQuery(sql);

            while (rs.next()) {
                list.add(new temp_residence_absence(
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

    public void editTempResidence_Absence(String title, String tid, String tname, String tiden, String tdate, String ttemp, String tabsence, String treason) {
        try{
            if (title.equals("Add Form")) {
                sql = "INSERT INTO TempResidence_Absence VALUES (N\'"
                        + tname + "\', "
                        + tiden + ", N\'"
                        + tdate + "\', N\'"
                        + ttemp + "\', N\'"
                        + tabsence + "\', N\'"
                        + treason + "\')";

            } else {
                sql = "UPDATE TempResidence_Absence SET"
                        + " TName = N\'" + tname
                        + "\', TIdentity = " + tiden
                        + ", TDate = N\'" + tdate
                        + "\', TempResidenceLocation = N\'" + ttemp
                        + "\', AbsenceLocation = N\'" + tabsence
                        + "\', TReason = N\'" + treason
                        + "\' WHERE TID = " + String.valueOf(tid);
            }
            System.out.println(sql);
            sm.executeUpdate(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }
    public void deleteHousehold(Object id) {
        try {
            String sql = "DELETE FROM TempResidence_Absence WHERE TID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error" ,JOptionPane.ERROR_MESSAGE);
        }
    }
}

