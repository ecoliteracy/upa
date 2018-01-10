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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(schema = "upa", name="app_user")
public class AppUser extends BaseEntity{

	@Id
	@Column(name="USER_ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long userId;
	@Column(name="LOGIN_ID")
	String loginId;
	@Column(name="USER_PASSWORD")
	String userPassword;
	@Column(name="USER_LAST_LOGIN_DATE")
	Date userLastLoginDate;
	@JoinColumn(name="ORG_SP_ID", nullable=true)
	@ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
	OrgSalaryPattern orgSalaryPattern;
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	UserSalaryType userSalaryType;
	
	public AppUser(){};
	
	public AppUser(Long userId, String loginId, String userPassword, Date userLastLoginDate){
		this.userId = userId;
		this.loginId = loginId;
		this.userPassword = userPassword;
		this.userLastLoginDate = userLastLoginDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	
	public OrgSalaryPattern getOrgSalaryPattern() {
		return orgSalaryPattern;
	}

	public void setOrgSalaryPattern(OrgSalaryPattern orgSalaryPattern) {
		this.orgSalaryPattern = orgSalaryPattern;
	}

	public UserSalaryType getUserSalaryType() {
		return userSalaryType;
	}

	public void setUserSalaryType(UserSalaryType userSalaryType) {
		this.userSalaryType = userSalaryType;
	}
}
