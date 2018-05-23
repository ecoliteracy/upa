package com.upa.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(schema = "upa", name="upa_user_salary_type")
public class UserSalaryType extends BaseEntity{	
	
	
/*
	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "app_user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_SEQ", unique = true, nullable = false)
*/
	/*
	@Id
	@GeneratedValue(generator = "foreigngen")
	@GenericGenerator(strategy = "foreign", name="foreigngen",
			parameters = @Parameter(name = "property", value="app_user"))
	@Column(name="USER_SEQ", unique = true, nullable = false)
	*/

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
	

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ", nullable = false)
	AppUser appUser;
	
	public UserSalaryType(){};
	
	public UserSalaryType(Integer userSeq, Double salAmt,String salCurrCode,Boolean exemptInd, String payPeriodType){
		this.userSalTypeSeq = userSeq;
		this.salAmt = salAmt;
		this.exemptInd = exemptInd;
		this.payPeriodType = payPeriodType;
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
	
	
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
}