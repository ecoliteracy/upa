package com.upa.service;

import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;

public interface UserService {
	
	public UserValidationResult isValidUserPassword(AppUser l);
	
}
