package com.romaniuk;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.*;

public class ScannerSQL {

    public static void main(String[] args) throws SQLException {


        Scanner sc=new Scanner(System.in);
        System.out.print("Enter name");
        String first_name = sc.next();
        System.out.print("Enter id");
        int id = sc.nextInt();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/score?autoReconnect=true&useSSL=false";
        String user = "root";
        String pass = "Sasanka01";

        System.out.print("First name is " +first_name);
        System.out.print("Your ID is " +id);

        try {
            // Get a connection to database
            //myConn = DriverManager.getConnection(dbUrl, user, pass);
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/score?autoReconnect=true&useSSL=false",
                    "root",
                    "Sasanka01");

            System.out.println("Database connection ok");
            myStmt = myConn.createStatement();
            PreparedStatement sql = myConn.prepareStatement(
                    "insert into score_table values (?,?);");

            // set parameter values
            sql.setInt(1,id);
            sql.setString(2,first_name);

            System.out.println("    ");
            sql.execute();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }
    private static void close(Connection myConn, Statement myStmt,
                              ResultSet myRs) throws SQLException {
        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }

}
