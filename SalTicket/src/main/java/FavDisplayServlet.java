

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * Servlet implementation class FavDisplayServlet
 */
@WebServlet("/FavDisplayServlet")
public class FavDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set up response data
		String responseJson = "";
		
		// Set response type to JSON
		response.setContentType("application/json");
		
		// Get first name from query
		String uname = request.getParameter("Name");
		System.out.println("Username is : " + uname);
		int user_id = JDBCConnector.findUserid(uname);
		System.out.println("userid is : " + String.valueOf(user_id));
		
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
		ps = conn.prepareStatement("SELECT event_id from favorites where user_id = ?");
		ps.setInt(1, user_id);
		rs = ps.executeQuery();
		
		
		ArrayList<String> userarray = new ArrayList<String>();
		
		while (rs.next()) {
			String ud = rs.getString("event_id");
			System.out.println(ud);
			userarray.add(ud);
		}
		
		responseJson = generateJSON(userarray);
		System.out.println(responseJson);
	} catch (SQLException sqle) {
		System.out.println ("SQLException in registerUser: " + sqle.getMessage());
		sqle.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
		response.getWriter().write(responseJson);
		
	}
	
	private String generateJSON(ArrayList<String> userarray) {
		// Create a Gson object
	    Gson gson = new Gson();

	    // Convert the ArrayList to a JSON string
	    String json = gson.toJson(userarray);

	    // Return the JSON string
	    return json;
	}

}
