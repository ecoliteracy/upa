package com.upa.service;

import java.util.Date;

import com.upa.web.model.entity.BaseEntity;

public class EntityUtils {
	
	public static void setupAuditTrail(BaseEntity entity, boolean isCreate) {
		entity.setLastModifiedDate(new Date());
		entity.setTzCode("CDT");
		
		if (isCreate) {
			entity.setCreatedDate(new Date());
		}
	}
}
