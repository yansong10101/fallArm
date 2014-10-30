<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@page import="fallArmDB.*" import="config.*" import="fallArmAction.*" import="java.util.*"%>
<title>Insert title here</title>
</head>
<body>
	<% 
		Viewer oViewer = (Viewer)request.getAttribute("viewer");
		if(oViewer == null)
		{
	%>
		<p>failed to get this nurse!</p>
	<%
		}else{
			Patient oPatient = Patient.GetPatient(oViewer.getPatient_id());
	%>
		<h2>profile:</h2><p><%=oViewer.toString() %></p>
		<h2>Patient in group</h2>
		<table border="1">
			<tr>
				<td>Patient ID</td>
				<td>Patient Name</td>
				<td>Patient Gender</td>
				<td>Patient BirthDay</td>
			</tr>
			<tr>
				<td><%=oPatient.getPatient_id() %></td>
				<td><%=oPatient.getPerson_firstname() + " " + oPatient.getPerson_lastname() %></td>
				<td><%=oPatient.getPerson_gender() %></td>
				<td><%=oPatient.getPerson_birth().toString() %></td>
			</tr>
		</table>
	<%
		}
	%>
</body>
</html>