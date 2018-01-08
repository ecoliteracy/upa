package com.upa.web.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


public class BaseEntity {
	@Column(name="TZ_CODE")
	String tz_code;
	@Column(name="CREATED_DATE")
	Date created_date;
	@Column(name="LAST_MODIFIED_DATE")
	Date last_modified_date;
	
	public String getTz_code() {
		return tz_code;
	}
	public void setTz_code(String tz_code) {
		this.tz_code = tz_code;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
}
