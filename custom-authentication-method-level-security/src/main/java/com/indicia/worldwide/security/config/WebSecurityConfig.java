package com.indicia.worldwide.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * Default Confifuration which will secure all the endpoints
		 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and()
		 * .httpBasic();
		 */

		/*
		 * Custom configuration as per requirement based on roles and authorities 
		 */
		/*
		 * http.authorizeRequests().antMatchers("/myAccount").hasAuthority("UPDATE").
		 * antMatchers("/myBalance").hasAuthority("READ")
		 * .antMatchers("/myLoans").hasAuthority("DELETE").antMatchers("/myCards").
		 * authenticated().antMatchers("/contact")
		 * .permitAll().and().formLogin().and().httpBasic();
		 */
		
		http.authorizeRequests().antMatchers("/myAccount").hasRole("USER").antMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
		.antMatchers("/myLoans").authenticated().antMatchers("/myCards").authenticated().antMatchers("/contact")
		.permitAll().and().formLogin().and().httpBasic();

		/**
		 * 
		 * Configuration to deny all the requests
		 *
		 * 
		 * http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().
		 * httpBasic();
		 * 
		 */

		/**
		 * 
		 * Permit all the request
		 * http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().
		 * httpBasic();
		 */

	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * 
	 * User Management
	 * 
	 * 
	 * auth.inMemoryAuthentication().withUser("admin").password("12345").authorities
	 * ("admin").and().withUser("user")
	 * .password("12345").authorities("read").and().passwordEncoder(
	 * NoOpPasswordEncoder.getInstance());
	 * 
	 * 
	 * 
	 * Understanding in depth
	 * 
	 * 
	 * 
	 * InMemoryUserDetailsManager userDetailsManager = new
	 * InMemoryUserDetailsManager(); UserDetails userDetails =
	 * User.withUsername("Mahendra").password("Ma120397@").authorities("admin").
	 * build(); UserDetails userDetails2 =
	 * User.withUsername("admin").password("12345").authorities("read").build();
	 * userDetailsManager.createUser(userDetails);
	 * userDetailsManager.createUser(userDetails2);
	 * auth.userDetailsService(userDetailsManager);
	 * 
	 * 
	 * 
	 * }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsService(javax.sql.DataSource
	 * dataSource) { return new JdbcUserDetailsManager(dataSource); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
