<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<tiles:importAttribute name="taskSheetForm"/>
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
			
			<html:select name="taskSheetForm" property="offset" styleId="offset" onchange="javascript:return paging('empForm','${pageNo}')">
				<html:option value="10">10 Per Page</html:option>
				<html:option value="20">20 Per Page</html:option>
			</html:select>
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
				<input type="button" alt="prev" class='prev' onclick="javascript:return paging(document.getElementById('${formId}'),'${pageNo - 1}')">
            </logic:notEqual>
			
			<html:text name="taskSheetForm" property="pageNo" styleId="pageNo" onchange="javascript:return paging('empForm',this.value)"/>
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