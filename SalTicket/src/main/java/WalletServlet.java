

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WalletServlet
 */
@WebServlet("/WalletServlet")
public class WalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketNum =Integer.parseInt(request.getParameter("TicketNum"));
		System.out.println(ticketNum);
		String eventId = request.getParameter("Eventid");
		String username = request.getParameter("Name");
		
		double price = Double. parseDouble(request.getParameter("Minprice"));
		

		 if (eventId != null && username != null) {
	            yourFunction(eventId, username);
	            response.setContentType("text/plain");
	            response.getWriter().write("Data processed successfully.");
	        } else {
	            System.out.println("Eventid and/or Name parameters not found.");
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter().write("Eventid and/or Name parameters not found.");
	        }
		 
		 int userid = JDBCConnector.findUserid(username);
		 JDBCConnector.addWallet(userid, eventId, ticketNum, price);
		 
		
		
	}
	
	public void yourFunction(String eventId, String name) {
        // Your function logic goes here
        System.out.println("Eventid: " + eventId);
        System.out.println("Name: " + name);
    }

}
