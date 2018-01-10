package com.upa.web.model.entity;

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

@Entity
@Table(schema = "upa", name="upa_organization")
public class Organization extends BaseEntity{

	@Id
	@Column(name="ORG_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long orgId;
	
	@Column(name="ORG_TYPE")
	String orgType;
	
	@Column(name="ORG_NAME")
	String orgName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "organization")
	List<OrgSalaryPattern> orgSalaryPatterns;
	
	public Organization(){};
	
	public Organization(Long orgId, String orgType, String orgName){
		this.orgId = orgId;
		this.orgType = orgType;
		this.orgName = orgName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}