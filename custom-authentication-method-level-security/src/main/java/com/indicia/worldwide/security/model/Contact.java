package com.indicia.worldwide.security.model;

import java.util.Date;

public class Contact {

	private String contactName;
	
	private String contactId;
	
	private Date CreatedDt;

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String string) {
		this.contactId = string;
	}

	public Date getCreatedDt() {
		return CreatedDt;
	}

	public void setCreatedDt(Date createdDt) {
		CreatedDt = createdDt;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", CreatedDt=" + CreatedDt + "]";
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	
}
