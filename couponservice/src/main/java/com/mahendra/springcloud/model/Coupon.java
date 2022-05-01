package com.mahendra.springcloud.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private BigDecimal discount;

	@Column(name = "expDate")
	private Instant expDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Instant getExpDate() {
		return expDate;
	}

	public void setExpDate(Instant expDate) {
		this.expDate = expDate;
	}




	/*
	 * CREATE TABLE coupon( id INT NOT NULL AUTO_INCREMENT, CODE VARCHAR(10),
	 * discount decimal, exp_date TIME DEFAULT NOW(), PRIMARY KEY(id) )
	 */
		




}
