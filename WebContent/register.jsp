<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/theme.css">
</head>
<body>
	<h1>Create a new account</h1>
    <form action="register" method="GET">
		<input class ="input" type="text" name="username" placeholder="Enter your username" required="required">
		<br />
		<br />
		<input class ="input" type="text" name="pwd" placeholder="Enter your password" required="required">
		<br />
		<br />
		<input class ="input" type="text" name="message" placeholder="Enter your initial message" required="required">
		<br />
		<input type="submit" value="Create">
	</form>
	<a href="login.jsp">Home</a>
<%
 if("registerFail".equals(session.getAttribute("registerFail"))) { 
 	session.invalidate(); %>
	 <div style="color: red">This user is already exist</div>
<% } %>
</body>
</html>