

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

@WebServlet("/subscribe")
public class subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public subscribe() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname= request.getParameter("name");
		String uemail = request.getParameter("email");
	    
	    
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/train?characterEncoding=UTF-8","root","root");
	      String query = "INSERT INTO subscriptions (name, email) VALUES (?, ?)";
	      PreparedStatement statement = con.prepareStatement(query);
	      statement.setString(1, uname);
	      statement.setString(2, uemail);
	      statement.executeUpdate();

	      PrintWriter out=response.getWriter();
	      response.setContentType("text/html");
	      out.println("<h1>Subscribed Successfully !!</h1>");
	      con.close();
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
