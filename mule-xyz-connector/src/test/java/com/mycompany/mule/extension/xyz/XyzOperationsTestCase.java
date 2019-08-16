package com.mycompany.mule.extension.xyz;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;

public class XyzOperationsTestCase extends MuleArtifactFunctionalTestCase {

	/**
	 * Specifies the mule config xml with the flows that are going to be executed in
	 * the tests, this file lives in the test resources.
	 */
	@Override
	protected String getConfigFile() {
		return "test-mule-config.xml";
	}

	@Test
	public void executeStoreOperation() throws Exception {
		flowRunner("storeFlow").run().getMessage().getPayload().getValue();
	}

	@Test
	public void executeRetrieveOperation() throws Exception {
		flowRunner("retrieveFlow").run().getMessage().getPayload().getValue();
	}
}
