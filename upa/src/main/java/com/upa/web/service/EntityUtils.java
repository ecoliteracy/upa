package com.upa.web.service;

import java.util.Date;

import com.upa.web.model.BaseEntity;

public class EntityUtils {
	
	public static void setupAuditTrail(BaseEntity entity, boolean isCreate) {
		if (isCreate) {
			entity.setTz_code("CDT");
			entity.setCreated_date(new Date());
			entity.setLast_modified_date(new Date());
		}else{
			entity.setLast_modified_date(new Date());
		}
	}
}
