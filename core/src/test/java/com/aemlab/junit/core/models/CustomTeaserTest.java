package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class CustomTeaserTest {
	
	private final AemContext context = new AemContext();

	@BeforeEach
	void setUp() throws Exception {
		context.addModelsForPackage("com.aemlab.junit.core.models");
		context.addModelsForClasses(CustomTeaser.class);
		context.load().json(CustomTeaserTest.class.getResourceAsStream("CustomTeaserTest.json"),JunitAppConstants.TEST_PAGE_ROOT);
	}
	
	@Test
	void testCustomTeaser() {
		context.currentResource(JunitAppConstants.TEST_CONTENT_ROOT+"/teaser");
		CustomTeaser customTeaser = context.request().adaptTo(CustomTeaser.class);
		assertNotNull(customTeaser);
		assertNotNull(customTeaser.getImgPos());
	}

}
