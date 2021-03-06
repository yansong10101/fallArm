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
		int i_level = Integer.parseInt(request.getAttribute("patient_level").toString());
		ArrayList<Patient> oPatientList = DBUtils.GetPatientListByLevel(i_level);
		ArrayList<Nurse> oNurseList = DBUtils.GetNurseListByLevel(i_level);
	%>
		<h1>Nursing Group <%=i_level %></h1>
		
		<h2>Patients List</h2>
		<table border="1">
			<tr>
				<td>Patient ID</td>
				<td>Patient Name</td>
				<td>Patient Gender</td>
				<td>Patient BirthDay</td>
			</tr>
			<%
				for(int i = 0; i < oPatientList.size(); i++)
				{
					Patient oPatient = oPatientList.get(i);
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
		
		<h2>Nurse List</h2>
		<table border="1">
			<tr>
				<td>Nurse ID</td>
				<td>Nurse Name</td>
				<td>Nurse Gender</td>
			</tr>
			<%
				for(int i = 0; i < oNurseList.size(); i++)
				{
					Nurse oNurse = (Nurse)oNurseList.get(i);
					%>
						<tr>
							<td><%=oNurse.getNurse_id() %></td>
							<td><%=oNurse.getPerson_firstname() + " " + oNurse.getPerson_lastname() %></td>
							<td><%=oNurse.getPerson_gender() %></td>
						</tr>
					<%				
				}
			%>
		</table>
</body>
</html>