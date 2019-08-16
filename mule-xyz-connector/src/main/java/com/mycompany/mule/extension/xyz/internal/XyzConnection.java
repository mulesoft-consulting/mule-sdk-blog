package com.mycompany.mule.extension.xyz.internal;

import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class XyzConnection {
	private HttpClient httpClient;

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public XyzConnection(HttpService httpService) {
		HttpClientConfiguration.Builder builder = new HttpClientConfiguration.Builder();
		builder.setName("xyz");
		httpClient = httpService.getClientFactory().create(builder.build());
		httpClient.start();
	}

	public void invalidate() {
		httpClient.stop();
	}
}
