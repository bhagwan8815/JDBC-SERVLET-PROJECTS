package firstCRUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // CSS Styling
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
        out.println("h1 { text-align: center; color: #333; margin-top: 20px; }");
        out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: #fff; }");
        out.println("th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }");
        out.println("th { background-color: #4CAF50; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #f1f1f1; }");
        out.println("a { display: block; text-align: center; margin: 20px auto; color: #4CAF50; text-decoration: none; font-size: 18px; }");
        out.println("a:hover { color: #333; }");
        out.println("</style>");

        out.println("<h1>User Records</h1>");

        try (Connection con = DBConnection.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            out.print("<table>");
            out.print("<tr><th>ID</th><th>Name</th><th>Email</th><th>Country</th></tr>");
            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getInt("id") + "</td>");
                out.print("<td>" + rs.getString("name") + "</td>");
                out.print("<td>" + rs.getString("email") + "</td>");
                out.print("<td>" + rs.getString("country") + "</td>");
                out.print("</tr>");
            }
            out.print("</table>");

            out.println("<a href='index.html'>Back to Home</a>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color: red; text-align: center;'>Error retrieving data: " + e.getMessage() + "</p>");
        }
    }
}






//package firstCRUD;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//@WebServlet("/ReadServlet")
//public class ReadServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        try (Connection con = DBConnection.getConnection()) {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
//
//            out.print("<table border='1'>");
//            out.print("<tr><th>ID</th><th>Name</th><th>Email</th><th>Country</th></tr>");
//            while (rs.next()) {
//                out.print("<tr>");
//                out.print("<td>" + rs.getInt("id") + "</td>");
//                out.print("<td>" + rs.getString("name") + "</td>");
//                out.print("<td>" + rs.getString("email") + "</td>");
//                out.print("<td>" + rs.getString("country") + "</td>");
//                out.print("</tr>");
//            }
//            out.print("</table>");
//            out.println("<a href='index.html'>Back to Home</a>");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

