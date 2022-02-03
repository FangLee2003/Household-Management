package controller;

import javax.swing.*;
import java.sql.*;

public class ChartController {
    Connection con;
    Statement smP;
    Statement smH;

    int people_num;
    int household_num;

    double household_percent;
    double dependent_percent;

    public ChartController() {
        try {
            con = ConnectionSQL.getConnection();
            smP = con.createStatement();
            smH = con.createStatement();

            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadData() {
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

    public double getHouseholdPercent() {
        loadData();
        return household_percent;
    }

    public double getDependentPercent() {
        loadData();
        return dependent_percent;
    }
}

