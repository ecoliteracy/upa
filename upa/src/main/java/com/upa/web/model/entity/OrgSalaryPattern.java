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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "upa", name="upa_org_salary_pattern")
public class OrgSalaryPattern extends BaseEntity{

	@Id
	@Column(name="ORG_SP_SEQ", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long orgspSeq;
	
	@JoinColumn(name="ORG_SEQ", nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	Organization organization;
	
	@Column(name="PAY_PERIOD_TYPE")
	String payPeriodType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_DATE")
	Date firstDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DATE")
	Date lastDate;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER, mappedBy="orgSalaryPattern")
	List<AppUser> appUser;
	
	public OrgSalaryPattern(){};
	
	public OrgSalaryPattern(Long orgspSeq, Organization organization, String payPeriodType, Date firstDate, Date lastDate){
		this.orgspSeq = orgspSeq;
		this.organization = organization;
		this.payPeriodType = payPeriodType;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}

	public Long getOrgspSeq() {
		return orgspSeq;
	}

	public void setOrgspSeq(Long orgspSeq) {
		this.orgspSeq = orgspSeq;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

}