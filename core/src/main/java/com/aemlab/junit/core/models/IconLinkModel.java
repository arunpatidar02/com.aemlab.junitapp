package com.aemlab.junit.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class IconLinkModel extends LinkModel {
	
	@Self
    private Resource resource;
	
	@Inject
	public String icon;
	
	private String iconSize = "icon-sm";
	
	@PostConstruct
    protected void init() {
		super.init();
		this.iconSize = ModelHelper.getDesignPropertyValue(resource, "iconSize", "icon-sm");
	}

	public String getIcon() {
		return icon;
	}
	
	public String getIconSize() {
		return iconSize;
	}
	

}