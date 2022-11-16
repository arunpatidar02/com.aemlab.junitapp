package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class IconLinkModelTest {

	private final AemContext context = new AemContext();

	@BeforeEach
	public void setup() throws Exception {
		context.load().json(IconLinkModelTest.class.getResourceAsStream("IconLinkModelTest.json"),
				JunitAppConstants.TEST_PAGE_ROOT);
	}

	@Test
	void testIcon() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT + "/links/icon");
		assertNotNull(r);
		IconLinkModel iconModel = r.adaptTo(IconLinkModel.class);
		assertNotNull(iconModel.getLnHref());
		assertNotNull(iconModel.getLnLabel());
		assertEquals("", iconModel.getLnTarget());
		assertNotNull(iconModel.getIcon());
		assertNotNull(iconModel.getIconSize());
		assertEquals("icon-home", iconModel.getIcon());
	}

}
