package com.service.payroll.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@EnableWebSecurity
public class OauthConfig   {
	

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated()
		       
			)	
			.csrf(c -> c
		            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		        )
			
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll()
			.and()
			.oauth2Login()
			.defaultSuccessUrl("/loginSuccess", true)
			
;
		
		return http.build();
	}
	
	

	
	
}
