<%@page import="java.util.ArrayList"%>
<%@page import="classes.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Message</title>
<link rel="stylesheet" type="text/css" href="css/theme.css">
</head>
<body>
<h1>Message Page</h1>
<%
 if(session.getAttribute("username") == null) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	 //response.sendRedirect("login.jsp");
 } else if(session.getAttribute("users") != null) {
	 ArrayList<User> users = (ArrayList<User>)(session.getAttribute("users"));
%>
		 <table>
		  <tr>
		    <th>Username</th>
		    <th>Message</th> 
		  </tr>
<%
		 for (int i = 0; i < users.size(); i++) { %>
		  <tr>
		    <td><%=users.get(i).getUsername()%></td>
		    <td><%=users.get(i).getMessage()%></td> 
		  </tr>
<%
		 } %>
		 </table>
<%
	 }
%>
	<a href="broadcast.jsp">Broadcast</a>
	<br />
    <form action="logout" method="GET">
		<input type="submit" value="Logout">
	</form>
</body>
</html>