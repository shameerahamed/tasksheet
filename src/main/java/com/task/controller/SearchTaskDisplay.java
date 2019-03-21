package com.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.task.form.TaskSheetForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.persistence.TaskSheetService;

public class SearchTaskDisplay extends Action{
	Logger logger = Logger.getLogger(this.getClass());	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "error";
		TaskSheetForm taskSheetForm=(TaskSheetForm) form;
		if(request.getSession().getAttribute("Uname")!=null) {
			taskSheetForm.setUserId((Integer) request.getSession().getAttribute("userID"));
			
			 //Added for pagination
	        taskSheetForm.setPageNo(1);
	        taskSheetForm.setOffset(10);	       
	        taskSheetForm.setFormId("taskSheetForm");  
	        
	        
			//TaskSheetDAO taskSheetDAO=new TaskSheetDAO();
			TaskSheetService service = new TaskSheetService();			
			service.populateEntry(taskSheetForm, null,null);
			taskSheetForm.getTaskList().remove(0);
			target = "display";		
			logger.info((request.getSession().getAttribute("loginForm")));
			taskSheetForm.setFromDate(null);
			taskSheetForm.setToDate(null);			
		}
		return mapping.findForward(target); 
	}
	
}
