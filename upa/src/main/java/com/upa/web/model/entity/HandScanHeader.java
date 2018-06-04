package com.upa.web.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(schema = "upa", name="upa_hand_scan")
public class HandScanHeader extends BaseEntity{

	@Id
	@Column(name="HEADER_SEQ", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer headerSeq;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USER_SEQ", nullable=false)
	AppUser appuser;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_DATE")
	Date firstDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DATE")
	Date lastDate;
	
	@Column(name="TOTAL_PRTCP_IN_MIN")
	Integer totalParticipationInMin;
	
 	@Column(name="REM_HOUR_IN_MIN")
 	Integer remainingHourInMin;
 	
 	@Column(name="WORKING_HOUR_IN_MIN")
 	Integer workingHourInMin;
	
	@JsonManagedReference
    @OrderBy(clause = "scanDate")
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "handScanHeader")
	public List<HandScanRecord> handscanrecords;

	public HandScanHeader(){};

	public HandScanHeader(Integer id, Date firstDate, Date lastDate, Integer totalParticipationInMin, Integer remainingHourInMin, List<HandScanRecord> handscanrecords){
		this.headerSeq=id;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.totalParticipationInMin = totalParticipationInMin;
		this.remainingHourInMin = remainingHourInMin;
		this.handscanrecords = handscanrecords;
	}
	
	public Integer getHeaderSeq() {
		return headerSeq;
	}

	public void setHeaderId(Integer headerSeq) {
		this.headerSeq = headerSeq;
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

	public Integer getTotalParticipationInMin() {
		return totalParticipationInMin;
	}

	public void setTotalParticipationInMin(Integer totalParticipationInMin) {
		this.totalParticipationInMin = totalParticipationInMin;
	}

	public Integer getRemainingHour() {
		return remainingHourInMin;
	}

	public void setRemainingHour(Integer remainingHourInMin) {
		this.remainingHourInMin = remainingHourInMin;
	}
	
	public Integer getWorkingHourInMin() {
		return workingHourInMin;
	}

	public void setWorkingHourInMin(Integer workingHourInMin) {
		this.workingHourInMin = workingHourInMin;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	public List<HandScanRecord> getHandscanrecords() {
		return handscanrecords;
	}

	public void setHandscanrecords(List<HandScanRecord> handscanrecords) {
		this.handscanrecords = handscanrecords;
	}
}