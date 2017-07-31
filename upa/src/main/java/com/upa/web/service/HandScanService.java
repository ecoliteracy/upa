package com.upa.web.service;

import java.util.Date;
import java.util.List;

import com.upa.web.model.HandScanHeader;
import com.upa.web.model.HandScanRecord;

public interface HandScanService {

	public List<HandScanHeader> getHandScanList();
	public String addHandScan(HandScanRecord hr, HandScanHeader h);
	public String addHandScanRecordUpdateHeader(HandScanRecord hr, HandScanHeader h);
	public HandScanHeader getHandScanOfTerm(Date dt);
	public HandScanRecord getMatchingHandScanRecord(HandScanHeader hd, String dateComp);
}
