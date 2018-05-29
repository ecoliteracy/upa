package com.upa.service;

import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.entity.HandScanRecord;

public class HandScanValidation {
	static HandScanConstant handscanconstant = new HandScanConstant();
	
	public String checkInOutTime(HandScanRecord hsr){
		if(hsr.getScanInTime() != null && hsr.getScanOutTime() != null){
			long diff = hsr.getScanOutTime().getTime() - hsr.getScanInTime().getTime();
			if(diff < 0){
				return handscanconstant.INVALID_TIME;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
}
