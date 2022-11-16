package com.aemlab.junit.core.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.aemlab.junit.core.models.JunitAppConstants;
import com.aemlab.junit.core.models.MockPageItem;
import com.aemlab.junit.core.models.PageItem;
import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

class CreatePageServiceImplTest {

	private PageItem pageItem;
	private ResourceResolver resolver;
	private Page page;

	@InjectMocks
	private CreatePageServiceImpl createPageService = new CreatePageServiceImpl();

	@BeforeEach
	void init() throws WCMException, RepositoryException {
		PageManager pageManager = mock(PageManager.class);
		pageItem = MockPageItem.getPageItem();
		resolver = mock(ResourceResolver.class);
		Resource resource = mock(Resource.class);
		Node node = mock(Node.class);
		page = mock(Page.class);
		when(pageManager.getPage(JunitAppConstants.TEST_PAGE_ROOT)).thenReturn(page);
		when(resolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(page.getContentResource()).thenReturn(resource);
		when(resource.adaptTo(Node.class)).thenReturn(node);
		when(page.getPath()).thenReturn(JunitAppConstants.TEST_PAGE_ROOT);
		when(pageManager.create(JunitAppConstants.TEST_PAGE_ROOT, JcrUtil.createValidName(pageItem.getTitle()),
				JunitAppConstants.TEMPLATE_PATH, pageItem.getTitle(), false)).thenReturn(page);
		when(node.getNode(anyString())).thenReturn(node);
		when(node.addNode(anyString(), anyString())).thenReturn(node);
		when(node.addNode(anyString())).thenReturn(node);
	}

	@Test
	void testPageCreation() {
		createPageService.createPage(pageItem, JunitAppConstants.TEST_PAGE_ROOT, resolver);
		assertNotNull(page);
	}

	@Test
	void testPageNoDescCreation() {
		pageItem = MockPageItem.getPageItem2();
		createPageService.createPage(pageItem, JunitAppConstants.TEST_PAGE_ROOT, resolver);
		assertNotNull(page);
	}

	@Test
	void testNullPageCover() {
		pageItem = MockPageItem.getPageItem3();
		createPageService.createPage(pageItem, JunitAppConstants.TEST_PAGE_ROOT, resolver);
		assertNotNull(page);
	}

	@Test
	void testNullPageCountent() {
		when(page.getContentResource().adaptTo(Node.class)).thenReturn(null);
		createPageService.createPage(pageItem, JunitAppConstants.TEST_PAGE_ROOT, resolver);
		assertNotNull(page);
	}

}
