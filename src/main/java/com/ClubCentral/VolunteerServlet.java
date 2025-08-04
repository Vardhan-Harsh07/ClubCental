package com.ClubCentral;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class VolunteerServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    // Retrieve data from the HTML form
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String msg = request.getParameter("message");
    
    // Create a new Volunteer object with the data
    //Volunteer volunteer = new Volunteer(name, email, phone, message);
    
    // Store the Volunteer object in the database
    try {
      // Establish a database connection
      String jdbcUrl = "jdbc:mysql://localhost:3306/volunteers";
      String dbUser = "root";
      String dbPassword = "Vardhan$187";
      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
      
      // Create a PreparedStatement to insert the Volunteer object into the database
      String insertSql = "INSERT INTO volunteer VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, email);
      preparedStatement.setString(3, phone);
      preparedStatement.setString(4, msg);
      
      // Execute the PreparedStatement and close the database connection
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
      System.out.println(name+email+phone+msg);
      
      // Redirect the user to a success page
      response.sendRedirect("success.html");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}