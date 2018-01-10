package com.upa.web.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(schema = "upa", name="upa_hand_scan_record")
public class HandScanRecord extends BaseEntity{
	
	@JoinColumn(name="HEADER_ID", nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	HandScanHeader handScanHeader;
	@Id
	@Column(name="RECORD_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long recordId;
	@Temporal(TemporalType.DATE)
	@Column(name="SCAN_DATE")
	Date scanDate;
	@Temporal(TemporalType.TIME)
	@Column(name="SCAN_IN_TIME")
	Date scanInTime;
	@Temporal(TemporalType.TIME)
	@Column(name="SCAN_OUT_TIME")
	Date scanOutTime;
	@Temporal(TemporalType.TIME)
	@Column(name="PARTICIPATION_TIME")
	Date participationTime;
	
	
	@Transient 
	String scanDateStr;
	@Transient
	String scanTimeStr;
	@Transient
	String type;
	
	public HandScanRecord(){
		
	}
	
	public HandScanRecord(Long id, String scanDateStr, String scanTimeStr, Date ScanInDateTime, Date scanOutDateTime, Date hour, HandScanHeader handScanHeader){
		this.recordId=id;
		this.scanDateStr=scanDateStr;
		this.scanTimeStr=scanTimeStr;
		this.scanInTime=ScanInDateTime;
		this.scanOutTime=scanOutDateTime;
		this.participationTime = hour;
		this.handScanHeader=handScanHeader;
	}
	
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getScanDate() {
		return scanDate;
	}

	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

	public Date getScanInDateTime() {
		return scanInTime;
	}

	public void setScanInTime(Date scanInDateTime) {
		this.scanInTime = scanInDateTime;
	}

	public Date getScanOutTime() {
		return scanOutTime;
	}

	public void setScanOutTime(Date scanOutDateTime) {
		this.scanOutTime = scanOutDateTime;
	}
	public Date getParticipationTime() {
		return participationTime;
	}

	public void setParticipationTime(Date hour) {
		this.participationTime = hour;
	}
	
	public HandScanHeader getHandScanHeader() {
		return handScanHeader;
	}

	public void setHandScanHeader(HandScanHeader handScanHeader) {
		this.handScanHeader = handScanHeader;
	}
}