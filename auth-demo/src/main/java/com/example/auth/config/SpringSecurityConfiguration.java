package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Trata os redirecionamentos para a página de login e outras questões de segurança pq a 
 * AuthorizationServerConfiguration só trata questões de segurança para os terminais padrão
 * do Authorization Server
 * @author wedson
 *
 */

@EnableWebSecurity
public class SpringSecurityConfiguration {

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests(authorizeRequests ->
			authorizeRequests.anyRequest().authenticated()
		)
		.formLogin(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService users() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user = User.withUsername("admin")
				.password(encoder.encode("password"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	
}
