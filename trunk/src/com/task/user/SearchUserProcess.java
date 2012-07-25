package com.task.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.task.user.dao.UserService;

public class SearchUserProcess extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Logger logger = Logger.getLogger(this.getClass());
		String target = "display";
		try {			
			UserForm userForm = (UserForm) form;		
			UserService service = new UserService();
			if(request.getParameter("searchUser")!=null) {
				service.PopulateUser(userForm);
			} else if(request.getParameter("activateUser")!=null) {			
				Integer idx =Integer.parseInt(request.getParameter("idx"));
				UserHolder userHolder = (UserHolder) userForm.getUserList().get(idx);
				userHolder.setStatus(1);
				service.updateUser(userHolder);
			}
		} catch (Exception e) {
			logger.error("Error in processing the search terms : "+e);
		}
		return mapping.findForward(target);
	}
}
