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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(schema = "upa", name="upa_hand_scan_record")
public class HandScanRecord extends BaseEntity{
	
	@JsonBackReference
	@JoinColumn(name="HEADER_SEQ", nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	HandScanHeader handScanHeader;
	@Id
	@Column(name="RECORD_SEQ")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer recordSeq;
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
	
	
	public HandScanRecord(){
		
	}
	
	public HandScanRecord(Integer id, Date scanDate, Date ScanInTime, Date scanOutTime, Date participationTime, HandScanHeader handScanHeader){
		this.recordSeq=id;
		this.scanDate = scanDate;
		this.scanInTime=ScanInTime;
		this.scanOutTime=scanOutTime;
		this.participationTime = participationTime;
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

	public Date getScanInTime() {
		return scanInTime;
	}

	public void setScanInTime(Date scanInTime) {
		this.scanInTime = scanInTime;
	}

	public Date getScanOutTime() {
		return scanOutTime;
	}

	public void setScanOutTime(Date scanOutTime) {
		this.scanOutTime = scanOutTime;
	}
	public Date getParticipationTime() {
		return participationTime;
	}

	public void setParticipationTime(Date participationTime) {
		this.participationTime = participationTime;
	}
	
	public HandScanHeader getHandScanHeader() {
		return handScanHeader;
	}

	public void setHandScanHeader(HandScanHeader handScanHeader) {
		this.handScanHeader = handScanHeader;
	}
}