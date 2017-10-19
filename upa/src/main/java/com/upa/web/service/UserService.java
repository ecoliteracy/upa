package com.upa.web.service;

import com.upa.web.model.AppUser;
import com.upa.web.model.UserValidationResult;

public interface UserService {
	
	public UserValidationResult isValidUserPassword(AppUser l);
	
}
