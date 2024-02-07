

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteFavServlet
 */
@WebServlet("/DeleteFavServlet")
public class DeleteFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = request.getParameter("Eventsid");
		System.out.println("eventid in deleteservlet is: " +eventId);
		JDBCConnector.deleteFavorites(eventId);
		
	}

}
