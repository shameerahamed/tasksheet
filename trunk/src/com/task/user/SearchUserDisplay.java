package com.task.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.user.dao.UserService;



public class SearchUserDisplay extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "display";
		UserForm userForm = (UserForm)form;
		UserService userService = new UserService();
		userForm.setUserList(userService.PopulateUser());		
		return mapping.findForward(target);
	}
}
