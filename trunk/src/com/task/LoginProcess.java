package com.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.dao.TaskSheetDAO;

public class LoginProcess extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginForm loginForm = (LoginForm) form;
		String target = "failure";
		TaskSheetDAO taskSheetDAO = new TaskSheetDAO();
		if(taskSheetDAO.isValidUser(loginForm)) {
			request.getSession().setAttribute("Uname", loginForm.getUsername());
			target = "success";
		}
		
		/*if(loginForm.getUsername().equals("admin") && loginForm.getPassword().equals("123")) {
			request.getSession().setAttribute("Uname", loginForm.getUsername());
			target = "success";
		}*/
		return mapping.findForward(target);
	}
}
