package com.upa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upa.web.dao.UserDao;
import com.upa.web.model.UserValidationResult;
import com.upa.web.model.entity.AppUser;
import com.upa.web.model.entity.UserSalaryType;

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
	
	
	public UserSalaryType getUserSalaryType(Integer userSeq){
		return userdao.getUserSalaryType(userSeq);
	}
	
	public String addUserSalaryType(UserSalaryType userSalaryType){
		return userdao.addUserSalaryType(userSalaryType);
	}
		
}
