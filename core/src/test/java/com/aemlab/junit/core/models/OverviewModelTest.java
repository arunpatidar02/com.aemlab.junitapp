package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class OverviewModelTest {

	private final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);

	@BeforeEach
	public void setup() throws Exception {
		context.load().json(OverviewModelTest.class.getResourceAsStream("OverviewTest.json"),
				JunitAppConstants.TEST_PAGE_ROOT);
	}

	@Test
	void testOverview() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT + "/overview");
		assertNotNull(r);
		OverviewModel overview = r.adaptTo(OverviewModel.class);
		assertNotNull(overview.getHeading());
		assertNotNull(overview.getDescription());
		assertNotNull(overview.getLink());
		assertNotNull(overview.getLinkedPageTitle());
	}

}
