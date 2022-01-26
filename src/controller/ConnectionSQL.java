package controller;

import java.sql.*;

public class ConnectionSQL {
    public static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PeopleManager;user=sa;password=sa";
        return DriverManager.getConnection(dbURL);
    }
}
/*
if(conn!=null){
        System.out.println("Connected");
        DatabaseMetaData dm=(DatabaseMetaData)conn.getMetaData();
        System.out.println("Driver name: "+dm.getDriverName());
        System.out.println("Driver version: "+dm.getDriverVersion());
        System.out.println("Product name: "+dm.getDatabaseProductName());
        System.out.println("Product version: "+dm.getDatabaseProductVersion());
        }
catch(SQLException ex){
        System.err.println("Cannot connect database, "+ex);
        }
        */

 