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
		ArrayList<Viewer> list = DBUtils.GetAllViewer();		
	%>		
		<h2>Viewer List</h2>
		<table border="1">
			<tr>
				<td>Viewer Name</td>
				<td>Viewer email</td>
				<td>Viewer phone</td>
			</tr>
			<%
				for(int i = 0; i < list.size(); i++)
				{
					Viewer oViewer = (Viewer)list.get(i);
					if(oViewer.getViewer_email().equals("admin@admin.com"))
						continue;
					%>
						<tr>
							<td><%=oViewer.getPerson_firstname() + " " + oViewer.getPerson_lastname() %></td>
							<td><%=oViewer.getViewer_email() %></td>
							<td><%=oViewer.getView_phone() %></td>
						</tr>
					<%				
				}
			%>
		</table>
</body>
</html>