<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Sheet</title>
</head>
<body>
<% 
if(request.getParameter("logout")!=null) {
	session.invalidate();	
}
%>
<jsp:forward page="/loginDisplay.do"></jsp:forward>
</body>
</html>