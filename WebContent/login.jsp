<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="fallArmDB.*" import="config.*" import="fallArmAction.*"%>
<title>Login</title>
</head>
<body>
	<p><strong>Login</strong></p>
	<form action="LoginAction" method="POST">
		</br><input name="uname" type="text" placeholder="user name" require>
		</br><input name="password" type="password" placeholder="password" require>
		</br><input name="login" type="submit" value="Login">
	</form>	
</body>
</html>