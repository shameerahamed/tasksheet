package com.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.dao.TaskSheetDAO;

public class TaskSheetProcess extends Action{	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TaskSheetForm taskSheetForm = (TaskSheetForm)form;
		TaskSheetDAO taskSheetDAO = new TaskSheetDAO();
		if(request.getParameter("addEntry")!=null){
			taskSheetForm.addTaskHolder();
		} if(request.getParameter("removeEntry")!=null) {
			taskSheetForm.removeTaskHolder();
		}
		
		//Added by Ramanathan
		if(request.getParameter("editEntry")!=null)
		{
			taskSheetForm.editTask();
		}
		if(request.getParameter("save")!=null)
		{
			taskSheetForm.saveTask();
		}
		if(request.getParameter("cancel")!=null)
		{
			taskSheetForm.cancelTask();
		}
		if(request.getParameter("submitForm")!=null) {
			taskSheetDAO.createEntry(taskSheetForm);
		}
		return mapping.findForward("display");
	}
}
