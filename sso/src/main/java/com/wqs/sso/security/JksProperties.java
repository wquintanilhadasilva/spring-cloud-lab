package com.wqs.sso.security;

import javax.validation.constraints.NotBlank;

public final class JksProperties {
	
	@NotBlank
	private String keypass;

	@NotBlank
	private String storepass;

	@NotBlank
	private String alias;
	
	@NotBlank
	private String path;
	
	public String getKeypass() {
		return keypass;
	}
	public void setKeypass(String keypass) {
		this.keypass = keypass;
	}
	public String getStorepass() {
		return storepass;
	}
	public void setStorepass(String storepass) {
		this.storepass = storepass;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
