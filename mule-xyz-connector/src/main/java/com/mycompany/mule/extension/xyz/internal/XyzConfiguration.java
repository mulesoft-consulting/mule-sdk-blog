package com.mycompany.mule.extension.xyz.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(XyzOperations.class)
@ConnectionProviders(XyzConnectionProvider.class)
public class XyzConfiguration {

	@Parameter
	private String host;

	public String getHost() {
		return host;
	}


}
