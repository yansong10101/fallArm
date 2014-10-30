<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="fallArmDB.*" import="config.*" import="fallArmAction.*"%>
<title>Viewer Information</title>
</head>
<body>
	<p><strong>Create a Viewer</strong></p>
	<form action="ViewerAction" method="POST">
		<input name="firstname" type="text" placeholder="first name"> 
		</br><input name="lastname" type="text" placeholder="last name">
		</br><input name="address" type="text" placeholder="Address">
		</br><input name="email" type="text" placeholder="Email">
		</br><input name="phone" type="text" placeholder="Phone">
		</br><input name="patient_id" type="text" placeholder="patient id">
		</br><input name="uname" type="text" placeholder="user name">
		</br><input name="password" type=password placeholder="password">
		</br><input name="add_viewer" type="submit" value="ADD viewer">
	</form>
</body>
</html>