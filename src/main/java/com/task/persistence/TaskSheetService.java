package com.task.persistence;
import com.task.form.LoginForm;
import com.task.form.TaskSheetForm;

import com.task.form.TaskSheetHolder;
import com.task.user.holder.UserForm;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskSheetService {
	TaskSheetDao daoImpl = new TaskSheetDaoImpl();	
    public ArrayList getOptionValues(String domain) {       
         ArrayList optionList = new ArrayList();
        try {
        	optionList = (ArrayList) daoImpl.getOptionList(domain);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return optionList;
    }
    
    public void populateEntry(TaskSheetForm taskSheetForm, String fromDate, String toDate) {    	
    	try {
    		String s_where = "";
    		ArrayList taskList = new ArrayList();
    		if(fromDate!=null && toDate!=null) {
    			//s_where+=" and Date>='"+fromDate+"' and Date<='"+toDate+"'";
    			taskSheetForm.setFromDate(fromDate);
    			taskSheetForm.setToDate(toDate);
    			taskList = (ArrayList) daoImpl.getTaskBySearchDate(taskSheetForm);
    		}   
    		else {
    			taskList = (ArrayList) daoImpl.getTaskByUserId(taskSheetForm.getUserId());
    		}
    		    		
    		//String sql = "select * from App.tbl_task where user_id='"+taskSheetForm.getUserId()+"'"+s_where+" order by date";
    		Iterator it = taskList.iterator();    		
    		while(it.hasNext()) {
    			TaskSheetHolder holder = (TaskSheetHolder)it.next();
    			holder.setMode("SAVE");
    		}
    		
    		taskSheetForm.setTaskList(taskList);
    		TaskSheetHolder holder = new TaskSheetHolder();    				
    		taskSheetForm.getTaskList().add(0,holder);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public void createEntry(TaskSheetForm taskSheetForm) {
       
    	if(taskSheetForm.getTaskList()!=null || taskSheetForm.getTaskList().size()>1){
        	TaskSheetHolder holder;
            try {    
                
                for(int i=1;i<taskSheetForm.getTaskList().size();i++) {
                    holder = (TaskSheetHolder)taskSheetForm.getTaskList().get(i);
                    if(!holder.getIsDeleted() && holder.getRecordId()==0) {
                    	 //To Insert Records
                    	holder.setUserId(taskSheetForm.getUserId());
                    	holder.setDate(taskSheetForm.getDate());
                        daoImpl.insertTask(holder);
                    } else if(!holder.getIsDeleted() && holder.getRecordId()!=0) {
                    	holder.setDate(taskSheetForm.getDate());
                    	daoImpl.updateTask(holder);
                    } else if(holder.getIsDeleted() && holder.getRecordId()!=0) {
                    	daoImpl.deleteTask(holder.getRecordId());
                    }
                }                

            } catch(Exception e) {
                e.printStackTrace();              
            }
        }        
    }

	public LoginForm isValidUser(LoginForm loginForm) {
		try {
			return daoImpl.validateUser(loginForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginForm;
	}
	
	public void populateUserEntry(UserForm userForm) {
		
	}
}

