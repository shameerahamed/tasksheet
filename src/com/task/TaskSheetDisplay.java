package com.task;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.persistence.TaskSheetService;
import com.task.utils.DateUtils;

public class TaskSheetDisplay extends Action{	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TaskSheetForm taskSheetForm = (TaskSheetForm)form;
		String date;        
		
		//TaskSheetDAO taskSheetDAO = new TaskSheetDAO();
		TaskSheetService service = new TaskSheetService();
		
        /** To Populate dropdown values **/
        taskSheetForm.setProjectList(service.getOptionValues("Project"));
        taskSheetForm.setPhaseList(service.getOptionValues("Phase"));
        taskSheetForm.setModuleList(service.getOptionValues("Modules"));
        taskSheetForm.setActivityList(service.getOptionValues("Activity"));       
       
        
        if(request.getParameter("taskDate")!=null) {        	
        	date = request.getParameter("taskDate");
        } else {
        	date = DateUtils.getDate();
        }
        
        taskSheetForm.setDate(date);        
        service.populateEntry(taskSheetForm,taskSheetForm.getDate(),taskSheetForm.getDate());

		if(taskSheetForm.getTaskList()==null || taskSheetForm.getTaskList().size()==0) {
			TaskSheetHolder taskSheetHolder = new TaskSheetHolder();
			taskSheetHolder.setIsDeleted(false);
			ArrayList taskList = new ArrayList();
			taskList.add(taskSheetHolder);
			taskSheetForm.setTaskList(taskList);			
		}
		
		return mapping.findForward("display");
	}

}
