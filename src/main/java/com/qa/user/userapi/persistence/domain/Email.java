package com.qa.user.userapi.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Email {

	@Id
	@GeneratedValue
	private Long emailId;
	
	private String emailAddress;
	
	public Email() {
		
	}
	
	public Email(Long emailId, String emailAddress) {
		this.emailAddress = emailAddress;
		this.emailId = emailId;
	}
	
	public Long getId() {
		return emailId;
	}
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String toString() {
		return this.emailId + this.emailAddress;
	}
}
