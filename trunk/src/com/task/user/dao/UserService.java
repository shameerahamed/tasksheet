package com.task.user.dao;

import java.util.ArrayList;

import com.task.user.UserForm;
import com.task.user.UserHolder;

public class UserService {
	UserDao userDao = new UserDaoImpl();
	public ArrayList PopulateUser() {
		return (ArrayList) userDao.getAllUser();		
	}
	
	
	public void PopulateUser(UserForm userForm) {
		ArrayList userList = (ArrayList) userDao.getUserBySearch(userForm);
		userForm.setUserList(userList);
	}
	
	public void createUser(UserHolder userHolder) {
		userDao.insertUser(userHolder);		
	}
	
	public void updateUser(UserHolder userHolder) {
		userDao.updateUser(userHolder);
	}
	
	public void deleteUser(int recId) {
		userDao.deleteUser(recId);
	}
}
