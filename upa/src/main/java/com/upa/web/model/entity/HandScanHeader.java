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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(schema = "upa", name="upa_hand_scan")
public class HandScanHeader extends BaseEntity{

	@Id
	@Column(name="HEADER_SEQ", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer headerSeq;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USER_SEQ", nullable=false)
	AppUser appuser;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_DATE")
	Date firstDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DATE")
	Date lastDate;
	
	@Column(name="TOTAL_HOUR")
	Long totalHour;
	
	@Column(name="REMAINING_HOUR")
	Long remainingHour;
	
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "handScanHeader")
	public List<HandScanRecord> handscanrecords;

	public HandScanHeader(){};

	public HandScanHeader(Integer id, Date firstDate, Date lastDate, Long totalHour, Long remainingHour, List<HandScanRecord> handscanrecords){
		this.headerSeq=id;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.totalHour = totalHour;
		this.remainingHour = remainingHour;
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

	public long getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(Long totalHour) {
		this.totalHour = totalHour;
	}

	public long getRemainingHour() {
		return remainingHour;
	}

	public void setRemainingHour(Long remainingHour) {
		this.remainingHour = remainingHour;
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