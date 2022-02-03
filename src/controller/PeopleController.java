package controller;

import model.people;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class PeopleController {
    private Connection con;
    private Statement sm;
    private ResultSet rs;

    private String sql;

    private List<people> list = new ArrayList<people>();

    private boolean check = true;

    public PeopleController() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<people> getPeople() {

        try {
            list.clear();

            sql = "SELECT * FROM People";
            System.out.println(sql);
            rs = sm.executeQuery(sql);

            while (rs.next()) {
                list.add(new people(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)
                ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    public void updatePeople(String title, String pid, String pname, String piden, String phiden, String prela, String pgen, String pbirth, String phometown, String pjob, String pedu, String preli) {

        try {
            if (title.equals("Add Form")) {
                sql = "INSERT INTO People VALUES (N\'"
                        + pname + "\', "
                        + piden + ", "
                        + phiden + ", N\'"
                        + prela + "\', N\'"
                        + pgen + "\', N\'"
                        + pbirth + "\', N\'"
                        + phometown + "\', N\'"
                        + pjob + "\', N\'"
                        + pedu + "\', N\'"
                        + preli
                        + "\')";

            } else {
                sql = "UPDATE People SET"
                        + " PName = N\'" + pname
                        + "\', PIdentity = " + piden
                        + ", HouseholderIdentity = " + phiden
                        + ", PRelationshipwithHouseholder = N\'" + prela
                        + "\', PGender = N\'" + pgen
                        + "\', PBirthday = N\'" + pbirth
                        + "\', PHometown = N\'" + phometown
                        + "\', PJob = N\'" + pjob
                        + "\', PEdu = N\'" + pedu
                        + "\', PReligion = N\'" + preli
                        + "\' WHERE PID = " + String.valueOf(pid);
            }
            System.out.println(sql);
            sm.executeUpdate(sql);
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            check = false;
        }
    }

    public void deletePeople(Object id) {
        try {
            String sql = "DELETE FROM People WHERE PID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean validatePeople() {
        return check;
    }
}
