package com.aemlab.junit.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.day.cq.wcm.api.Page;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OverviewModel {

	@Self
	Resource resource;

	@Inject
	private String heading;

	@Inject
	private String description;

	@Inject
	private LinkModel link;

	private String linkedPageTitle;

	@PostConstruct
	protected void init() {
		Resource linkResource = resource.getChild("link");
		if (linkResource != null) {
			Object url = linkResource.getValueMap().get("lnHref");
			if (url != null) {
				Resource pageResource = resource.getResourceResolver().getResource(url.toString());
				if (pageResource != null) {
					Page page = pageResource.adaptTo(Page.class);
					linkedPageTitle = page.getTitle();
				}
			}
		}

	}

	public String getHeading() {
		return heading;
	}

	public String getDescription() {
		return description;
	}

	public LinkModel getLink() {
		return link;
	}

	public String getLinkedPageTitle() {
		return linkedPageTitle;
	}

}
