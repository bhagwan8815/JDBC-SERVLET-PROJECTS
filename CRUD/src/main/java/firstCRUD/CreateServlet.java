package firstCRUD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Adding CSS styling
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
        out.println("h1 { text-align: center; color: #333; margin-top: 20px; }");
        out.println("form { width: 300px; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("input[type='text'], input[type='email'], input[type='number'] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { width: 100%; padding: 10px; background: #007BFF; color: white; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background: #0056b3; }");
        out.println("a { display: block; text-align: center; margin: 10px 0; color: #007BFF; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Adding the form content
        out.println("<h1>Create Record</h1>");
        out.println("<form action='CreateServlet' method='post'>");
        out.println("ID: <input type='number' name='id' required><br>");
        out.println("Name: <input type='text' name='name' required><br>");
        out.println("Email: <input type='email' name='email' required><br>");
        out.println("Country: <input type='text' name='country' required><br>");
        out.println("<input type='submit' value='Create'>");
        out.println("</form>");
        out.println("<a href='index.html'>Back to Home</a>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        try (Connection con = DBConnection.getConnection()) {
            // Insert query
            String query = "INSERT INTO users (id, name, email, country) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, country);

            int rowsInserted = ps.executeUpdate();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Adding CSS for the response page
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
            out.println(".message { width: 400px; margin: 50px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); text-align: center; }");
            out.println("p { color: #333; font-size: 16px; }");
            out.println("a { color: #007BFF; text-decoration: none; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='message'>");
            if (rowsInserted > 0) {
                out.println("<p>Record created successfully!</p>");
            } else {
                out.println("<p>Failed to create record. Please try again.</p>");
            }
            out.println("<a href='CreateServlet'>Create Another Record</a><br>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}




//package firstCRUD;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/CreateServlet")
//public class CreateServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("<h1>Create Record</h1>");
//        out.println("<form action='CreateServlet' method='post'>");
//        out.println("ID: <input type='number' name='id' required><br>");
//        out.println("Name: <input type='text' name='name' required><br>");
//        out.println("Email: <input type='email' name='email' required><br>");
//        out.println("Country: <input type='text' name='country' required><br>");
//        out.println("<input type='submit' value='Create'>");
//        out.println("</form>");
//        out.println("<a href='index.html'>Back to Home</a>");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//
//        try (Connection con = DBConnection.getConnection()) {
//            // Insert query
//            String query = "INSERT INTO users (id, name, email, country) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setInt(1, id);
//            ps.setString(2, name);
//            ps.setString(3, email);
//            ps.setString(4, country);
//
//            int rowsInserted = ps.executeUpdate();
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//            if (rowsInserted > 0) {
//                out.println("<p>Record created successfully!</p>");
//            } else {
//                out.println("<p>Failed to create record. Please try again.</p>");
//            }
//            out.println("<a href='CreateServlet'>Create Another Record</a><br>");
//            out.println("<a href='index.html'>Back to Home</a>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
//          
//        }
//    }
//}
