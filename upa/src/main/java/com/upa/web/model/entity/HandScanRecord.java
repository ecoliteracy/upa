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
	
	@JoinColumn(name="HEADER_SEQ", nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	HandScanHeader handScanHeader;
	@Id
	@Column(name="RECORD_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer recordSeq;
	@Temporal(TemporalType.DATE)
	@Column(name="SCAN_DATE")
	public Date scanDate;
	@Temporal(TemporalType.TIME)
	@Column(name="SCAN_IN_TIME")
	public Date scanInTime;
	@Temporal(TemporalType.TIME)
	@Column(name="SCAN_OUT_TIME")
	public Date scanOutTime;
	@Temporal(TemporalType.TIME)
	@Column(name="PARTICIPATION_TIME")
	Date participationTime;
	
	
	public HandScanRecord(){
		
	}
	
	public HandScanRecord(Integer id, Date ScanInDateTime, Date scanOutDateTime, Date hour, HandScanHeader handScanHeader){
		this.recordSeq=id;
		this.scanInTime=ScanInDateTime;
		this.scanOutTime=scanOutDateTime;
		this.participationTime = hour;
		this.handScanHeader=handScanHeader;
	}
	
	public Integer getRecordSeq() {
		return recordSeq;
	}

	public void setRecordSeq(Integer recordId) {
		this.recordSeq = recordId;
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