 <%@page isELIgnored="false" %>
 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 <%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
 <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<div id="maintbl">
<table class="tablesorter" cellpadding="5" cellspacing="1"  id="main" width="100%">	
    <tr class="row1">
    	<logic:present parameter="reportTask"> 
			<th>Project Name&nbsp;</th>
			<th>Date&nbsp;</th>			
		</logic:present>
		<logic:notPresent parameter="reportTask">
			<th>Date&nbsp;</th>
			<th>Project Name&nbsp;</th>
		</logic:notPresent>
		<th>Phase&nbsp;</th>
		<th>Module&nbsp;</th>
		<th>Activity&nbsp;</th>
		<th>Work Products&nbsp;</th>
		<th>Hour Spent&nbsp;</th>
		<logic:present parameter="reportTask">
			<th>Remarks</th>
		</logic:present>
	</tr>	
	<logic:iterate id="taskHolderId" indexId="indx"	type="com.task.form.TaskSheetHolder" property="taskList"	name="taskSheetForm" length="${taskSheetForm.offset}" offset="${(taskSheetForm.pageNo-1)*taskSheetForm.offset}">
              <tr>
		    	<logic:notPresent parameter="reportTask">
		    		<td><html:link href="taskSheetDisplay.do" paramId="taskDate" paramName="taskHolderId" paramProperty="date"><bean:write name="taskHolderId" property="date" /></html:link></td>
		    		<td><bean:write name="taskHolderId" property="projectName" /></td>											
				</logic:notPresent>
				<logic:present parameter="reportTask">
					<td><bean:write name="taskHolderId" property="projectName" /></td>
					<td><bean:write name="taskHolderId" property="date" /></td>					
				</logic:present> 
				<td><bean:write name="taskHolderId" property="phase" /></td>
				<td><bean:write name="taskHolderId" property="module" /></td>
				<td><bean:write name="taskHolderId" property="activity" /></td>
				<td><bean:write name="taskHolderId" property="workProducts" /></td>
				<td><bean:write name="taskHolderId" property="hourSpent" /></td>
				<logic:present parameter="reportTask">
					<td><bean:write name="taskHolderId" property="remarks" /></td>
				</logic:present>
			</tr>		
	</logic:iterate>	
</table>

<!-- For Pagination -->

<bean:define id="pageNo" value="${taskSheetForm.pageNo}"/>
<bean:define id="maxPage" value="${taskSheetForm.maxPage}"/>
<bean:define id="offset" value="${taskSheetForm.offset}"/>
<bean:define id="formId" value="${taskSheetForm.formId}"/>
<bean:define id="maxRec" value="${taskSheetForm.maxRecord}"/>

<logic:lessThan name="maxPage" value="${startPage+10}">
	<logic:greaterEqual name="maxPage" value="10">
		<bean:define id="startPage" value="${maxPage - 9}"></bean:define>
	</logic:greaterEqual>
</logic:lessThan>
<logic:greaterThan name="maxPage" value="${startPage+10}">
	<bean:define id="startPage" value="${pageNo}"/>
</logic:greaterThan>

<logic:greaterEqual name="maxPage" value="10">
	<bean:define id="endPage" value="${startPage+10}"/>
</logic:greaterEqual>
<logic:lessEqual name="maxPage" value="10">
	<bean:define id="endPage" value="${maxPage+1}"/>
	<bean:define id="startPage" value="1"></bean:define>
</logic:lessEqual>
<table width="99%" cellpadding="0" cellspacing="0" border="0">
	<tr>	
		<td align="left">
			<!-- Items Display -->
			<bean:define id="startRec" value="${(pageNo-1)*offset}"/>
			<logic:greaterThan name="maxRec" value="${startRec+offset}">
				<bean:define id="endRec" value="${startRec+offset}"/>
			</logic:greaterThan>
			<logic:lessThan name="maxRec" value="${startRec+offset}">
				<bean:define id="endRec" value="${maxRec}"></bean:define>	
			</logic:lessThan>
			Items ${startRec+1}-${endRec} of ${maxRec}
			
			<%--<html:select name="taskSheetForm" property="offset" styleId="offset" onchange="javascript:return paging('empForm','${pageNo}')">
				<html:option value="10">10 Per Page</html:option>
				<html:option value="20">20 Per Page</html:option>
			</html:select> --%>
			</td>
			<td align="right">           
			<!-- Prev Link -->
			<logic:equal name="pageNo" value="1">
				<input type="button" alt="first" class='first' onclick='return false;'>&nbsp;
				<input type="button" alt="prev" class='prev' onclick='return false;'>&nbsp;
			</logic:equal>
			<logic:notEqual value="1" name="pageNo">
                <input type="button" alt="first" class='first' onclick="javascript:return paging(document.getElementById('${formId}'),'1')">
                &nbsp;
				<input type="button" alt="prev" class='prev'  onclick="javascript:return paging(document.getElementById('${formId}'),'${pageNo - 1}')">
            </logic:notEqual>
			
			<html:text name="taskSheetForm" property="pageNo" style="width:40px" styleId="pageNo" onchange="javascript:return paging(document.getElementById('${formId}'),this.value)"/>
			&nbsp;of&nbsp;${maxPage}
			
			<!-- Next Link -->
			<logic:equal name="pageNo" value="${maxPage}">	
				<input type="button" alt="next" class='next' onclick='return false;'>&nbsp;
				<input type="button" alt="last" class='last' onclick='return false;'>
			</logic:equal>
			
			<logic:notEqual name="pageNo" value="${maxPage}">	
				<input type="button" alt="next" class='next'
					onclick="javascript:return paging(document.getElementById('${formId}'),'${pageNo + 1}')">&nbsp;
				<input type="button" alt="last" class='last'
					onclick="javascript:return paging(document.getElementById('${formId}'),'${maxPage}')">
			</logic:notEqual>            
		</td>
	</tr>
</table>
</div>
<script>
var maxPage = ${maxPage}; //get max page from Page
</script>