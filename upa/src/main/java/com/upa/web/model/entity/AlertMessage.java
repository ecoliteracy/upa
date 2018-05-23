package com.upa.web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "upa", name="upa_alert_message")
public class AlertMessage extends BaseEntity{

	@Id
	@Column(name="MESSAGE_SEQ", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer messageSeq;
	
	@Column(name="MESSAGE_CODE")
	String messageCode;

	@Column(name="MESSAGE_TYPE")
	String messageType;
	
	@Column(name="LANGUAGE_CODE")
	String languageCode;
	
	@Column(name="MESSAGE_VALUE")
	String messageValue;
	
	public AlertMessage(){};
	
	public AlertMessage(Integer messageSeq, String messageCode, String messageType, String languageCode, String messageValue){
		this.messageSeq = messageSeq;
		this.messageCode = messageCode;
		this.messageType = messageType;
		this.messageValue = messageValue;
	}

	public Integer getMessageSeq() {
		return messageSeq;
	}

	public void setMessageSeq(Integer messageSeq) {
		this.messageSeq = messageSeq;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getMessageValue() {
		return messageValue;
	}

	public void setMessageValue(String messageValue) {
		this.messageValue = messageValue;
	}
	
}