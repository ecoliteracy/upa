package com.upa.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_user")
public class AppUser extends BaseEntity{

	@Id
	@Column(name="USER_NO", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long userNo;
	@Column(name="USER_ID")
	String userId;
	@Column(name="USER_PASSWORD")
	String userPassword;
	@Column(name="USER_LAST_LOGIN_DATE")
	Date userLastLoginDate;
	
	public AppUser(){};
	
	public AppUser(Long userNo, String userId, String userPassword, Date userLastLoginDate){
		this.userNo = userNo;
		this.userId = userId;
		this.userPassword = userPassword;
		this.userLastLoginDate = userLastLoginDate;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserLastLoginDate() {
		return userLastLoginDate;
	}

	public void setUserLastLoginDate(Date userLastLoginDate) {
		this.userLastLoginDate = userLastLoginDate;
	}

}
