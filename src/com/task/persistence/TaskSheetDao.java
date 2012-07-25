package com.task.persistence;

import java.util.List;

import com.task.LoginForm;
import com.task.TaskSheetForm;
import com.task.TaskSheetHolder;
import com.task.user.UserForm;

public interface TaskSheetDao {
	public List getAllTask();
	
	public List getTaskByUserId(Integer userID);
	
	//public List getTaskByDate(String date);
	
	public void insertTask(TaskSheetHolder holder);
	
	public void updateTask(TaskSheetHolder holder);
	
	public void deleteTask(int recid);
	
	
	public List getOptionList(String domain);
	
	public List getTaskBySearchDate(TaskSheetForm task);
	
	
	public LoginForm validateUser(LoginForm loginForm); 
}
