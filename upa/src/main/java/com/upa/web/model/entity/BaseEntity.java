package com.upa.web.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	@Column(name="TZ_CODE")
	private String tzCode;
	@Column(name="CREATED_DATE")
	private Date createdDate = new Date();;
	@Column(name="LAST_MODIFIED_DATE")
	private Date lastModifiedDate = new Date();;
	
	public String getTzCode() {
		return tzCode;
	}
	public void setTzCode(String tz_code) {
		this.tzCode = tz_code;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date created_date) {
		this.createdDate = created_date;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date last_modified_date) {
		this.lastModifiedDate = last_modified_date;
	}
}
