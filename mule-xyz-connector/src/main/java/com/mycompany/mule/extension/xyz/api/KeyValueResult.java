package com.mycompany.mule.extension.xyz.api;

public class KeyValueResult {

	public KeyValueResult(String key, String value, String token) {
		super();
		this.key = key;
		this.value = value;
		this.token = token;
	}

	private String key;
	private String value;
	private String token;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
