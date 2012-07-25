<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:include page="header.jsp"></jsp:include>
<link href="css/styles.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
	$(function() {
		$("#fromDate").datepicker({
			showOn: 'button',
			buttonImage: 'images/calendar.gif',
			buttonImageOnly: true,
			changeMonth: true,
			dateFormat: 'yy-mm-dd'
		});
	});

	$(function() {
		$("#toDate").datepicker({
			showOn: 'button',
			buttonImage: 'images/calendar.gif',
			buttonImageOnly: true,
			changeMonth: true,
			dateFormat: 'yy-mm-dd'
		});
	});
	/* $(function() {
		var dates = $('#fromDate, #toDate').datepicker({
			showOn: 'button',
			buttonImage: 'images/calendar.gif',
			buttonImageOnly: true,
			dateFormat: 'yy-mm-dd',
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 1,
			onSelect: function(selectedDate) {
				var option = this.id == "fromDate" ? "minDate" : "maxDate";
				var instance = $(this).data("datepicker");
				var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
				dates.not(this).datepicker("option", option, date);
			}
		});
	});*/
	</script>
<br>
<font color="red" size="2"><html:errors/></font>
<br>

<a href="taskSheetDisplay.do" style="font-size:12px">Add</a>
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
<jsp:include page="footer.jsp"></jsp:include>