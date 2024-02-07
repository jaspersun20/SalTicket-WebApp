

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;

/**
 * Servlet implementation class FavoritesServlet
 */
@WebServlet("/FavoritesServlet")
public class FavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cookie[] cookies = request.getCookies();
//        String eventId = null;
//        
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("Eventid".equals(cookie.getName())) {
//                    eventId = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        if (eventId != null) {
//            yourFunction(eventId);
//        } else {
//            System.out.println("Eventid cookie not found.");
//        }
//    }
		String eventId = request.getParameter("Eventid");
		String username = request.getParameter("Name");
		

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
		 System.out.println("userid: " + userid);
		 JDBCConnector.addFavorites(userid, eventId);
	    }

	    public void yourFunction(String eventId, String name) {
	        // Your function logic goes here
	        System.out.println("Eventid: " + eventId);
	        System.out.println("Name: " + name);
	    }

	}


