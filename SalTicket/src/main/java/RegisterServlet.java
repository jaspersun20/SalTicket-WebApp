

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		pw.print(username);
		
		int verification = JDBCConnector.loginUser(username, password);
		
		
		if(verification == 1) {
//			response.sendRedirect("index.html");
			pw.print(1);
			pw.flush();
		}
		else {
			pw.print(0);
			pw.flush();
//			response.sendRedirect("login.html?loginerror=true");
//			response.getWriter().write(0);
//			System.out.println("logininerrorrr!");
		}
		
		
	}

}
