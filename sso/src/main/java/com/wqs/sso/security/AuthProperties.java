package com.wqs.sso.security;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties("aw.auth")
public class AuthProperties {

	@NotBlank
	private String providerUri;
	
	@NotBlank
	private JksProperties jks;

	public String getProviderUri() {
		return providerUri;
	}

	public void setProviderUri(String providerUri) {
		this.providerUri = providerUri;
	}
	
	public JksProperties getJks() {
		return jks;
	}

	public void setJks(JksProperties jks) {
		this.jks = jks;
	}
	
}
