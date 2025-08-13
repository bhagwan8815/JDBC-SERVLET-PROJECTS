package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/reg")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static String lOAD_DRIVERString ="com.mysql.cj.jdbc.Driver";
	public static String URL ="jdbc:mysql://localhost:3306/Userdb";
	public static String USERNAME="root";
	public static String PASSWORD ="system";
	
	Connection connection;

    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		  try {
			  connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
	        	
	        }catch(SQLException e ) {
	        	e.printStackTrace();
	        	
	        }

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		
		try {
			
			PreparedStatement ps = connection.prepareStatement("insert into uinfo values(?,?,?,?)");
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3,uname);
			ps.setString(4,pword);
			ps.executeUpdate();
			
			
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			pw.println("<h1> registration successfully...........</h1>");
			pw.println("<a href=login.html>Login</a>");
			pw.println("</center></body></html>");
			
		}catch(SQLException e) {
			e.printStackTrace();
		
			
		}
		doGet(request, response);
	}

}
