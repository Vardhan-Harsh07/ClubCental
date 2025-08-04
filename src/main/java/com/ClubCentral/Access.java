package com.ClubCentral;


import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class Access extends HttpServlet {
  
  private String databaseUrl = "jdbc:mysql://localhost/cc";
  private String databaseUser = "root";
  private String databasePassword = "Vardhan$187";
  
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println(username+password);
    
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cc", "root", "Vardhan$187");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE enroll='" + username + "' AND pass='" + password + "'");
      
      if (rs.next()) {
        response.sendRedirect("dashboard.html");
      } else {
        response.sendRedirect("invalid.html");
      }
      
      rs.close();
      stmt.close();
      con.close();
   }catch(ClassNotFoundException e) {
	   e.printStackTrace();
   }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
}