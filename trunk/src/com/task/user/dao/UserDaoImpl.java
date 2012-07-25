package com.task.user.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.task.persistence.BaseDao;
import com.task.user.UserForm;
import com.task.user.UserHolder;

public class UserDaoImpl extends BaseDao implements UserDao{
	Logger logger = Logger.getLogger(this.getClass());
	
	public List getAllUser() {
		List userList = null;
		try {
			userList = sqlMap.queryForList("User.userList"); 
		} catch (Exception e) {
			logger.error("Error in fetching details",e);
		}
		return userList;		
	}

	public void insertUser(UserHolder userHolder) {
		try {
			sqlMap.insert("User.insertUser", userHolder);
		} catch (Exception e) {
			logger.error("Error in inserting user record "+e);
		}
	}

	public void updateUser(UserHolder userHolder) {
		try {
			sqlMap.update("User.updateUser",userHolder);			
		} catch (Exception e) {
			logger.error("Error in updating user records"+e);
		}		
	}

	public void deleteUser(int recId) {
		try {
			sqlMap.delete("User.deleteUser",recId);
		} catch (Exception e) {
			logger.error("Error in deleting user records"+e);
		}		
	}

	public List getUserBySearch(UserForm userForm) {
		List userList = null;
		try {
			userList = sqlMap.queryForList("User.getUserBySearch",userForm);
		} catch (Exception e) {
			logger.error("Error in Fetching search details "+e);
		}
		return userList;		
	}
}
