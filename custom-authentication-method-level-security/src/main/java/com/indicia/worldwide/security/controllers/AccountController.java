package com.indicia.worldwide.security.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController  {

	@GetMapping("/myAccount")
	public String accountDetails() {
		return " Account details of yours";
	}
	
	@GetMapping("/myBalance")
	public String balanceDetails() {
		return " Balance details of yours";
	}
	
	
	@GetMapping("/myLoans")
	//@PreAuthorize("hasRole('ROOT')")
	@PostAuthorize("hasRole('ROOT')")
	public String loanDetails() {
		String loanTest = " loan ";
		System.out.println(loanTest);
		return " Balance details of yours";
	}
	
	@GetMapping("/mycards")
	public String cardDetails() {
		return " Card details of yours";
	}
	
	
	
}
