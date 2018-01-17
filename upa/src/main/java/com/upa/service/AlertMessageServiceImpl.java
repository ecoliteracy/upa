package com.upa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upa.web.dao.AlertMessageDaoImpl;
import com.upa.web.model.entity.AlertMessage;

@Service
public class AlertMessageServiceImpl implements AlertMessageService{

	private static AlertMessageDaoImpl alertMessageDaoImp;
	@Autowired
	public void alertMessageDao(AlertMessageDaoImpl alertMessageDaoImpl){
		this.alertMessageDaoImp = alertMessageDaoImpl;
	}
	
	public AlertMessage getAlertMessage(String messageCode, String languageCode){
		AlertMessage alertMessage = alertMessageDaoImp.getAllertMessage(messageCode, languageCode);
		return alertMessage;
	}
}
