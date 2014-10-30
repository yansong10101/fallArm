<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="fallArmDB.*" import="java.util.*" import="fallArmAction.*" %>
<title>Insert title here</title>
</head>
<body>

<!-- **** create patient object ****-->
	<p><strong>Patient Information</strong></p>
	<form action="PatientAction" method="GET">
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
		</br><select name="level">
				 		   	<option>1
				 		   	<option>2
				 		   </select>
		</br><input name="add_patient" type="submit" value="ADD patient">
	</form>

<!-- **** Get patient object **** -->
	<form action="${pageContext.request.contextPath}/src/fallArmAction/PatientControl" method="GET">
		</br><input name="patient_id" type="text" placeholder="input patient ID">
		</br><input name="get_patient" type="submit" value="Get patient">
	</form>
		
<!-- **** create nurse object ****-->
	<p><strong>Nurse Information</strong></p>
	<form action="NurseControl?action=toServlet" method="GET">
		<input name="firstname" type="text" placeholder="first name"> 
		</br><input name="lastname" type="text" placeholder="last name">
		</br><input name="address" type="text" placeholder="Address">
		</br><select name="gender">
				 <option>M
				 <option>F
				 </select>
		</br><select name="patient_level">
				 		   	<option>1
				 		   	<option>2
				 		   	<option>3
				 		   </select>
		</br><input name="add_nurse" type="submit" value="ADD nurse">
	</form>
	
<!-- **** Get nurse object **** -->
	<form action="NurseControl?action=toServlet" method="GET">
		</br><input name="nurse_id" type="text" placeholder="input nurse ID">
		</br><input name="get_nurse" type="submit" value="Get nurse">
	</form>
	
<!-- **** create viewer object ****-->
	<p><strong>Viewer Information</strong></p>
	<form action="ViewerControl?action=toServlet" method="GET">
		<input name="firstname" type="text" placeholder="first name"> 
		</br><input name="lastname" type="text" placeholder="last name">
		</br><input name="address" type="text" placeholder="Address">
		</br><input name="email" type="text" placeholder="Email">
		</br><input name="phone" type="text" placeholder="Phone">
		</br><select name="patient_id">
				 		   	<option>7
				 		   	<option>8
				 		   	<option>9
				 		   	<option>10
				 		   	<option>11
				 		   </select>
		</br><input name="add_viewer" type="submit" value="ADD viewer">
	</form>
	
<!-- **** Get viewer object **** -->
	<form action="ViewerControl?action=toServlet" method="GET">
		</br><input name="patient_id" type="text" placeholder="input patient ID">
		</br><input name="get_viewers" type="submit" value="Get viewer list">
	</form>
	
<!-- **** display all patients object **** -->
	
</body>
</html>