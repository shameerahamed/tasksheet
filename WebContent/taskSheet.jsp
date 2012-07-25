<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
 <%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@include file="header.jsp" %>
<script>
	$(function() {
		$("#date").datepicker({
			showOn: 'button',
			buttonImage: 'images/calendar.gif',
			buttonImageOnly: true,
			dateFormat: 'yy-mm-dd',
			showButtonPanel: true
		});
	});
	function removeHolder(indx) {

		if(window.confirm('Are you sure to delete the row?'))
		{
			document.forms[0].removeindex.value = indx;
			document.forms[0].action = 'taskSheetProcess.do?removeEntry=true';
			document.forms[0].submit();
		}
	}

	function edit(indx){
		document.forms[0].editindex.value=indx;
		document.forms[0].action='taskSheetProcess.do?editEntry=true';
		document.forms[0].submit();
	}

	function saveEntry(indx){
		document.forms[0].editindex.value=indx;
		document.forms[0].action='taskSheetProcess.do?save=true';
		document.forms[0].submit();
	}

	function cancelEntry(indx){
		document.forms[0].editindex.value=indx;
		document.forms[0].action='taskSheetProcess.do?cancel=true';
		document.forms[0].submit();
	}

	function addEntry() {
		document.forms[0].action='taskSheetProcess.do?addEntry=true';
		document.forms[0].submit();
	}
</script>
<br><br>
<font color="red" size="2"><html:errors/></font> 
<html:form action="taskSheetProcess">
   <html:hidden property="removeindex" value=""/>
   <html:hidden property="editindex" value=""/>
   <a href="searchTask.do" style="font-size:12px">Search</a><br><br>
   Date&nbsp;<span class="mand">*</span>
   <html:text name="taskSheetForm" property="date" styleId="date"/>       	           
      <table border="0" cellpadding="3" cellspacing="1" class="main" width="100%">        	
          <tr class="row1">
                  <th>Project Name&nbsp;<span class="mand">*</span></th>
                  
                  <th>Phase&nbsp;<span class="mand">*</span></th>
                  <th>Module&nbsp;<span class="mand">*</span></th>
                  <th>Activity&nbsp;<span class="mand">*</span></th>
                  <th>Work Products&nbsp;<span class="mand">*</span></th>
                  <th>Hour Spent&nbsp;<span class="mand">*</span></th>
                  <th>Remarks</th>
                  <th>Action</th>
              </tr>
              <logic:iterate id="taskHolderId" indexId="indx" type="com.task.TaskSheetHolder" property="taskList" name="taskSheetForm">
              	<logic:equal name="taskHolderId" property="isDeleted" value="false">                    
                   <logic:equal value="0" name="indx">
                       <tr class="row1">
                           <td>
                               <%--<html:text name="taskHolderId"  property="projectName" indexed="true"/>--%>
                               <html:select name="taskHolderId" property="projectName" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="projectList"/>
                               </html:select>
                           </td>	                         
                           <td>
                               <html:select name="taskHolderId" property="phase" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="phaseList"/>
                               </html:select>
                               <%--<html:textarea name="taskHolderId" property="phase"  indexed="true"/>--%>
                           </td>
                           <td>
                               <%-- <html:text name="taskHolderId" property="module"  indexed="true"/> --%>
                               <html:select name="taskHolderId" property="module" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="moduleList"/>
                               </html:select>
                           </td>
                           <td>
                               <%-- <html:text name="taskHolderId" property="activity"  indexed="true"/> --%>
                               <html:select name="taskHolderId" property="activity" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="activityList"/>
                               </html:select>
                           </td>
                           <td><html:text name="taskHolderId" property="workProducts" indexed="true"/></td>
                           <td>
                               <%--<html:text name="taskHolderId" property="hourSpent" indexed="true"/>--%>
                               <html:select name="taskHolderId" property="hourSpent" indexed="true">
                                   <html:options property="timeList"/>
                               </html:select>
                           </td>
                           <td><html:text name="taskHolderId" property="remarks" indexed="true"/></td>
                           <td><html:button property="ADD" onclick="addEntry()">Add</html:button></td>
                       </tr>
                   </logic:equal>
                   <logic:notEqual value="0" name="indx">
                   <logic:notEqual value="SAVE" name="taskHolderId" property="mode">
                   
                       <tr bgcolor="#FFFFFF" class="row2">                                                     
                           <td>	                                
                               <html:select name="taskHolderId" property="projectName" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="projectList"/>
                               </html:select>
                           </td>	                          
                           <td>
                               <html:select name="taskHolderId" property="phase" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="phaseList"/>
                               </html:select>	                                
                           </td>
                           <td>	                                
                               <html:select name="taskHolderId" property="module" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="moduleList"/>
                               </html:select>
                           </td>
                           <td>	                                
                               <html:select name="taskHolderId" property="activity" indexed="true">
                                   <html:option value="">--Please Select--</html:option>
                                   <html:options property="activityList"/>
                               </html:select>
                           </td>
                           <td><html:text name="taskHolderId" property="workProducts" indexed="true"/></td>
                           <td>	                                
                               <html:select name="taskHolderId" property="hourSpent" indexed="true">
                                   <html:options property="timeList"/>
                               </html:select>
                           </td>
                           <td><html:text name="taskHolderId" property="remarks" indexed="true"/></td>
                           <td>
                               <html:button property="save" onclick='<%="saveEntry("+indx+")"%>'>Save</html:button>
                               <html:button property="cancel" onclick='<%="cancelEntry("+indx+")"%>'>Cancel</html:button>
                           </td>
                       </tr>
                       </logic:notEqual>
                       <logic:equal value="SAVE" name="taskHolderId" property="mode">
                   
                       <tr bgcolor="#FFFFFF">                                                     
                           <td>	                                
                               <bean:write name="taskHolderId" property="projectName"/>
                           </td>	                           
                           <td>
                               <bean:write name="taskHolderId" property="phase"/>
                           </td>
                           <td>	                                
                               <bean:write name="taskHolderId" property="module"/>
                           </td>
                           <td>
                               <bean:write name="taskHolderId" property="activity"/>	                                    
                           </td>
                           <td><bean:write name="taskHolderId" property="workProducts"/></td>
                           <td>	                                
                               <bean:write name="taskHolderId" property="hourSpent"/>	                                    
                           </td>
                           <td><bean:write name="taskHolderId" property="remarks"/></td>
                           <td>
                               <html:button property="editEntry" onclick='<%="edit("+indx+")"%>'>Edit</html:button>
                               <html:button property="removeEntry" onclick='<%="removeHolder("+ indx +")"%>'>Delete</html:button>
                           </td>	                            
                       </tr>
                       </logic:equal>
                   </logic:notEqual>
                  </logic:equal>
              </logic:iterate>
      </table>
      <html:submit property="submitForm"></html:submit>
      </html:form>
<jsp:include page="footer.jsp"></jsp:include>