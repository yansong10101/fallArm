<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nurse Information</title>
</head>
<body>
	<p><strong>Create a Nurse</strong></p>
	<form action="NurseAction" method="POST">
		<input name="firstname" type="text" placeholder="first name"> 
		</br><input name="lastname" type="text" placeholder="last name">
		</br><input name="address" type="text" placeholder="Address">
		</br>Gender<select name="gender">
				 <option>M
				 <option>F
				 </select>
		</br>Group ID<select name="patient_level">
				 		   	<option>1
				 		   	<option>2
				 		   	<option>3
				 		   </select>
		</br><input name="uname" type="text" placeholder="user name">
		</br><input name="password" type="password" placeholder="password">
		</br><input name="add_nurse" type="submit" value="ADD nurse">
	</form>
</body>
</html>