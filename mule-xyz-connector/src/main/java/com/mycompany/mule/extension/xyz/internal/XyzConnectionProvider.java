package com.mycompany.mule.extension.xyz.internal;

import javax.inject.Inject;

import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.http.api.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class (as it's name implies) provides connection instances and the
 * funcionality to disconnect and validate those connections.
 * <p>
 * All connection related parameters (values required in order to create a
 * connection) must be declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares
 * that connections resolved by this provider will be pooled and reused. There
 * are other implementations like {@link CachedConnectionProvider} which lazily
 * creates and caches connections or simply {@link ConnectionProvider} if you
 * want a new connection each time something requires one.
 */
public class XyzConnectionProvider implements PoolingConnectionProvider<XyzConnection> {

	private final Logger LOGGER = LoggerFactory.getLogger(XyzConnectionProvider.class);

	@Inject
	private HttpService httpService;

	@Override
	public XyzConnection connect() throws ConnectionException {
		return new XyzConnection(httpService);
	}

	@Override
	public void disconnect(XyzConnection connection) {
		try {
			connection.invalidate();
		} catch (Exception e) {
			LOGGER.error("Error while disconnecting: " + e.getMessage(), e);
		}
	}

	@Override
	public ConnectionValidationResult validate(XyzConnection connection) {
		return ConnectionValidationResult.success();
	}
}
