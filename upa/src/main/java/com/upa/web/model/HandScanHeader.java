package com.upa.web.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="upa_hand_scan")
public class HandScanHeader extends BaseEntity{

	@Id
	@Column(name="HEADER_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long headerId;
	@Column(name="TERM_TYPE")
	String termType;
	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_DATE")
	Date firstDate;
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DATE")
	Date lastDate;
	@Column(name="TOTAL_HOUR")
	long totalHour;
	@Column(name="REMAINING_HOUR")
	long remainingHour;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "handScanHeader")
	List<HandScanRecord> handscanrecords;

	public HandScanHeader(){};

	public HandScanHeader(Long id, Date firstDate, Date lastDate, String termType, long totalHour, long remainingHour, List<HandScanRecord> handscanrecords){
		this.headerId=id;
		this.termType = termType;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.totalHour = totalHour;
		this.remainingHour = remainingHour;
		this.handscanrecords = handscanrecords;
	}
	
	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
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

	public void setTotalHour(long totalHour) {
		this.totalHour = totalHour;
	}

	public long getRemainingHour() {
		return remainingHour;
	}

	public void setRemainingHour(long remainingHour) {
		this.remainingHour = remainingHour;
	}

	public List<HandScanRecord> getHandscanrecords() {
		return handscanrecords;
	}

	public void setHandscanrecords(List<HandScanRecord> handscanrecords) {
		this.handscanrecords = handscanrecords;
	}
}