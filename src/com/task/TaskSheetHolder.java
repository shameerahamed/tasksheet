package com.task;

import java.io.Serializable;

public class TaskSheetHolder implements Serializable{	
	private static final long serialVersionUID = 1L;
	
    private int recordId_;
    private String projectName_;
    private String date_;
    private String phase_;
    private String module_;
    private String activity_;
    private String workProducts_;
    private float hourSpent_;
    private String remarks_;
    private boolean isDeleted_;
    private Integer userId_;
    
    //Added for Ibatis integration
    
    public Integer getUserId() {
		return userId_;
	}

	public void setUserId(Integer userId) {
		userId_ = userId;
	}
    
    //Added by Ramanathan   

	private String mode_;

    public String getMode() {
		return mode_;
	}

	public void setMode(String mode) {
		mode_ = mode;
	}

	// End of Ramanathan's code 
	
	public boolean getIsDeleted() {
		return isDeleted_;
	}

	public void setIsDeleted(boolean isDeleted) {
		isDeleted_ = isDeleted;
	}

	public String getActivity() {
        return activity_;
    }

    public void setActivity(String activity_) {
        this.activity_ = activity_;
    }

    public String getDate() {
        return date_;
    }

    public void setDate(String date_) {
        this.date_ = date_;
    }

    public float getHourSpent() {
        return hourSpent_;
    }

    public void setHourSpent(float hourSpent_) {
        this.hourSpent_ = hourSpent_;
    }

    public String getModule() {
        return module_;
    }

    public void setModule(String module_) {
        this.module_ = module_;
    }

    public String getPhase() {
        return phase_;
    }

    public void setPhase(String phase_) {
        this.phase_ = phase_;
    }

    public String getProjectName() {
        return projectName_;
    }

    public void setProjectName(String projectName_) {
        this.projectName_ = projectName_;
    }

    public int getRecordId() {
        return recordId_;
    }

    public void setRecordId(int recordId_) {
        this.recordId_ = recordId_;
    }

    public String getRemarks() {
        return remarks_;
    }

    public void setRemarks(String remarks_) {
        this.remarks_ = remarks_;
    }

    public String getWorkProducts() {
        return workProducts_;
    }

    public void setWorkProducts(String workProducts_) {
        this.workProducts_ = workProducts_;
    }
	
	
	
	
}
