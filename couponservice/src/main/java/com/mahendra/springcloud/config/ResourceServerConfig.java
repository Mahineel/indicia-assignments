package com.mahendra.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String COUPONSERVICE = "couponservice";

	/*
	 * @Value("{$publicKey}") private String publicKey;
	 */

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(COUPONSERVICE);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$")
				.hasAnyRole("USER", "ADMIN").anyRequest().authenticated().and().csrf().disable();
	}

	/*
	 * @Bean public JwtAccessTokenConverter jwtAccessTokenConverter() {
	 * JwtAccessTokenConverter jwtAccessTokenConverter = new
	 * JwtAccessTokenConverter();
	 * 
	 * 
	 * jwtAccessTokenConverter.setVerifierKey(publicKey); return
	 * jwtAccessTokenConverter; }
	 * 
	 * @Bean public TokenStore tokenStore() { return new
	 * JwtTokenStore(jwtAccessTokenConverter()); }
	 */
}
