package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import com.aemlab.junit.core.services.ProjectConfigurations;

import io.wcm.testing.mock.aem.junit5.AemContext;
import junitx.util.PrivateAccessor;

@RunWith(JUnitPlatform.class)
class GoogleMapModelTest {

	private ProjectConfigurations config;

	@InjectMocks
	GoogleMapModel googleMapModel = new GoogleMapModel();

	@BeforeEach
	void setUp() throws Exception {
		new AemContext();
		config = mock(ProjectConfigurations.class);
	}

	@Test
	void testNonEmptyKeyAndLocation() throws NoSuchFieldException {
		when(config.getGoogleAPIKey()).thenReturn("somekey");
		PrivateAccessor.setField(googleMapModel, "projConfig", config);
		PrivateAccessor.setField(googleMapModel, "loc", "somelocation");
		googleMapModel.init();
		assertNotNull(googleMapModel.getLocation());
		assertNotNull(googleMapModel.getGoogleMapURI());
	}

	@Test
	void testEmptyKey() throws NoSuchFieldException {
		when(config.getGoogleAPIKey()).thenReturn(new String());
		PrivateAccessor.setField(googleMapModel, "projConfig", config);
		PrivateAccessor.setField(googleMapModel, "loc", "Some location");
		googleMapModel.init();
		assertNotNull(googleMapModel.getLocation());
		assertNull(googleMapModel.getGoogleMapURI());

	}

	@Test
	void testEmptyKeyAndLocation() throws NoSuchFieldException {
		when(config.getGoogleAPIKey()).thenReturn("");
		PrivateAccessor.setField(googleMapModel, "projConfig", config);
		PrivateAccessor.setField(googleMapModel, "loc", null);
		googleMapModel.init();
		assertNull(googleMapModel.getLocation());
		assertNull(googleMapModel.getGoogleMapURI());

	}

	@Test
	void testEmptyLocation() throws NoSuchFieldException {
		when(config.getGoogleAPIKey()).thenReturn("ssomekey");
		PrivateAccessor.setField(googleMapModel, "projConfig", config);
		PrivateAccessor.setField(googleMapModel, "loc", null);
		googleMapModel.init();
		assertNull(googleMapModel.getLocation());
		assertNull(googleMapModel.getGoogleMapURI());
	}

	@Test
	void testUnsupportedEncoding() throws NoSuchFieldException {
		when(config.getGoogleAPIKey()).thenReturn("ssomekey");
		PrivateAccessor.setField(googleMapModel, "projConfig", config);
		PrivateAccessor.setField(googleMapModel, "loc", "");
		googleMapModel.init();
	}

}
