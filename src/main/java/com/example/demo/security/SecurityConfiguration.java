package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private AdminDetailService adminDetailService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(adminDetailService);
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/api/**").permitAll();

		http.antMatcher("/admin/**").authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin()
				.loginPage("/admin/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/admin/dashboard")
				.failureUrl("/admin/login?error=true")
				.permitAll()
				.and()
				.logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login?message=logout")
				.and()
				.exceptionHandling().accessDeniedPage("/erorr/403");
		
		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
		
		return http.build();
	}
}
