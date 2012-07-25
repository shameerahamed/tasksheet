 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 <logic:notPresent parameter="reportTask">
 	<link href="css/styles.css" type="text/css" rel="stylesheet"/>
 </logic:notPresent>
<br></br>
<table border="1" cellpadding="5" cellspacing="1" class="main" width="100%">		
    <tr class="row1">
		<th>Date&nbsp;</th>
		<th>Project Name&nbsp;</th>
		<th>Phase&nbsp;</th>
		<th>Module&nbsp;</th>
		<th>Activity&nbsp;</th>
		<th>Work Products&nbsp;</th>
		<th>Hour Spent&nbsp;</th>
		<th>Remarks</th>
	</tr>	
	<logic:iterate id="taskHolderId" indexId="indx"	type="com.task.TaskSheetHolder" property="taskList"	name="taskSheetForm">	
		<%--<logic:equal name="taskHolderId" property="isDeleted" value="false"> --%>
              <tr bgcolor="#FFFFFF">
			    <td>
			    	<logic:notPresent parameter="reportTask">			    				    	
						<html:link href="taskSheetDisplay.do" paramId="taskDate" paramName="taskHolderId" paramProperty="date"><bean:write name="taskHolderId" property="date" /></html:link>
					</logic:notPresent>
					<logic:present parameter="reportTask">
						<bean:write name="taskHolderId" property="date" />
					</logic:present>				    	
			    </td>
				<td><bean:write name="taskHolderId" property="projectName" /></td>
				<td><bean:write name="taskHolderId" property="phase" /></td>
				<td><bean:write name="taskHolderId" property="module" /></td>
				<td><bean:write name="taskHolderId" property="activity" /></td>
				<td><bean:write name="taskHolderId" property="workProducts" /></td>
				<td><bean:write name="taskHolderId" property="hourSpent" /></td>
				<td><bean:write name="taskHolderId" property="remarks" /></td>
			</tr>
		<%-- </logic:equal> --%>
	</logic:iterate>
</table>