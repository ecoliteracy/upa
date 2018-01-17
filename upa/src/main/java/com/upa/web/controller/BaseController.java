package com.upa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.upa.service.AlertMessageService;
import com.upa.web.model.entity.AlertMessage;

@Controller
public abstract class BaseController {

	
	private AlertMessageService alertMsgSrv;
	
	@Autowired(required=true)
	@Qualifier(value="alertMsgSrv")
	public void AlertMessageService(AlertMessageService alertMsgSrv){
		this.alertMsgSrv = alertMsgSrv;
	}
	
	public String getAlertMessage(String messageCode, String languageCode){
		AlertMessage altMsg = alertMsgSrv.getAlertMessage(messageCode, languageCode);
		if(altMsg != null){
			return altMsg.getMessageValue();
		}else{
			return null;	
		}
	}
}
