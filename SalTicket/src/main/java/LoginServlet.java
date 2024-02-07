

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
			
		String username = request.getParameter("logusername");
		String password = request.getParameter("logpassword");
		String email = request.getParameter("email");
		String confirm = request.getParameter("confirmpassword");
		double balance = 3000.00;
		int user_id;
		
		pw.print(username);
		
		
	
		
//		User newuser = new User(username, email, password, user_id, balance);
		
		int verify = JDBCConnector.verifyUser(email);
		if(verify==1) {
			response.sendRedirect("login.html?emailerror=true");
		}
		else {
			user_id = JDBCConnector.registerUser(username, password, email);
//			if(user_id != 0) {
//				Cookie cookie = new Cookie("Userid", Integer. toString(user_id));
//		        cookie.setMaxAge(60 * 60 * 24); // Sets the cookie to expire in 24 hours
//		        response.addCookie(cookie);
//			}
			response.sendRedirect("index.html");
		}
		
		
	}

}
