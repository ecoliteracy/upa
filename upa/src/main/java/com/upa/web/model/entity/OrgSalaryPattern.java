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

@Entity
@Table(schema = "upa", name="upa_org_salary_pattern")
public class OrgSalaryPattern extends BaseEntity{

	@Id
	@Column(name="ORG_SP_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long orgspId;
	
	@JoinColumn(name="ORG_ID", nullable=false)
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	Organization organization;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_DATE")
	Date firstDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DATE")
	Date lastDate;
	
	@Column(name="FREQUENCY_TYPE")
	String frequencyType;
	
	public OrgSalaryPattern(){};
	
	public OrgSalaryPattern(Long orgspId, Organization organization, Date firstDate, Date lastDate, String frequencyType){
		this.orgspId = orgspId;
		this.organization = organization;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.frequencyType = frequencyType;
	}

	public Long getOrgspId() {
		return orgspId;
	}

	public void setOrgspId(Long orgspId) {
		this.orgspId = orgspId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}
}


/*
DROP TABLE IF EXISTS upa.upa_org_salary_pattern;
CREATE TABLE upa.upa_org_salary_pattern (
  ORG_SP_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  ORG_ID INT(10) NOT NULL,
  FIRST_DATE DATE NOT NULL,
  LAST_DATE DATE NOT NULL,
  FREQUENCY_TYPE VARCHAR(3) NOT NULL, 
  TZ_CODE VARCHAR(3) NOT NULL,
  CREATED_DATE DATETIME NOT NULL,
  LAST_MODIFIED_DATE DATETIME NOT NULL, 
  PRIMARY KEY (ORG_SP_ID) USING BTREE,
  FOREIGN KEY (ORG_ID) REFERENCES upa.upa_organization(ORG_ID))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
