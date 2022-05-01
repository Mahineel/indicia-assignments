package com.indicia.worldwide.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.indicia.worldwide.security.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

	
	List<Customer> findByEmail(String email);
}
