package com.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.task.dao.DateUtils;

public class TaskSheetForm extends ActionForm {
	private String date_;
	String fromDate_;
	String toDate_;
	String userId_;
    ArrayList taskList_;
    ArrayList projectList_;
    ArrayList phaseList_;
    ArrayList moduleList_;
    ArrayList activityList_;
    ArrayList timeList_ = new ArrayList();
    int removeindex_;
    int editindex_=0;
    
    
    public String getFromDate() {
		return fromDate_;
	}

	public void setFromDate(String fromDate) {
		fromDate_ = fromDate;
	}

	public String getToDate() {
		return toDate_;
	}

	public void setToDate(String toDate) {
		toDate_ = toDate;
	}

	public String getUserId() {
		return userId_;
	}

	public void setUserId(String userId) {
		userId_ = userId;
	}

	public TaskSheetForm() {
    	float i = 0;
    	while(i<=4){
    		timeList_.add(i);
    		i+=0.25;
    	}
    	System.out.println(timeList_);
	}
    
    public int getEditindex() {
		return editindex_;
	}

	public void setEditindex(int editindex) {
		editindex_ = editindex;
	}
	
	public String getDate() {
        return date_;
    }

    public void setDate(String date_) {
        this.date_ = date_;
    }
    
    public ArrayList getActivityList() {
        return activityList_;
    }

    public void setActivityList(ArrayList activityList) {
        this.activityList_ = activityList;
    }

    public ArrayList getModuleList() {
        return moduleList_;
    }

    public void setModuleList(ArrayList moduleList) {
        this.moduleList_ = moduleList;
    }

    public ArrayList getPhaseList() {
        return phaseList_;
    }

    public void setPhaseList(ArrayList phaseList) {
        this.phaseList_ = phaseList;
    }

    public ArrayList getTimeList() {
        return timeList_;
    }

    public void setTimeList(ArrayList timeList) {
        this.timeList_ = timeList;
    }

    public ArrayList getProjectList() {
        return projectList_;
    }

    public void setProjectList(ArrayList projectList) {
        this.projectList_ = projectList;
    }

    public ArrayList getTaskList() {
        return taskList_;
    }

    public void setTaskList(ArrayList taskList) {
        taskList_ = taskList;
    }

    public int getRemoveindex() {
        return removeindex_;
    }

    public void setRemoveindex(int removeindex) {
        removeindex_ = removeindex;
    }

    public void addTaskHolder() {
        TaskSheetHolder holder;
        holder = (TaskSheetHolder) getTaskList().remove(0);
        if (holder.getProjectName() != null) {
        	holder.setMode("SAVE");
        	getTaskList().add(holder);
        }
        holder = new TaskSheetHolder();
        holder.setIsDeleted(false);
        getTaskList().add(0, holder);
    }

    public void removeTaskHolder() {
    	TaskSheetHolder taskSheetHolder = (TaskSheetHolder) getTaskList().get(removeindex_);
    	taskSheetHolder.setIsDeleted(true);    	
    }

    public TaskSheetHolder getTaskHolderId(int index) {
        return (TaskSheetHolder) getTaskList().get(index);
    }
    
    //Added by Ramanathan
    
    public void editTask()
    {
    	TaskSheetHolder holder=(TaskSheetHolder) getTaskList().get(editindex_);
    	holder.setMode("ADD");
    }
    
    public void saveTask()
    {
    	TaskSheetHolder holder=(TaskSheetHolder) getTaskList().get(editindex_);
    	holder.setMode("SAVE");
    }
    
    public void cancelTask()
    {
    	TaskSheetHolder holder=(TaskSheetHolder) getTaskList().get(editindex_);
    	holder.setMode("SAVE");
    }
    
    public ActionErrors validate(ActionMapping mapping,
    		HttpServletRequest request) {
    
    	ActionErrors errors=new ActionErrors();
    	if(request.getParameter("searchTask")!=null)
		{
    		if(getFromDate().length()==0)
    		{
    			errors.add("fromDate",new ActionMessage("errors.required","From Date",false));
    		}
		
    		if(getToDate().length()==0)
    		{
    			errors.add("toDate",new ActionMessage("errors.required","To Date",false));
    		}
    		if(getFromDate().length()!=0 && getToDate().length()!=0)
    		{
    			if(DateUtils.isFuture(getFromDate()))
    			{
    				errors.add("futureDate",new ActionMessage("future.date","From Date",false));
    			}
    			if(DateUtils.isFuture(getToDate()))
    			{
    				errors.add("futureDate",new ActionMessage("future.date","To Date",false));
    			}
    			if(DateUtils.compareDate(getFromDate(), getToDate())>0)
    			{
    				errors.add("greaterDate",new ActionMessage("date.greater","From Date","To Date", false));
    			}
    		}
		}
    	if(request.getParameter("addEntry")!=null)
    	{
    		TaskSheetHolder holder=(TaskSheetHolder) getTaskList().get(0);
    		if(holder.getPhase().length()==0)
    		{
    			errors.add("phase",new ActionMessage("errors.required","Phase",false));
    		}
    		if(holder.getModule().length()==0)
    		{
    			errors.add("module",new ActionMessage("errors.required","Module",false));
    		}
    		if(holder.getActivity().length()==0)
    		{
    			errors.add("activity",new ActionMessage("errors.required","Activity",false));
    		}
    		if(holder.getWorkProducts().length()==0)
    		{
    			errors.add("workproducts",new ActionMessage("errors.required","Workproducts",false));
    		}
    		if(holder.getHourSpent()==0)
    		{
    			errors.add("hoursspent",new ActionMessage("errors.required","Hours spent",false));
    		}
    	}
    	return errors;
    }
}
