package controller;

import javax.swing.*;
import java.sql.*;
import java.util.*;

import model.hometown;

public class ChartController {
    private Connection con;
    private Statement smP;
    private Statement smH;
    private Statement smB;

    private int people_num;
    private int household_num;

    private double household_percent;
    private double dependent_percent;

    private List<hometown> hometowns = new ArrayList<hometown>();

    public ChartController() {
        try {
            con = ConnectionSQL.getConnection();
            smP = con.createStatement();
            smH = con.createStatement();
            smB = con.createStatement();

            loadPieData();
            loadBarData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadPieData() {
        try {
            ResultSet rsP = smP.executeQuery("SELECT COUNT(*) FROM People");
            if (rsP.next()) {
                people_num = rsP.getInt(1) > 0 ? rsP.getInt(1) : 1;
            }
            rsP.close();

            ResultSet rsH = smH.executeQuery("SELECT COUNT(*) FROM Household");
            if (rsH.next()) {
                household_num = rsH.getInt(1);
            }
            rsH.close();

            System.out.println("People Number: " + people_num);
            System.out.println("Householder Number: " + household_num);

            household_percent = household_num * 100 / people_num;
            dependent_percent = 100 - household_percent;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadBarData() {
        try {
            hometowns.clear();
            ResultSet rsB = smB.executeQuery("SELECT CAST(PHometown AS NVARCHAR(100)) Hometown , COUNT(CAST(PHometown AS NVARCHAR(100))) AS Population FROM People GROUP BY CAST(PHometown AS NVARCHAR(100))");
            while (rsB.next()) {
                hometowns.add(new hometown(rsB.getString(1),
                        rsB.getLong(2)
                ));
            }
            rsB.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getHouseholdPercent() {
        loadPieData();
        return household_percent;
    }

    public double getDependentPercent() {
        loadPieData();
        return dependent_percent;
    }
    public List<hometown> getHometown() {
        loadBarData();
        return hometowns;
    }
}

