package com.mycompany.mule.extension.xyz.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.net.URI;

import org.mule.runtime.core.api.util.IOUtils;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;

import com.mycompany.mule.extension.xyz.api.KeyValueResult;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class XyzOperations {

	/**
	 * Get a value for given key
	 */
	@MediaType(value = ANY, strict = false)
	public String retrieve(@Config XyzConfiguration config, @Connection XyzConnection connection, String token,
			String key) throws Exception {

		HttpClient client = connection.getHttpClient();
		String response = IOUtils
				.toString(client
						.send(HttpRequest.builder().method("GET")
								.uri("https://" + config.getHost() + "/" + token + "/" + key).build(), 0, false, null)
						.getEntity().getContent());
		return response;

	}

	/**
	 * Store a key and value
	 */
	@MediaType(value = ANY, strict = false)
	public KeyValueResult store(@Config XyzConfiguration config, @Connection XyzConnection connection, String key,
			String value) throws Exception {

		HttpClient client = connection.getHttpClient();
		String response = IOUtils.toString(client
				.send(HttpRequest.builder().method("POST").uri("https://" + config.getHost() + "/new/" + key).build(),
						0, false, null)
				.getEntity().getContent());
		URI uri = new URI(response.trim());

		String[] parts = uri.getPath().split("/");

		String token = parts[1];

		KeyValueResult result = new KeyValueResult(key, value, token);

		client.send(
				HttpRequest.builder().method("POST")
						.uri("https://" + config.getHost() + "/" + token + "/" + key + "/" + value).build(),
				0, false, null);

		return result;
	}
}