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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
        out.println("h1 { text-align: center; color: #333; margin-top: 20px; }");
        out.println("form { max-width: 400px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println("input[type='text'], input[type='email'], input[type='number'], input[type='submit'] {");
        out.println("  width: calc(100% - 22px); margin-bottom: 15px; padding: 10px; font-size: 16px; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='submit'] { background-color: #5cb85c; color: white; border: none; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #4cae4c; }");
        out.println("a { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007bff; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style></head><body>");

        out.println("<h1>Update Record</h1>");
        out.println("<form action='UpdateServlet' method='post'>");
        out.println("<label for='id'>ID to Update:</label>");
        out.println("<input type='number' name='id' id='id' required><br>");
        out.println("<label for='name'>New Name:</label>");
        out.println("<input type='text' name='name' id='name'><br>");
        out.println("<label for='email'>New Email:</label>");
        out.println("<input type='email' name='email' id='email'><br>");
        out.println("<label for='country'>New Country:</label>");
        out.println("<input type='text' name='country' id='country'><br>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
        out.println("<a href='index.html'>Back to Home</a>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        try (Connection con = DBConnection.getConnection()) {
            // Update query (dynamic columns)
            StringBuilder query = new StringBuilder("UPDATE users SET ");
            boolean first = true;

            if (name != null && !name.isEmpty()) {
                query.append("name = ?");
                first = false;
            }
            if (email != null && !email.isEmpty()) {
                if (!first) query.append(", ");
                query.append("email = ?");
                first = false;
            }
            if (country != null && !country.isEmpty()) {
                if (!first) query.append(", ");
                query.append("country = ?");
            }
            query.append(" WHERE id = ?");

            PreparedStatement ps = con.prepareStatement(query.toString());

            int paramIndex = 1;
            if (name != null && !name.isEmpty()) ps.setString(paramIndex++, name);
            if (email != null && !email.isEmpty()) ps.setString(paramIndex++, email);
            if (country != null && !country.isEmpty()) ps.setString(paramIndex++, country);
            ps.setInt(paramIndex, id);

            int rowsUpdated = ps.executeUpdate();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; }");
            out.println("h1 { text-align: center; color: #333; margin-top: 20px; }");
            out.println("p { text-align: center; font-size: 18px; }");
            out.println("a { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #007bff; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style></head><body>");

            if (rowsUpdated > 0) {
                out.println("<p>Record updated successfully!</p>");
            } else {
                out.println("<p>No record found with ID: " + id + "</p>");
            }
            out.println("<a href='UpdateServlet'>Update Another Record</a><br>");
            out.println("<a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
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
//@WebServlet("/UpdateServlet")
//public class UpdateServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        out.println("<h1>Update Record</h1>");
//        out.println("<form action='UpdateServlet' method='post'>");
//        out.println("ID to Update: <input type='number' name='id' required><br>");
//        out.println("New Name: <input type='text' name='name'><br>");
//        out.println("New Email: <input type='email' name='email'><br>");
//        out.println("New Country: <input type='text' name='country'><br>");
//        out.println("<input type='submit' value='Update'>");
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
//            // Update query (dynamic columns)
//            StringBuilder query = new StringBuilder("UPDATE users SET ");
//            boolean first = true;
//
//            if (name != null && !name.isEmpty()) {
//                query.append("name = ?");
//                first = false;
//            }
//            if (email != null && !email.isEmpty()) {
//                if (!first) query.append(", ");
//                query.append("email = ?");
//                first = false;
//            }
//            if (country != null && !country.isEmpty()) {
//                if (!first) query.append(", ");
//                query.append("country = ?");
//            }
//            query.append(" WHERE id = ?");
//
//            PreparedStatement ps = con.prepareStatement(query.toString());
//
//            int paramIndex = 1;
//            if (name != null && !name.isEmpty()) ps.setString(paramIndex++, name);
//            if (email != null && !email.isEmpty()) ps.setString(paramIndex++, email);
//            if (country != null && !country.isEmpty()) ps.setString(paramIndex++, country);
//            ps.setInt(paramIndex, id);
//
//            int rowsUpdated = ps.executeUpdate();
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//            if (rowsUpdated > 0) {
//                out.println("<p>Record updated successfully!</p>");
//            } else {
//                out.println("<p>No record found with ID: " + id + "</p>");
//            }
//            out.println("<a href='UpdateServlet'>Update Another Record</a><br>");
//            out.println("<a href='index.html'>Back to Home</a>");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().println("<p>Error: " + e.getMessage() + "</p>");
//        }
//    }
//}
