package com.upa.web.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(schema = "upa", name="upa_user_salary_type")
public class UserSalaryType extends BaseEntity{	

	@Id
    @Column(name="USER_SAL_TYPE_SEQ", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userSalTypeSeq;
	
	@Column(name="SALARY_AMT")
	Double salAmt;
	
	@Column(name="SALARY_CURRENCY_CODE")
	String salCurrCode;
	
	@Column(name="EXEMPT_IND")
	Boolean exemptInd;
	
	@Column(name="PAY_PERIOD_TYPE")
	String payPeriodType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_DATE")
	Date firstDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DATE")
	Date lastDate;
	
	@Temporal(TemporalType.TIME)
	@Column(name="TIME_ON_BREAK")
	Date timeOnBreak;
	
	@Transient
	String timeOnBreakStr;
	
		@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ", nullable = false)
	AppUser appUser;
	
	public UserSalaryType(){};
	
	public UserSalaryType(Integer userSeq, Double salAmt,String salCurrCode,Boolean exemptInd, String payPeriodType, Date firstDate, Date lastDate, Date timeOnBreak){
		this.userSalTypeSeq = userSeq;
		this.salAmt = salAmt;
		this.exemptInd = exemptInd;
		this.payPeriodType = payPeriodType;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.timeOnBreak = timeOnBreak;
	}
	
	public Integer getUserSeq() {
		return userSalTypeSeq;
	}

	public void setUserSeq(Integer userSeq) {
		this.userSalTypeSeq = userSeq;
	}

	public Double getSalAmt() {
		return salAmt;
	}

	public void setSalAmt(Double salAmt) {
		this.salAmt = salAmt;
	}

	public String getSalCurrCode() {
		return salCurrCode;
	}

	public void setSalCurrCode(String salCurrCode) {
		this.salCurrCode = salCurrCode;
	}

	public Boolean getExemptInd() {
		return exemptInd;
	}

	public void setExemptInd(Boolean exemptInd) {
		this.exemptInd = exemptInd;
	}
	
	public String getPayPeriodType() {
		return payPeriodType;
	}

	public void setPayPeriodType(String payPeriodType) {
		this.payPeriodType = payPeriodType;
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
	
	public Date getTimeOnBreak() {
		return timeOnBreak;
	}

	public void setTimeOnBreak(Date timeOnBreak) {
		this.timeOnBreak = timeOnBreak;
	}	
	
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getTimeOnBreakStr() {
		return timeOnBreakStr;
	}

	public void setTimeOnBreakStr(String timeOnBreakStr) {
		this.timeOnBreakStr = timeOnBreakStr;
	}
}