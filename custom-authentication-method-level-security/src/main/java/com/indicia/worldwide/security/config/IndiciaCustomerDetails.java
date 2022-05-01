package com.indicia.worldwide.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.indicia.worldwide.security.model.Customer;
import com.indicia.worldwide.security.model.SecurityCustomer;
import com.indicia.worldwide.security.repository.CustomerRepository;

@Service
public class IndiciaCustomerDetails implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customers = customerRepository.findByEmail(username);
		System.out.println( customers  );
		if (customers.size() == 0) {
			throw new UsernameNotFoundException("User details not found for the user:  " + username);

		}
		return new SecurityCustomer(customers.get(0));
	}

}
