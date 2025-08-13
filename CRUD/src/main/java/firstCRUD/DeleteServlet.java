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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
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
        out.println("input[type='number'] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { width: 100%; padding: 10px; background: #e74c3c; color: white; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background: #c0392b; }");
        out.println("a { display: block; text-align: center; margin: 10px 0; color: #007BFF; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Adding form content
        out.println("<h1>Delete Record</h1>");
        out.println("<form action='DeleteServlet' method='post'>");
        out.println("Enter ID to delete: <input type='number' name='id' required><br>");
        out.println("<input type='submit' value='Delete'>");
        out.println("</form>");
        out.println("<a href='index.html'>Back to Home</a>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection con = DBConnection.getConnection()) {
            // SQL query to delete the record with the specified ID
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Adding CSS for response page
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

            // Adding message content
            out.println("<div class='message'>");
            if (rowsAffected > 0) {
                out.println("<p>Record with ID " + id + " deleted successfully!</p>");
            } else {
                out.println("<p>No record found with ID " + id + ".</p>");
            }
            
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("<p>Error: " + e.getMessage() + "</p>");
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
//@WebServlet("/DeleteServlet")
//public class DeleteServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.print("<h1>Delete Record</h1>");
//        out.print("<form action='DeleteServlet' method='post'>");
//        out.print("Enter ID to delete: <input type='number' name='id'><br>");
//        out.print("<input type='submit' value='Delete'>");
//        out.print("</form>");
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        try (Connection con = DBConnection.getConnection()) {
//            // SQL query to delete the record with the specified ID
//            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
//            ps.setInt(1, id);
//
//            int rowsAffected = ps.executeUpdate();
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//
//            if (rowsAffected > 0) {
//                out.print("<p>Record with ID " + id + " deleted successfully!</p>");
//            } else {
//                out.print("<p>No record found with ID " + id + ".</p>");
//            }
//            out.print("<a href='index.html'>Back to Home</a>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().print("<p>Error: " + e.getMessage() + "</p>");
//        }
//    }
//}
//
//
