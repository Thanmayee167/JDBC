package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
  public static void main(String[] args) {
    // Database credentials and URL
    String databaseURL = "jdbc:mysql://localhost:3306/mydatabase";
    String user = "root";
    String password = "password";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      // Loading the JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Establishing a connection
      conn = DriverManager.getConnection(databaseURL, user, password);

      // Insert query with parameters
      String sql = "INSERT INTO employees (name, position) VALUES (?, ?)";
      pstmt = conn.prepareStatement(sql);

      // Setting parameters for the prepared statement
      pstmt.setString(1, "John Doe");
      pstmt.setString(2, "Software Developer");

      // Executing the insert statement
      int rowsInserted = pstmt.executeUpdate();
      if (rowsInserted > 0) {
        System.out.println("A new employee was inserted successfully!");
      }
    } catch (ClassNotFoundException e) {
      System.out.println("MySQL JDBC Driver not found.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("Error connecting to the database or executing query.");
      e.printStackTrace();
    } finally {
      // Closing resources
      try {
        if (pstmt != null) {
          pstmt.close();
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
