

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
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Add the newly added user to DB
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String message = request.getParameter("message");
		DBConnection db = new DBConnection();
		Connection conn = db.connect();
		boolean success = db.addUser(conn, username, pwd, message);
		// If success, go to Message page
		if(success) {
			ArrayList<User> users = db.getAllUsers(conn);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("pwd", pwd);
			request.getSession().setAttribute("users", users);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/message.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("message.jsp");
		}
		// Unless, stay at Register page and show registerFail error message
		else {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(30);
			session.setAttribute("registerFail", "registerFail");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("register.jsp");
		}
		db.closeConnection(conn);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
