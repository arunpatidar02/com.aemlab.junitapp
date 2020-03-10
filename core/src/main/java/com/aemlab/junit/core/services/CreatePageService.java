package com.aemlab.junit.core.services;

import org.apache.sling.api.resource.ResourceResolver;

import com.aemlab.junit.core.models.PageItem;
import com.day.cq.wcm.api.Page;

public interface CreatePageService {

    /**
     * @param PageItem(DTO) pageItem
     * @param String - path
     * @param resolver - resolver
     */
    public Page createPage(PageItem pageItem, String path, ResourceResolver resolver);

}
