package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class LinkModelTest {

	private final AemContext context = new AemContext();

	@BeforeEach
	public void setup() throws Exception {
		 context.load().json(LinkModelTest.class.getResourceAsStream("LinkModelTest.json"),JunitAppConstants.TEST_PAGE_ROOT);
	}
	
	@Test
	public void testTargetNull() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT+"/links/link0");
		assertNotNull(r);
		LinkModel linkModel = r.adaptTo(LinkModel.class);
		assertNotNull(linkModel.getLnHref());
		assertNotNull(linkModel.getLnLabel());
		assertEquals("", linkModel.getLnTarget());
	}

	@Test
	public void testTargetBlank() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT+"/links/link1");
		assertNotNull(r);
		LinkModel linkModel = r.adaptTo(LinkModel.class);
		assertNotNull(linkModel.getLnTitle());
		assertNotNull(linkModel.getLnHref());
		assertEquals("_blank", linkModel.getLnTarget());
	}

	@Test
	public void testTargetHrefNull() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT+"/links/link2");
		assertNotNull(r);
		LinkModel linkModel = r.adaptTo(LinkModel.class);
		assertNull(linkModel.getLnTarget());
		assertNull(linkModel.getLnHref());
	}

}
