<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.task.utils.DateUtils"%>
 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
 <%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
 <% 
String filename = "TimeSheet.xls";
String attachment = "attachment;filename=\""+filename+"\"";
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition",attachment);

//response.setHeader("content-disposition","attachment;filename=\"tasksheet.xls\"");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Report Generation</title>
<style type="text/css">

table.main{
      background: #FFCF9C;   
      border: 1px solid #000000;  
}

.row1{ 
  color:#000000;
}


.row2{ 
  color:#000000;
}

body {
	font-size:11px;
}
</style>
</head>
<body>
<br>
<table cellpadding="4" border="0">
<tr>
	<td colspan="2"></td>
</tr>
<tr>
	<td>&nbsp;</td>
	<td><%@ include file="search.jsp" %></td>
</tr>
</table>
</body>
</html>