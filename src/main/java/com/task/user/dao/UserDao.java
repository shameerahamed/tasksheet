package com.task.user.dao;

import java.util.List;

import com.task.user.holder.UserForm;
import com.task.user.holder.UserHolder;

public interface UserDao {
	public List getAllUser() ;
	public List getUserBySearch(UserForm userForm);
	public void insertUser(UserHolder userHolder);
	public void updateUser(UserHolder userHolder);	
	public void deleteUser(int recId);	
}
