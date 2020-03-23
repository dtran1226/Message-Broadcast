import java.sql.*;
import java.util.ArrayList;
import classes.User;

public class DBConnection {
	/*
	 * Establish a DB connection with MySQL 
	 */
	public Connection connect() {
		Connection conn = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/message";
            String user = "root";
            String pass = "thucTRAN1993";
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
        }
        return conn;
	}
	
	/*
	 * Select all Users from DB
	 */
	public ArrayList<User> getAllUsers(Connection conn) {
		Statement stmt;
		ArrayList<User> users = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String SQL = "Select * from user";
	        ResultSet rs = stmt.executeQuery(SQL);
	        while(rs.next()) {
	        	User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
	        	users.add(user);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	/*
	 * Insert a user
	 */
	public boolean addUser(Connection conn, String userName, String password, String message) {
		Statement stmt;
		boolean success = true;
		try {
			stmt = conn.createStatement();
			String SQL = "Insert into user values ('" + userName +"', '" + password + "', '" + message + "')";
	        stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	/*
	 * Update a message from a designated user
	 */
	public void addMessage(Connection conn, String userName, String message) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String SQL = "Update user set message = '" + message + "' where username = '" + userName + "'";
	        stmt.execute(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Close the current connection when not being used anymore
	 */
	public void closeConnection(Connection conn) {
		try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
}
