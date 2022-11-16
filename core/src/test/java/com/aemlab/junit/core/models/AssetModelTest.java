package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.day.cq.dam.api.Asset;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class AssetModelTest {

	private final AemContext context = new AemContext();

	@BeforeEach
	public void setup() throws Exception {
		context.load().json(AssetModelTest.class.getResourceAsStream("AssetModelTest.json"),
				JunitAppConstants.TEST_DAM_ROOT);
	}

	@Test
	void testTargetNull() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_DAM_ROOT + "/asset.zip");
		assertNotNull(r);
		Asset asset = r.adaptTo(Asset.class);
		assertNotNull(asset);
	}

}
