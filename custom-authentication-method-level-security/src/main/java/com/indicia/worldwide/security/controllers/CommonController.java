package com.indicia.worldwide.security.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indicia.worldwide.security.model.Contact;

@RestController
public class CommonController {

	@PostMapping("/contact")
	@PreFilter("filterObject.contactName == 'TEST'")
	public Contact getContact(@RequestBody Contact contact) {
		contact.setContactId(getServiceRequestNumber());
		contact.setCreatedDt(new Date(System.currentTimeMillis()));
		return contact;
		
	}
	
	public String getServiceRequestNumber() {
		Random random = new Random();
		int randomNumber  = random.nextInt(999999999-9999) + 9999;
		return "SR"+randomNumber;
	}
}
