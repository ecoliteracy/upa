package com.upa.service;

import com.upa.web.model.entity.AlertMessage;

public interface AlertMessageService {

	public AlertMessage getAlertMessage(String messageCode, String languageCode);
	
}
