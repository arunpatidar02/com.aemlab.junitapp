package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class DownloadTest {

	private final AemContext context = new AemContext();

	@BeforeEach
	public void setup() throws Exception {
		context.load().json(DownloadTest.class.getResourceAsStream("DownloadTest.json"),
				JunitAppConstants.TEST_PAGE_ROOT);
	}

	@Test
	void testDownload() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT + "/download");
		assertNotNull(r);
		Download download = r.adaptTo(Download.class);
		assertNotNull(download.getHref());
		assertNotNull(download.getLabel());
		assertNotNull(download.getTitle());
	}

}
