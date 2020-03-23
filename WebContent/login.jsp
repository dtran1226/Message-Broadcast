<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/theme.css">
</head>
<body>
	<h1>Login</h1>
    <form action="login" method="GET">
		<input class ="input" type="text" name="username" placeholder="Enter your username" required="required">
		<br />
		<br />
		<input class ="input" type="text" name="pwd" placeholder="Enter your password" required="required">
		<br />
		<input type="submit" value="Login">
	</form>
	<a href="register.jsp">Register</a>
	<br />
<%
 if("invalid".equals(session.getAttribute("invalid"))) { 
 	session.invalidate(); %>
	 <div style="color: red">Invalid username or password</div>
<% } %>
</body>
</html>