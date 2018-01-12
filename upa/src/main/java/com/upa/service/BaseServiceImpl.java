package com.upa.service;

import java.util.Date;

import com.upa.service.logger.ILoggerService;
import com.upa.service.logger.LoggerServiceImpl;
import com.upa.web.model.entity.BaseEntity;

public class BaseServiceImpl {

	/* logger */
	protected ILoggerService LOG = new LoggerServiceImpl(this.getClass());

	
	protected void setupAuditTrail(BaseEntity entity, boolean isCreate) {
		LOG.logMessage("setupAuditTrail() - enter");
		entity.setLastModifiedDate(new Date());
		entity.setTzCode("CDT");
		if (isCreate) {
			LOG.logMessage("setupAuditTrail() - create operation");
			entity.setCreatedDate(new Date());
		}
		
		LOG.logMessage("setupAuditTrail() - exit");
	}
	
}
