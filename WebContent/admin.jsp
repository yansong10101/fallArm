<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="listAllPatient.jsp" method="POST"><input type="submit" name"listallpatients" value="List all Patients" /></form>
	<form action="listAllNurse.jsp" method="POST"><input type="submit" name"listallnurses" value="List all Nurses" /></form>
	<form action="listAllViewer.jsp" method="POST"><input type="submit" name"listallviewers" value="List all Viewers" /></form>
	<form action="AdminAction" method="GET">
		</br><select name="patient_level">
				 		   	<option>1
				 		   	<option>2
				 		   	<option>3
			 </select>
		<input type="submit" name"listbygroup" value="List by patient level" />
	</form>
	<form action="adminAddNurse.jsp" method="POST"><input type="submit" name"add_nurse" value="add new nurse" /></form>
	<form action="adminAddPatient.jsp" method="POST"><input type="submit" name"add_patient" value="add new patient" /></form>
</body>
</html>