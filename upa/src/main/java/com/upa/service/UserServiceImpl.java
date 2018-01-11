package com.upa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upa.web.dao.UserDao;
import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;

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
