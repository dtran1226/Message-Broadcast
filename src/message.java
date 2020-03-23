import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.User;

/**
 * Servlet implementation class message
 */
@WebServlet("/message")
public class message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get input message parameter from user 
		String message = request.getParameter("message");
		// Check if message of the current user is null. If so, forward
		// to Broadcast page to add message. Unless, go to 'message.jsp'
		// to show all the messages of all user 
		if(message != null) {
			// Update the message of the current user
			DBConnection db = new DBConnection();
			Connection conn = db.connect();
			db.addMessage(conn, request.getSession().getAttribute("username").toString(), message);
			ArrayList<User> users = db.getAllUsers(conn);
			db.closeConnection(conn);
			request.getSession().setAttribute("users", users);
			//response.sendRedirect("message.jsp");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/message.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/broadcast.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("broadcast.jsp");
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
