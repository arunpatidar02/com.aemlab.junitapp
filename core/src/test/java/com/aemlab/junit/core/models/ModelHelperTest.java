package com.aemlab.junit.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class ModelHelperTest {

	private final AemContext context = new AemContext();
	private String url1, url2, url3, url4, url5, url6, url7, url8;
	private static final String WCM_PATH = "/conf/JunitApp/settings/wcm";
	private static final String ICON_SIZE_PROPERTY = "iconSize";

	@BeforeEach
	public void setup() throws Exception {
		url1 = "/content/JunitAppen";
		url2 = "https://www.google.com/";
		url3 = "#anchor";
		url4 = "/";
		url5 = "/content/dam/JunitApp/proj-info.pdf";
		url7 = "tel:9999956462";
		url8 = "mailto:mymailid.test@mymail.com";
		context.load().json(ModelHelperTest.class.getResourceAsStream("ModelHelperTest.json"),
				JunitAppConstants.TEST_PAGE_ROOT);
	}

	@Test
	void testCovertArrayToList() {
		String[] prop = { "val1", "val2", "val3" };
		assertEquals(Arrays.asList("val1", "val2", "val3"), ModelHelper.covertArrayToList(prop));
		String[] prop2 = null;
		assertEquals(0, ModelHelper.covertArrayToList(prop2).size());
	}

	@Test
	void testGetLink() {
		assertTrue(ModelHelper.getLink(url1).endsWith("html"));
		assertEquals(url2, ModelHelper.getLink(url2));
		assertEquals(url3, ModelHelper.getLink(url3));
		assertEquals("/", ModelHelper.getLink(url4));
		assertEquals(ModelHelper.getLink(url5), url5);
		assertNull(ModelHelper.getLink(url6));
		assertEquals(url7, ModelHelper.getLink(url7));
		assertEquals(url8, ModelHelper.getLink(url8));

	}

	@Test
	void testGetList() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT + "/links");
		List<LinkModel> links = ModelHelper.getChildrenModels(r, LinkModel.class);
		assertNotNull(links);
	}

	@Test
	void testGetNullList() {
		Resource r = context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT + "/links2");
		List<LinkModel> links = ModelHelper.getChildrenModels(r, LinkModel.class);
		assertNull(links);
	}

	@Test
	void testNullDesign() {
		context.load().json(ModelHelperTest.class.getResourceAsStream("IconLinkModelTest.json"),
				JunitAppConstants.TEST_PAGE_ROOT2);
		context.load().json(ModelHelperTest.class.getResourceAsStream("IconLinkDesign.json"), WCM_PATH);
		Resource componentResource = context.resourceResolver()
				.getResource(JunitAppConstants.TEST_CONTENT_ROOT2 + "/links/icon");
		String iconSize = ModelHelper.getDesignPropertyValue(componentResource, ICON_SIZE_PROPERTY, "icon-sm");
		assertEquals("icon-sm", iconSize);
	}

	/*
	 * @Test void testNonNullDesign() {
	 * context.contentPolicyMapping("JunitApp/components/content/icon",
	 * ICON_SIZE_PROPERTY, "icon-md"); Resource componentResource =
	 * context.resourceResolver().getResource(JunitAppConstants.TEST_CONTENT_ROOT2 +
	 * "/links/icon"); String iconSize =
	 * ModelHelper.getDesignPropertyValue(componentResource, ICON_SIZE_PROPERTY,
	 * "icon-sm"); assertEquals("icon-md", iconSize); }
	 */

}
