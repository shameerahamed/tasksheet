package com.task.user.holder;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author sham1778
 *
 */
public class UserForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List userList_;
	String empId;
	String empName;

	public List getUserList() {
		return userList_;
	}

	public void setUserList(List userList) {
		userList_ = userList;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
}
