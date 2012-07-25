package com.task.dao;

import com.task.LoginForm;
import com.task.TaskSheetForm;

import com.task.TaskSheetHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskSheetDAO {

    public ArrayList getOptionValues(String domain) {
        // Class.forName("com.task.dao.ConnectionHelper");
        //ConnectionHelper connectionHelper = new ConnectionHelper();
         ArrayList optionList = new ArrayList();
        try {
            Connection con = ConnectionHelper.getConnection();
            Statement st = con.createStatement();
            ResultSet rs;           
            String qry = "select * from App.tbl_picklist where section = '"+domain+"'";
            rs = st.executeQuery(qry);
            while (rs.next()) {
                optionList.add(rs.getString("option_name"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return optionList;
    }
    
    public void populateEntry(TaskSheetForm taskSheetForm, String fromDate, String toDate) {
    	try {
    		Connection con = ConnectionHelper.getConnection();
    		String s_where = "";
    		if(fromDate!=null && toDate!=null)
    			s_where+=" and Date>='"+fromDate+"' and Date<='"+toDate+"'";
    		String sql = "select * from App.tbl_task where user_id='"+taskSheetForm.getUserId()+"'"+s_where+" order by date";
    		Statement st = con.createStatement();
    		ResultSet rs = st.executeQuery(sql);
    		
    		ArrayList taskList = new ArrayList();    		
    		while(rs.next()) {
    			TaskSheetHolder holder = new TaskSheetHolder();
    			holder.setRecordId(rs.getInt("id"));
    			holder.setProjectName(rs.getString("Project_Name"));
    			taskSheetForm.setDate(rs.getString("Date"));
				holder.setDate(rs.getString("Date"));
    			holder.setPhase(rs.getString("Phase"));
    			holder.setModule(rs.getString("Module"));
    			holder.setActivity(rs.getString("Activity"));
    			holder.setWorkProducts(rs.getString("Work_Product"));
    			holder.setHourSpent(rs.getFloat("Hour_Spent"));
    			holder.setRemarks(rs.getString("Remarks"));
    			holder.setMode("SAVE");
    			taskList.add(holder);
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
                Connection con = ConnectionHelper.getConnection();
                /*String sql = "insert into App.tbl_task "
                              + "(Project_Name,Date,Phase,Module,Activity,Work_Product,Hour_Spent,Remarks) "
                              + " values (?,?,?,?,?,?,?,?)";*/
                //To Insert Records
                
                String sql = "insert into App.tbl_task "
                    + "(Project_Name,Date,Phase,Module,Activity,Work_Product,Hour_Spent,Remarks,user_id) "
                    + " values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ipst = con.prepareStatement(sql);
                
                //To update Records
                
                String update_sql = "update App.tbl_task set Project_Name=?,Date=?,Phase=?,Module=?,"
                	+ "Activity=?,Work_Product=?,Hour_Spent=?,Remarks=? where id = ?";
                PreparedStatement upst = con.prepareStatement(update_sql);
                
                //To Delete Records
                String delete_sql = "delete from App.tbl_task where id = ?";
                PreparedStatement dpst = con.prepareStatement(delete_sql);                
                
                for(int i=1;i<taskSheetForm.getTaskList().size();i++) {
                    holder = (TaskSheetHolder)taskSheetForm.getTaskList().get(i);
                    if(!holder.getIsDeleted() && holder.getRecordId()==0) {
	                	ipst.setString(1, holder.getProjectName());
	                	ipst.setString(2, taskSheetForm.getDate());
	                	ipst.setString(3, holder.getPhase());
	                	ipst.setString(4, holder.getModule());
	                	ipst.setString(5, holder.getActivity());
	                	ipst.setString(6, holder.getWorkProducts());
	                	ipst.setFloat(7, holder.getHourSpent());
	                	ipst.setString(8, holder.getRemarks());
	                	ipst.setString(9, taskSheetForm.getUserId());
	                	/*ipst.setString(2, holder.getPhase());
	                	ipst.setString(3, holder.getModule());
	                	ipst.setString(4, holder.getActivity());
	                	ipst.setString(5, holder.getWorkProducts());
	                	ipst.setFloat(6, holder.getHourSpent());
	                	ipst.setString(7, holder.getRemarks());*/
	                	ipst.addBatch();
                    } else if(!holder.getIsDeleted() && holder.getRecordId()!=0) {
                    	upst.setString(1, holder.getProjectName());
	                	upst.setString(2, taskSheetForm.getDate());
	                	upst.setString(3, holder.getPhase());
	                	upst.setString(4, holder.getModule());
	                	upst.setString(5, holder.getActivity());
	                	upst.setString(6, holder.getWorkProducts());
	                	upst.setFloat(7, holder.getHourSpent());
	                	upst.setString(8, holder.getRemarks());
	                	upst.setInt(9, holder.getRecordId());
	                	/*upst.setString(2, holder.getPhase());
	                	upst.setString(3, holder.getModule());
	                	upst.setString(4, holder.getActivity());
	                	upst.setString(5, holder.getWorkProducts());
	                	upst.setFloat(6, holder.getHourSpent());
	                	upst.setString(7, holder.getRemarks());
	                	upst.setInt(8, holder.getRecordId());*/
	                	upst.addBatch();
                    } else if(holder.getIsDeleted() && holder.getRecordId()!=0) {
                    	dpst.setInt(1, holder.getRecordId());
                    	dpst.addBatch();
                    }
                }
                
                // To insert Record
                int[] updateCounts = ipst.executeBatch();
                checkUpdateCounts(updateCounts);
                
                //To update Record
                upst.executeBatch();
                
                //To Delete record
                dpst.executeBatch();     

            } catch(Exception e) {
                e.printStackTrace();              
            }
        }        
    }

	private void checkUpdateCounts(int[] updateCounts) {
		 for (int i=0; i<updateCounts.length; i++) {
		        if (updateCounts[i] >= 0) {
		            System.out.println("OK; updateCount="+updateCounts[i]);
		        }
		        else if (updateCounts[i] == Statement.SUCCESS_NO_INFO) {
		            System.out.println("OK; updateCount=Statement.SUCCESS_NO_INFO");
		        }
		        else if (updateCounts[i] == Statement.EXECUTE_FAILED) {
		            System.out.println("Failure; updateCount=Statement.EXECUTE_FAILED");
		        }
		    }
		
	}
	
	public boolean isValidUser(LoginForm loginForm) {
		try {
			Connection con = ConnectionHelper.getConnection();
			String sql = "select * from APP.tbl_user where user_name = ? and password = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, loginForm.getUsername());
			pst.setString(2, loginForm.getPassword());
			ResultSet rs = pst.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
