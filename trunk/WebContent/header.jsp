<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Sheet</title>
<script src="js/functions.js" type="text/javascript"></script>
<link href="css/styles.css" type="text/css" rel="stylesheet"/>
<link REL="SHORTCUT ICON" HREF="images/favicon.ico">
<link type="text/css" href="css/theme/jquery.ui.all.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/ui/jquery.ui.datepicker.js"></script>
<link type="text/css" href="css/demos.css" rel="stylesheet" />
</head>
<% String username = null;
if(session.getAttribute("Uname")!=null ){
	username =session.getAttribute("Uname").toString(); 
}
%>
<body style="font-size:11px;">
<p align="right">
	<% if(username != null) {%>	
	Welcome <%=username %>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="index.jsp?logout=true">Log Out</a>
	<%} %>
</p>
<center>
<h3>Task Tracker</h3>
</center>