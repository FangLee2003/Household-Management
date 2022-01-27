package controller;

import java.sql.*;

public class ChartController {
    Connection con;
    Statement smP;
    Statement smH;

    int people_num = 1;
    int household_num = 1;
    double household_percent;
    double dependent_percent;

    public ChartController(){
        try {
            con = ConnectionSQL.getConnection();
            smP = con.createStatement();
            smH = con.createStatement();

            ResultSet rsP = smP.executeQuery("SELECT COUNT(*) FROM People");

            if (rsP.next()) {
                people_num = rsP.getInt(1);
            }
            ResultSet rsH = smH.executeQuery("SELECT COUNT(*) FROM Household");
            if (rsP.next()) {
                household_num = rsP.getInt(1);
            }

            household_percent = household_num * 100 / people_num;
            dependent_percent = 100 - household_percent;
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public double getHouseholdPercent() {
        return household_percent;
    }
    public double getDependentPercent() {
        return dependent_percent;
    }
}
