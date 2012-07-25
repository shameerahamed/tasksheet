<%@include file="../header.jsp" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
	<html:form action="searchUserProcess">
		<div align="center">	
			<fieldset>
				<legend>Filter</legend>
					<table width="70%" cellpadding="3" cellspacing="1" align="center">
						<tr>
							<td width="33%">Employee ID&nbsp; 
								<html:text name="userForm" property="empId" styleId="empId"/>
							</td>
							<td width="33%">Employee Name&nbsp;
								<html:text name="userForm" property="empName" styleId="empName"/>
							</td>
							<td width="33%">
								<html:button property="searchUser" onclick="doUserSearch();">Search</html:button>&nbsp;&nbsp;&nbsp;						
							</td>
						</tr>
					</table>
			</fieldset>
		</div>
	<br><br>
	<bean:size id="userCnt" name="userForm" property="userList"/>
	<table border="0" cellpadding="3" cellspacing="1" class="main" width="80%" align="center">  
		<tr class="row1">
			<th width="5%">S.No</th>
			<th width="18%">Employee Id</th>
			<th>Full Name</th>			
			<th>User Name</th>			
			<th width="20%">Action</th>
		</tr>
		<logic:equal value="0" name="userCnt">
			<tr bgcolor="#FFFFFF">
				<td colspan="5">Your search did not match any records</td>
			</tr>
		</logic:equal>		
		<logic:iterate id="userSearchId" name="userForm" property="userList" indexId="idx">			
			<tr bgcolor="#FFFFFF">
				<td align="center">${idx +1}</td>
				<td>
					<a href='searchUserProcess.do?update=true&id=<bean:write name="userSearchId" property="recId"/>'>
						<bean:write name="userSearchId" property="empId"/>
					</a>
				</td>
				<td><bean:write name="userSearchId" property="fullName"/> </td>
				<td><bean:write name="userSearchId" property="userName"/> </td>
				<td align="center">
					<logic:equal value="1" property="status" name="userSearchId">
						<html:button property="activate" value="Activate" disabled="true"></html:button>
					</logic:equal>
					<logic:notEqual value="1" property="status" name="userSearchId">
						<html:button property="activate" value="Activate" onclick="javascript:activateUser('${idx}')"></html:button>
					</logic:notEqual>
				 </td>
			</tr>
		</logic:iterate>
	</table>
	</html:form>
<jsp:include page="../footer.jsp"></jsp:include>