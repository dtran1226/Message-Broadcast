<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Broadcast</title>
<link rel="stylesheet" type="text/css" href="css/theme.css">
</head>
<body>
<%
 if(session.getAttribute("username") == null) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	 //response.sendRedirect("login.jsp");
 }
%>
<h1>Broadcast Message</h1>
<form action="message" method="GET">
		<input class ="input" type="text" name="message" placeholder="Enter your message" maxlength="50" required="required">
		<br />
		<input type="submit" value="Submit">
	</form>
</body>
</html>