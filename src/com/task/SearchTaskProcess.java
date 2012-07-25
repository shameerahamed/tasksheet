package com.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.dao.TaskSheetDAO;
import com.task.persistence.TaskSheetService;

public class SearchTaskProcess extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target ="";
		TaskSheetForm taskSheetForm=(TaskSheetForm) form;
		//TaskSheetDAO taskSheetDAO=new TaskSheetDAO();
		TaskSheetService service = new TaskSheetService();
		service.populateEntry(taskSheetForm, taskSheetForm.getFromDate(),taskSheetForm.getToDate());
		taskSheetForm.getTaskList().remove(0);
		
		if(request.getParameter("pageNo")!=null && request.getParameter("offset")!=null) {
			taskSheetForm.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			taskSheetForm.setOffset(Integer.parseInt(request.getParameter("offset")));
		}
		
		if(request.getParameter("searchTask")!=null) {
			 //Added for pagination
	        taskSheetForm.setPageNo(1);
	        taskSheetForm.setOffset(10);	
		}
		
		if(request.getParameter("reportTask")!=null) {
			target = "report";
		} else if(request.getParameter("paging")!=null) {
			target = "paging";
		} else {
			target = "display";
		}
		return mapping.findForward(target);
	}
	
}
