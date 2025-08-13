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
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String lOAD_DRIVERString ="com.mysql.cj.jdbc.Driver";
	public static String URL ="jdbc:mysql://localhost:3306/Userdb";
	public static String PASSWORD ="system";
	public static String USERNAME="root";
	private Connection connection;
       
   
    public LoginServlet() {
        super();
   
    }

	
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
		
		String uname = request.getParameter("unmae");
		String pword = request.getParameter("pword");
		
		
		try {
			 
			PreparedStatement ps = connection.prepareStatement("select * from uinfo where uname=?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			
			if(rs.next()) {
				pw.println("welcome :"+uname);
			}else {
				pw.println("user not valid :");
			}
			pw.println("</center></body></html>");
		}
		catch(SQLException e) {
			e.printStackTrace()
;			
		}

		
		doGet(request, response);
	}

}
