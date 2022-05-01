package com.indicia.worldwide.security.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.indicia.worldwide.security.model.Authority;
import com.indicia.worldwide.security.model.Customer;
import com.indicia.worldwide.security.repository.CustomerRepository;

@Component
public class IndiciaCustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		System.out.println(userName + " user");
		System.out.println(pwd + " password");
		List<Customer> customers = customerRepository.findByEmail(userName);

		if (customers.size() > 0) {
			if (passwordEncoder.matches(pwd, customers.get(0).getPassword())) {

				return new UsernamePasswordAuthenticationToken(userName, pwd,
						getGrantedAuthorities(customers.get(0).getAuthorities()));
			} else {
				throw new BadCredentialsException("Invalid Password");
			}
		} else {
			throw new BadCredentialsException("No user registered with details");
		}

	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authority) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority2 : authority) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority2.getAuthority()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
