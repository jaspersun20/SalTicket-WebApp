

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SellWalletServlet
 */
@WebServlet("/SellWalletServlet")
public class SellWalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketNum =Integer.parseInt(request.getParameter("TicketNum"));
		System.out.println(ticketNum);
		String eventId = request.getParameter("Eventid");
		String username = request.getParameter("Name");
		double price = Double.parseDouble(request.getParameter("Maxprice"));
		
		int userid = JDBCConnector.findUserid(username);
		int result = JDBCConnector.updateWallet(userid, eventId, ticketNum, price);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print("{\"result\": " + result + "}");
		out.flush();
		

	}

}
