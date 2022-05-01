package com.mahendra.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahendra.springcloud.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
