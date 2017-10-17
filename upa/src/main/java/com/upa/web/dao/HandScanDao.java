package com.upa.web.dao;

import java.util.Date;
import java.util.List;

import com.upa.web.model.HandScanHeader;
import com.upa.web.model.HandScanRecord;

public interface HandScanDao {
	
	public String saveHandscan(HandScanRecord hs);
	public String saveHandscanHeader(HandScanHeader hs);
	public String getCurrentDate();
	public List<HandScanHeader> getHandScan();
	public HandScanHeader getHandScanOfTerm(Date dt);
	public HandScanHeader getHandScanHeaderById(long id);
	public List<Date> getParticipateTime(Long id);
	
}
