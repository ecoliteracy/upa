package com.upa.service;

import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.UserSalaryType;

public interface UserService {
	
	public UserValidationResult isValidUserPassword(AppUser l);
	public UserSalaryType getUserSalaryType(Integer userSeq);
	public String addUserSalaryType(UserSalaryType userSalaryType);
}
