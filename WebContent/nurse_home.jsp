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
		Nurse oNurse = (Nurse)request.getAttribute("nurse");
		if(oNurse == null)
		{
	%>
		<p>failed to get this nurse!</p>
	<%
		}else{
	%>
		<h2>profile:</h2><p><%=oNurse.toString() %></p>
		<h2>Patients in group</h2>
		<table border="1">
			<tr>
				<td>Patient ID</td>
				<td>Patient Name</td>
				<td>Patient Gender</td>
				<td>Patient BirthDay</td>
			</tr>
			<%
				ArrayList<Patient> list = DBUtils.GetPatientListByLevel(oNurse.getPatient_level());
				for(int i = 0; i < list.size(); i++)
				{
					Patient oPatient = list.get(i);
					%>
						<tr>
							<td><%=oPatient.getPatient_id() %></td>
							<td><%=oPatient.getPerson_firstname() + " " + oPatient.getPerson_lastname() %></td>
							<td><%=oPatient.getPerson_gender() %></td>
							<td><%=oPatient.getPerson_birth().toString() %></td>
						</tr>
					<%				
				}
			%>
		</table>
	<%
		}
	%>
</body>
</html>