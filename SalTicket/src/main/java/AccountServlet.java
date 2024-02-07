

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("Name");
		double money = JDBCConnector.findUserMoney(username);
		double account = JDBCConnector.findUserValue(username);
		
		account +=money;
		// Create a JSON object with the "money" and "account" variables
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(new AccountData(money, account));

        // Set the response content type and send the JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    // A class to hold the "money" and "account" data
    static class AccountData {
        double money;
        double account;

        AccountData(double money, double account) {
            this.money = money;
            this.account = account;
        }
    }

}
