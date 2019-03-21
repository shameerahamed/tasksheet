package com.task.persistence;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.task.form.LoginForm;
import com.task.form.TaskSheetForm;
import com.task.form.TaskSheetHolder;

public class TaskSheetDaoImpl  extends BaseDao implements TaskSheetDao{
	Log logger = LogFactory.getLog(this.getClass());
	public List getAllTask() {
		List taskList = null;
		try {
			taskList = sqlMap.queryForList("Tasksheet.get");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taskList;
	}

	public List getTaskByUserId(Integer userID) {
		List taskList = null;
		try {
			taskList = sqlMap.queryForList("TaskSheet.getByUserId",userID);
		} catch (SQLException e) {
			 logger.error("Error getting Task ", e);
		}
		return taskList;
	}

	public List getTaskByDate(TaskSheetForm task) {
		List taskList = null;
		try {
			taskList = sqlMap.queryForList("TaskSheet.getByDate",task);
		} catch (SQLException e) {
			logger.error("Error in getting records",e);
		}
		return taskList; 
	}
	
	public List getTaskBySearchDate(TaskSheetForm task) {
		List taskList = null;
		try {
			taskList = sqlMap.queryForList("TaskSheet.getBySearchDate",task);
		} catch (Exception e) {
			logger.error("Error in fetching records",e);
		}
		return taskList;
	}

	public void insertTask(TaskSheetHolder holder) {
		try {
			sqlMap.insert("TaskSheet.insertTask",holder);
		} catch (SQLException e) {
			logger.error("Error in inserting records",e);
		}
		
	}

	public void updateTask(TaskSheetHolder holder) {
		try {
			sqlMap.update("TaskSheet.updateTask",holder);
		} catch (SQLException e) {
			logger.error("Error in updating records", e);
		}
		
	}

	public void deleteTask(int recid) {
		try {
			sqlMap.delete("TaskSheet.deleteTask", recid);
		} catch (SQLException e) {
			logger.error("Error in deleting records", e);
		}
	}

	public List getOptionList(String domain) {
		List optionList = null;
		try {
			optionList = sqlMap.queryForList("TaskSheet.getOptionBySection",domain);
		} catch (Exception e) {
			logger.error("Error in fetching Option List", e);
		}
		return optionList;
	}

	public LoginForm validateUser(LoginForm loginForm) {
		//boolean valid = false;		
		try {
			loginForm = (LoginForm) sqlMap.queryForObject("TaskSheet.validateUser",loginForm);
			//userID =  (Integer) loginForm.getUserID();
		} catch (Exception e) {
			logger.error("Error in fetching records from DB", e);
		}
		/*if(userID!=null) { 
			//valid = true;	
			return userID;
		}*/
		return loginForm;
	}
}
