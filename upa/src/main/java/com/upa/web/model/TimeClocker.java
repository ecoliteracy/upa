package com.upa.web.model;

import java.util.Date;

public class HandScan {

	Long headerId;
	Date firstDate;
	Date lastDate;
	String clockInOut;
	
	String scanDateStr;
	String scanTimeStr;
	
	public HandScan(){};
	
	public HandScan(Long headerId, Date firstDate, Date lastDate, String clockInOut, String scanDateStr, String scanTimeStr){
		this.headerId = headerId;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.clockInOut = clockInOut;
		this.scanDateStr = scanDateStr;
		this.scanTimeStr = scanTimeStr;
	}
	
	public Long getHeaderId() {
		return headerId;
	}
	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getClockInOut() {
		return clockInOut;
	}
	public void setClockInOut(String clockInOut) {
		this.clockInOut = clockInOut;
	}
	public String getScanDateStr() {
		return scanDateStr;
	}
	public void setScanDateStr(String scanDateStr) {
		this.scanDateStr = scanDateStr;
	}
	public String getScanTimeStr() {
		return scanTimeStr;
	}
	public void setScanTimeStr(String scanTimeStr) {
		this.scanTimeStr = scanTimeStr;
	}
	
}