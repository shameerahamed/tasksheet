<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:include page="header.jsp"></jsp:include>
<link href="css/styles.css" type="text/css" rel="stylesheet"/>
<link href="css/styles.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="css/tablestyle.css" type="text/css"/>	
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	$(function() {
		$("#fromDate").datepicker({
			showOn: 'button',
			buttonImage: 'images/calendar.gif',
			buttonImageOnly: true,
			changeMonth: true,
			dateFormat: 'dd-M-y'
		});
	});

	$(function() {
		$("#toDate").datepicker({
			showOn: 'button',
			buttonImage: 'images/calendar.gif',
			buttonImageOnly: true,
			changeMonth: true,
			dateFormat: 'dd-M-y'
		});		
		//$("#main").tablesorter(); 
	});	

	$(document).ready(function(){
		$('table.tablesorter tr:odd').css('background-color','#DBFBF8');	
	});
	</script>
<br>
<font color="red" size="2"><html:errors/></font>
<br>

<a href="taskSheetDisplay.do" style="font-size:12px">Add</a>&nbsp;&nbsp;
<a href="searchTaskDisplay.do" style="font-size: 12px">Show All</a>
<html:form action="searchTaskProcess">
	<table width="100%">
		<tr>
			<td width="33%">From Date&nbsp;<span class="mand">*</span> 
				<html:text name="taskSheetForm" property="fromDate" styleId="fromDate"/>
			</td>
			<td width="33%">To Date&nbsp;<span class="mand">*</span> 
				<html:text name="taskSheetForm" property="toDate" styleId="toDate"/>
			</td>
			<td width="33%">
				<html:button property="searchTask" onclick="doSearch();">Search</html:button>&nbsp;&nbsp;&nbsp;
				<html:button property="reportTask" onclick="GenerateReport();">Report</html:button>				
			</td>
		</tr>
	</table>
	<%@include file="search.jsp" %>	
</html:form>
<br/><br/>
<jsp:include page="footer.jsp"></jsp:include>