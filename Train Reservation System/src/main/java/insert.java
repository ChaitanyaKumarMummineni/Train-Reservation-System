

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insert
 */
@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
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
		String uname= request.getParameter("txt");
		String uemail = request.getParameter("email");
	    String  upassword = request.getParameter("pswd");
	    
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/train?characterEncoding=UTF-8","root","root");
	      String query = "INSERT INTO userregistrations (username, email, password) VALUES (?, ?, ?)";
	      PreparedStatement statement = con.prepareStatement(query);
	      statement.setString(1, uname);
	      statement.setString(2, uemail);
	      statement.setString(3, upassword);
	      statement.executeUpdate();

	      PrintWriter out=response.getWriter();
	      response.setContentType("text/html");
	      out.println("<h1>Sign Up successfull !!</h1>");
	      con.close();
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
		doGet(request, response);
	}

}
