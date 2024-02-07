import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;

public class JDBCConnector {
	
	public static int registerUser(String username, String password, String email) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int user_id = 0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
			if(conn!=null) {
				System.out.println("Connection established.");
			}
			ps = conn.prepareStatement("INSERT INTO User(username, password, email, balance) VALUES(?, ?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setDouble(4, 3000.00);
			int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Failed to insert the record.");
            }
	      
		} catch (SQLException sqle) {
			System.out.println ("SQLException in registerUser: " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs1 != null) {
					rs1.close();
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
				if (ps1 != null) {
					ps1.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return user_id;
	}
	
	public static int verifyUser(String email) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int verify = 0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
			ps = conn.prepareStatement("SELECT * FROM user WHERE email = ?");
			ps.setString(1, email);

			rs = ps.executeQuery();
			if(rs.next())
				verify = 1;
			else
				verify = 0;
			
	      
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
		return verify;
	}
	
	public static int loginUser(String username, String password) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
			ps = conn.prepareStatement("SELECT user_id from user where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = 1;
			}
			else {
				result = 0;
			}
			
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
		return result;
	}
	
	public static void addFavorites(int user_id, String eventId) {
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
			System.out.println("In addFavorites.");
			ps = conn.prepareStatement("INSERT INTO favorites(user_id, event_id) VALUES(?, ?) ");
			ps.setInt(1, user_id);
			ps.setString(2, eventId);
			int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("added favorites..");
            } else {
                System.out.println("Not add into favoritesss");
            }
			
	      
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
	}
	
	
	public static int findUserid(String username) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int user_id=0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
		  
			ps = conn.prepareStatement("SELECT user_id FROM user WHERE username = ?");
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			if(rs.next()){
				user_id = rs.getInt(1);
			}
			else {
				user_id=0;
			}
			
	      
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
		return user_id;
	}
	
	public static int favPage(int user_id) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
			ps = conn.prepareStatement("SELECT event_d from user where user_id = ?");
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = 1;
			}
			else {
				result = 0;
			}
			
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
		return result;
	}
	
	public static void deleteFavorites(String eventId) {
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
			System.out.println("In addFavorites.");
			ps = conn.prepareStatement("DELETE FROM favorites WHERE event_id = ?");
			ps.setString(1, eventId);
			int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("deleted favorites..");
            } else {
                System.out.println("Not deleted into favoritesss");
            }
			
	      
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
	}
	
	public static void addWallet(int user_id, String eventId, int ticket, double price) {
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
			double money = ticket*price;
			String updatemoney = "UPDATE user SET balance = balance - ? WHERE user_id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(updatemoney)) {
				preparedStatement.setDouble(1, money);
				preparedStatement.setInt(2, user_id);
		        preparedStatement.executeUpdate();
		    }
			
			String condition = "SELECT * FROM wallet WHERE user_id = ? AND event_id = ? AND purchase_price = ? ";
			try (PreparedStatement preparedStatement = conn.prepareStatement(condition)) {
				preparedStatement.setInt(1, user_id);
				preparedStatement.setString(2, eventId);
				preparedStatement.setDouble(3, price);
		        rs = preparedStatement.executeQuery();
		        if(rs.next()) {
		        	String updatew = "UPDATE wallet SET num_tickets = num_tickets + ? WHERE user_id = ? AND event_id = ? AND purchase_price = ?";
		        	try (PreparedStatement preparedStatement1 = conn.prepareStatement(updatew)) {
						preparedStatement1.setInt(1, ticket);
						preparedStatement1.setInt(2, user_id);
						preparedStatement1.setString(3, eventId);
						preparedStatement1.setDouble(4, price);
				        preparedStatement1.executeUpdate();
				    }
		        }
		        
		        else {
		        	ps = conn.prepareStatement("INSERT INTO wallet(user_id, event_id, num_tickets, purchase_price) VALUES(?, ?, ?, ?) ");
					ps.setInt(1, user_id);
					ps.setString(2, eventId);
					ps.setInt(3, ticket);
					ps.setDouble(4, price);

					int rowsAffected = ps.executeUpdate();
		        }
		    }
			
			
	      
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
	}
	
	public static void sellWallet(int user_id, String eventId, int ticket, int newticket, double price) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double purchase=0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
			String findpurchase = "SELECT purchase_price FROM wallet WHERE user_id = ? AND event_id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(findpurchase)) {
				preparedStatement.setInt(1, user_id);
				preparedStatement.setString(2, eventId);
		        rs = preparedStatement.executeQuery();
		        if(rs.next()){
		        	purchase = rs.getInt(1);
		        }
		        
		    }
			
			String deletedata = "DELETE FROM wallet WHERE user_id = ? AND event_id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(deletedata)) {
				preparedStatement.setInt(1, user_id);
				preparedStatement.setString(2, eventId);
		        preparedStatement.executeUpdate();
		    }
			double money = ticket*price;
			String updatemoney = "UPDATE user SET balance = balance + ? WHERE user_id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(updatemoney)) {
				preparedStatement.setDouble(1, money);
				preparedStatement.setInt(2, user_id);
		        preparedStatement.executeUpdate();
		    }
			if(newticket>0) {
			ps = conn.prepareStatement("INSERT INTO wallet(user_id, event_id, num_tickets, purchase_price) VALUES(?, ?, ?, ?) ");
			ps.setInt(1, user_id);
			ps.setString(2, eventId);
			ps.setInt(3, newticket);
			ps.setDouble(4, purchase);
			
			ps.executeUpdate();
			}
	      
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
	}
	
	public static int updateWallet(int user_id, String eventId, int ticket, double price) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
			System.out.println("In update Wallet.");
			ps = conn.prepareStatement("SELECT SUM(num_tickets) FROM wallet WHERE user_id = ? AND event_id = ?");
			ps.setInt(1, user_id);
			ps.setString(2, eventId);
			rs = ps.executeQuery();
            int real_ticket = 0;
            int newticket = 0;
			if(rs.next()){
				real_ticket = rs.getInt(1);
				System.out.println("Total ticket num currently is" + real_ticket);
				if(ticket<=real_ticket) {
					newticket = real_ticket - ticket;
					sellWallet(user_id, eventId, ticket, newticket, price);
					result = 1;
					return result;
				}
				else {
					result=0;
				}
			} 
			else {
                System.out.println("Erroor in sellWallet JDBC");
            }
			
	      
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
		return result;
	}
	
	public static double findUserMoney(String username) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double money=0;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
		  
			ps = conn.prepareStatement("SELECT balance FROM user WHERE username = ?");
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			if(rs.next()){
				money = rs.getDouble(1);
			}
			else {
				money=0;
			}
			
	      
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
		return money;
	}
	
	public static double findUserValue(String username) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double money=0;
		int userid = findUserid(username);
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sal?user=root&password=Jaspersun2001");
		  
			ps = conn.prepareStatement("SELECT SUM(num_tickets*purchase_price) FROM wallet WHERE user_id = ?");
			ps.setInt(1, userid);
			
			rs = ps.executeQuery();
			if(rs.next()){
				money = rs.getDouble(1);
			}
			else {
				money=0;
			}
			
	      
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
		return money;
	}
	
	

	
	
}
