<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@include file="header.jsp" %>
<center>
<% if(request.getParameter("auth")!=null) { %>
	<font color="red" size="3">You have entered invalid username/password .<br/> Pls try again.</font>
<%} %>
<br/></center>
<table cellpadding="5" cellspacing="5" border="0" align="center" width="300px" style="border:1px solid #109284">
	<html:form action="/loginProcess" method="post">
	<tr>
		<td width="50%">Username</td>
		<td><html:text name="loginForm" property="username"/></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><html:password name="loginForm" property="password"/></td>
	</tr>	
	<tr>
		<td colspan="2" align="center">
			<html:submit>Login</html:submit>
		</td>
	</tr>
	</html:form>
</table>
<jsp:include page="footer.jsp"></jsp:include>