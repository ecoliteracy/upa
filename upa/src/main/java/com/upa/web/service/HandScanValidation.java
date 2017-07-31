package com.upa.web.service;

import com.upa.web.constant.HandScanConstant;
import com.upa.web.model.HandScanRecord;

public class HandScanValidation {
	static HandScanConstant handscanconstant = new HandScanConstant();
	
	public String checkInOutTime(HandScanRecord hsr){
		if(hsr.getScanInDateTime() != null && hsr.getScanOutTime() != null){
			long diff = hsr.getScanOutTime().getTime() - hsr.getScanInDateTime().getTime();
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
