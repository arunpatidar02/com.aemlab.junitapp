package com.aemlab.junit.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Download{

	@Inject
	private String label;
	
	@Inject
	private String href;
	
	@Inject
	private String title;

	@PostConstruct
	protected void init() {

		if (href != null) {
			href = ModelHelper.getLink(href);
		}
	}

	public String getLabel() {
		return label;
	}

	public String getHref() {
		return href;
	}

	public String getTitle() {
		return title;
	}

}