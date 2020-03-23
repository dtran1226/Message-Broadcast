import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import classes.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Connect to DB and get all users
		DBConnection db = new DBConnection();
		Connection conn = db.connect();
		ArrayList<User> users = db.getAllUsers(conn);
		db.closeConnection(conn);
		// Get input parameters for username and pwd from client request
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		boolean userExist = false;
		// Check if the input username and pwd already exist in DB
		// It means the user already has an account
		for (int i =0; i < users.size(); i++) {
			if (username.equals(users.get(i).getUsername()) && pwd.equals(users.get(i).getPassword())) {
				userExist = true;
				break;
			}
		}
		// If exsit (login sucessfully), go to Message page
		if (userExist) {
			// Put all necessary variables to session for being used
			// later during the current active session
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30);
			session.setAttribute("username", username);
			session.setAttribute("pwd", pwd);
			session.setAttribute("users", users);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/message.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("message.jsp");
		} 
		// Unless, stay at Login page with "invalid" error message
		else {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30);
			session.setAttribute("invalid", "invalid");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
