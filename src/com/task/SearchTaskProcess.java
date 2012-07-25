package com.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.dao.TaskSheetDAO;

public class SearchTaskProcess extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target ="";
		TaskSheetForm taskSheetForm=(TaskSheetForm) form;
		TaskSheetDAO taskSheetDAO=new TaskSheetDAO();
		taskSheetDAO.populateEntry(taskSheetForm, taskSheetForm.getFromDate(),taskSheetForm.getToDate());
		taskSheetForm.getTaskList().remove(0);
		if(request.getParameter("reportTask")!=null) {
			target = "report";
		} else {
			target = "display";
		}
		return mapping.findForward(target);
	}
	
}
