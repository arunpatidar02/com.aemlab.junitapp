package com.aemlab.junit.core.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import io.wcm.testing.mock.aem.junit5.AemContext;
import junitx.util.PrivateAccessor;

@RunWith(JUnitPlatform.class)
class ProjectConfigurationsImplTest {

	@InjectMocks
	private ProjectConfigurationsImpl projBaseConfig = new ProjectConfigurationsImpl();

	private ProjectConfigurationsImpl.Config config;

	@BeforeEach
	void setup() {
		try {
			initMock();
		} catch (NoSuchFieldException | RepositoryException | LoginException e) {
			System.out.println("Exception::" + e);
		}
	}

	@Test
	void testGoogleAPI() {
		assertEquals("somekey", projBaseConfig.getGoogleAPIKey());
	}

	@Test
	void testOtherAPI() {
		assertEquals("somepath", projBaseConfig.getOtherAPI());
	}

	private void initMock() throws RepositoryException, NoSuchFieldException, LoginException {
		new AemContext();
		config = mock(ProjectConfigurationsImpl.Config.class);

		PrivateAccessor.setField(projBaseConfig, "config", config);
		when(config.api_key()).thenReturn("somekey");
		when(config.other_api()).thenReturn("somepath");
		projBaseConfig.activate(config);
	}

}
