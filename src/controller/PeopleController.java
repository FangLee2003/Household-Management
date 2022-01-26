package controller;

import model.people;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeopleController {
    private Connection con;
    private Statement sm;
    private ResultSet rs;

    public PeopleController() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public List<people> getPeople() {
        List<people> list = new ArrayList<people>();

        try {
            String sql = "SELECT * FROM People";
            ResultSet rs = sm.executeQuery(sql);
            System.out.println(sql);

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
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public void editPeople(String title, String pid, String pname, String piden, String phiden, String prela, String pgen, String pbirth, String phometown, String pjob, String pedu, String preli) {

        try {
            String sql;
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletePeople(Object id) {
        try {
            String sql = "DELETE FROM People WHERE PID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
