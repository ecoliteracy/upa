package com.upa.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upa.web.dao.UserDao;

import Login.Login;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userdao = new UserDao();
	
	@Autowired
	public void userdao(UserDao userdao){
		this.userdao = userdao;
	}
	
	public String isValidUserPassword(Login l){		
		return userdao.isValidUserPassword(l);		
	}
	
}
