package com.wqs.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(OAuth2AuthorizationServerConfiguration.class)
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
