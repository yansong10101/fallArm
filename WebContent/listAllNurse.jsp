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
		ArrayList<Nurse> list = DBUtils.GetAllNurse();		
	%>		
		<h2>Nurse List</h2>
		<table border="1">
			<tr>
				<td>Nurse ID</td>
				<td>Nurse Name</td>
				<td>Nurse Gender</td>
			</tr>
			<%
				for(int i = 0; i < list.size(); i++)
				{
					Nurse oNurse = (Nurse)list.get(i);
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