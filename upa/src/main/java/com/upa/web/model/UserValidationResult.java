package com.upa.web.model;

import com.upa.web.model.entity.AppUser;

public class UserValidationResult {
	
	String validationResult;
	AppUser appuser;
	
	
	public String getValidationResult() {
		return validationResult;
	}
	public void setValidationResult(String validationResult) {
		this.validationResult = validationResult;
	}
	public AppUser getAppuser() {
		return appuser;
	}
	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

}