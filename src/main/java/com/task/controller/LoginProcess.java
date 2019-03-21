package com.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.task.form.LoginForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.persistence.*;

public class LoginProcess extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginForm loginForm = (LoginForm) form;
		String target = "failure";

		// TaskSheetDAO taskSheetDAO = new TaskSheetDAO();
		TaskSheetService service = new TaskSheetService();
		loginForm = service.isValidUser(loginForm);
		if (loginForm != null) {
			Integer userID = loginForm.getUserID();
			if (userID != null) {
				if(loginForm.getStatus()==1) {
					request.getSession().setAttribute("loginForm", loginForm);
					request.getSession().setAttribute("userID", userID);
					request.getSession().setAttribute("Uname",loginForm.getUsername());
					if(userID == 1) {
						target = "admin";
					} else {
						target = "success";
					}
				}
				else {
					target = "inactive";
					request.getSession().removeAttribute("loginForm");
				}
			}
			
			
		}

		/*
		 * if(loginForm.getUsername().equals("admin") &&
		 * loginForm.getPassword().equals("123")) {
		 * request.getSession().setAttribute("Uname", loginForm.getUsername());
		 * target = "success"; }
		 */
		return mapping.findForward(target);
	}
}
