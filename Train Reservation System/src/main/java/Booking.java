

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

@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Booking() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname= request.getParameter("name");
		String uemail = request.getParameter("email");
	    String  uphone = request.getParameter("phone");
	    String  ufrom = request.getParameter("ufrom");
	    String  uto = request.getParameter("uto");
	    String  udate = request.getParameter("date");
	    String  upassengers = request.getParameter("passengers");
	    String  upayment = request.getParameter("payment");
	    
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/train?characterEncoding=UTF-8","root","root");
	      String query = "INSERT INTO booking(name, email, phone, ufrom, uto, date, passengers, payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	      PreparedStatement statement = con.prepareStatement(query);
	      statement.setString(1, uname);
	      statement.setString(2, uemail);
	      statement.setString(3, uphone);
	      statement.setString(4, ufrom);
	      statement.setString(5, uto);
	      statement.setString(6, udate);
	      statement.setString(7, upassengers);
	      statement.setString(8, upayment);
	      statement.executeUpdate();

	      PrintWriter out=response.getWriter();
	      response.setContentType("text/html");
	     
	      out.println("<br><br><br><br><h1 Style='text-align: center',padding:50px, background-color: linear-gradient(to bottom, #FAF0E4, #9BCDD2, #FFDEDE);>Train Ticket Booking successfull !!</h1><br><br>");
	      
	      out.println("<h1 Style='text-align: center',padding:50px, background-color: linear-gradient(to bottom, #FAF0E4, #9BCDD2, #FFDEDE);>Thank you for Booking!!</h1>");
	      con.close();
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
		doGet(request, response);
	}

}
