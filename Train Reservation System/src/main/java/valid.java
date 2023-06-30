

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class valid
 */
@WebServlet("/valid")
public class valid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public valid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("email");
	    String  upassword = request.getParameter("pswd");
	    
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/train?characterEncoding=UTF-8","root","root");
	      
	      String query = "select * from userregistrations where email=? and password=?";
	      PreparedStatement pst = con.prepareStatement(query);
	      
	      pst.setString(1,uemail);
	      pst.setString(2, upassword);
	      
	      ResultSet rs = pst.executeQuery();
	      
	      if(rs.next()) {   
	        request.setAttribute("output", rs);
	        ServletContext ctxt = getServletContext();
	        RequestDispatcher rd = ctxt.getRequestDispatcher("/index1.html");
	        rd.forward(request, response);
	      }else {       
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
	        rd.include(request, response);
	        out.println("<h1>Invalid UserName or Password</h1>");
	      }
	      
	      }catch (Exception e) {
	      e.printStackTrace();
	    }
		doGet(request, response);
	}

}
