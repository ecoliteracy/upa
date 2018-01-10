package com.upa.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(schema = "upa", name="upa_user_salary_type")
public class UserSalaryType extends BaseEntity{	
	
	@Id
	@GeneratedValue(generator = "foreigngen")
	@GenericGenerator(strategy = "foreign", name="foreigngen",
			parameters = @Parameter(name = "property", value="app_user"))
	@Column(name="USER_ID", unique = true, nullable = false)
	Long userId;
	@Column(name="SALARY_AMT")
	Double salAmt;
	
	@Column(name="SALARY_CURRENCY_CODE")
	String salCurrCode;
	
	@Column(name="SALARY_TYPE")
	Double salType;
	
	@Column(name="EXEMPT_IND")
	Boolean exemptInd;	
	
	public UserSalaryType(){};
	
	public UserSalaryType(Long userId, Double salAmt,String salCurrCode,Double salType,Boolean exemptInd){
		this.userId = userId;
		this.salAmt = salAmt;
		this.salType = salType;
		this.exemptInd = exemptInd;		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Double getSalType() {
		return salType;
	}

	public void setSalType(Double salType) {
		this.salType = salType;
	}

	public Boolean getExemptInd() {
		return exemptInd;
	}

	public void setExemptInd(Boolean exemptInd) {
		this.exemptInd = exemptInd;
	}
}