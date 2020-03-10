package com.aemlab.junit.core.services.impl;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemlab.junit.core.models.JunitAppConstants;
import com.aemlab.junit.core.models.PageItem;
import com.aemlab.junit.core.services.CreatePageService;
import com.aemlab.junit.core.services.ProjectConfigurations;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component
public class CreatePageServiceImpl implements CreatePageService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Reference
	private ProjectConfigurations config;

	/**
	 * @param Item(DTO)
	 *            pageItem
	 * @param String
	 *            - path
	 * @param resolver
	 *            - resolver
	 */
	@Override
	public Page createPage(PageItem pageItem, String path, ResourceResolver resolver) {
		Page page = null;
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		try {
			String pageName = JcrUtil.createValidName(pageItem.getTitle());
			page = pageManager.create(path, pageName, JunitAppConstants.TEMPLATE_PATH, pageItem.getTitle(), false);
			// Add or Update properties
			if (page != null)
				setProperties(pageItem, page, resolver);

		} catch (WCMException e) {
			log.error("{} Error thrown while creating the Page [{}]", e, pageItem.getTitle());
		}

		return page;
	}

	/**
	 * Method that sets all the page properties from the Bean
	 * 
	 * @param Item(DTO)
	 *            pageItem, Page page
	 */
	private void setProperties(PageItem pageItem, Page page, ResourceResolver resolver) {
		Node jcrNode;
		try {

			jcrNode = page.getContentResource().adaptTo(Node.class);
			if (null != jcrNode) {
				log.debug("Adding or Updating properties for Page [{}]", pageItem.getTitle());
				jcrNode.setProperty(JcrConstants.JCR_LANGUAGE, pageItem.getLanguageCode());
				jcrNode.setProperty(JcrConstants.JCR_TITLE, pageItem.getTitle());
				jcrNode.setProperty(JcrConstants.JCR_DESCRIPTION, pageItem.getDescription());

				// All component specific content added in this method
				addPageContent(pageItem, jcrNode);

			}
		} catch (RepositoryException e) {
			log.error("Error in setting property {}", e);
		}

	}

	/**
	 * 
	 * @param Item(DTO)
	 *            pageItem, Node jcrNode
	 */
	private void addPageContent(PageItem pageItem, Node jcrNode) throws RepositoryException {
		Node componentsNode = jcrNode.addNode("item");
		String contentHtml = "";
		if (StringUtils.isNotBlank(pageItem.getDescription())) {
			contentHtml += "<h4>" + pageItem.getDescription() + "</h4>";
		}
		componentsNode.setProperty("description", contentHtml);
	}
}
