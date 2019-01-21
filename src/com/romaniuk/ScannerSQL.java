package com.romaniuk;
import com.mysql.jdbc.log.NullLogger;

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
        System.out.print("Enter name: ");
        String first_name = sc.next();
        System.out.print("Enter id: ");
        int id = sc.nextInt();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/score?autoReconnect=true&useSSL=false";
        String user = "root";
        String pass = "Sasanka01";

        System.out.print("\nFirst name is " +first_name+ "\n");
        System.out.print("Your ID is " +id+ "\n");

        try {
            // Get a connection to database
            //myConn = DriverManager.getConnection(dbUrl, user, pass);
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/score?autoReconnect=true&useSSL=false",
                    "root",
                    "Sasanka01");

            System.out.println("\nDatabase connection ok \n");
            myStmt = myConn.createStatement();
            PreparedStatement sql = myConn.prepareStatement(
                    "insert into score_table values (?,?);");

            // set parameter values
            sql.setInt(1,id);
            sql.setString(2, first_name);
            sql.execute();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            //close(myConn, myStmt, null);
        }

        // Select nad print from SQL
        System.out.println("Print SQL Table yes/no ?");
        String openTable = sc.next();

        if(openTable.equals("no")){
            System.out.println(" ");
        }else {
            try {
                // Prepare statement
                PreparedStatement sql = myConn.prepareStatement ("select * from score_table");

                // Execute SQL query
                myRs = sql.executeQuery();

                // Process result set
                System.out.print("SELECT * FROM socer_table\n\n");
                while (myRs.next()) {
                    int idRow = myRs.getInt("id");
                    String firstName = myRs.getString("first_name");
                    System.out.printf("%s , %s %n",idRow, firstName);
                }

                System.out.println();
            } catch (Exception exc) {
                exc.printStackTrace();
            } finally {
                close(myConn,myStmt, myRs);
            }
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
