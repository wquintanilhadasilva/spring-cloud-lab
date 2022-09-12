package com.wqs.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
	
	
	
	public SecurityConfig(PasswordEncoder passwordEncoder, AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("admin@email.com")
        .password(passwordEncoder.encode("123456"))
        .roles("USER")
        .and()
        .withUser("admin")
        .password(passwordEncoder.encode("123456"))
        .roles("USER", "ADMIN");
	}
	
}
