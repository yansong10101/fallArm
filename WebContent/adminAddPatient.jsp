<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Information</title>
</head>
<body>
	<p><strong>Create a patient</strong></p>
	<form action="PatientAction" method="POST">
		<input name="firstname" type="text" placeholder="first name"> 
		</br><input name="lastname" type="text" placeholder="last name">
		</br><input name="address" type="text" placeholder="Address">
		</br><select name="gender">
				 <option>M
				 <option>F
				 </select>
		</br><select name="birthdayYear"><% for(int i = 2014; i >= 1990; i--) out.println("<option>" + i); %></select>
				   		<select name="birthdayMonth"><% for(int i = 12; i >= 1; i--) out.println("<option>" + i); %></select>
				   		<select name="birthdayDay"><% for(int i = 31; i >= 1; i--) out.println("<option>" + i); %></select>
		</br>group ID<select name="level">
				 		   	<option>1
				 		   	<option>2
				 		   	<option>3
				 		   </select>
		</br><input name="add_patient" type="submit" value="ADD patient">
	</form>
</body>
</html>