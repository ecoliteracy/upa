package com.upa.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upa.web.dao.UserDao;
import com.upa.web.model.AppUser;
import com.upa.web.model.UserValidationResult;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userdao = new UserDao();
	
	@Autowired
	public void userdao(UserDao userdao){
		this.userdao = userdao;
	}
	
	public UserValidationResult isValidUserPassword(AppUser l){		
		return userdao.isValidUserPassword(l);		
	}
	
}
