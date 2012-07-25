package com.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.dao.TaskSheetDAO;

public class SearchTaskDisplay extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "error";
		TaskSheetForm taskSheetForm=(TaskSheetForm) form;
		if(request.getSession().getAttribute("Uname")!=null) {
			taskSheetForm.setUserId(request.getSession().getAttribute("Uname").toString());
			TaskSheetDAO taskSheetDAO=new TaskSheetDAO();
			taskSheetDAO.populateEntry(taskSheetForm, null,null);
			taskSheetForm.getTaskList().remove(0);
			target = "display";
		}
		return mapping.findForward(target); 
	}
	
}
