package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchData {
  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://localhost:3306/mydatabase";
    String user = "root"; // Replace with your MySQL username
    String password = "password"; // Replace with your MySQL password

    try {
      // Step 1: Load and register JDBC driver class
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Step 2: Establish a connection
      conn = DriverManager.getConnection(url, user, password);

      // Step 3: Create a statement
      stmt = conn.createStatement();

      // Step 4: Execute a query
      rs = stmt.executeQuery("SELECT * FROM employees");
      // Step 5: Process the results
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String position = rs.getString("position");
        System.out.println("ID: " + id + ", Name: " + name + ", Position: " + position);
      }
    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC driver not found.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("Error connecting to the database.");
      e.printStackTrace();
    } finally {
      // Step 6: Clean up the environment
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
