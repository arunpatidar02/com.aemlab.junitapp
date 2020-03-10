package com.aemlab.junit.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.aemlab.junit.core.models.ModelHelper;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkModel {
	@Inject
	public String lnLabel; 
	
	@Inject
	public String lnHref;
	
	@Inject
	public String lnTarget; 
	
	@Inject
	public String lnTitle;


	@PostConstruct
	protected void init() {

		if (lnTarget != null) {
			lnTarget = lnTarget.equals("none") ? "" : lnTarget;
		}
		if (lnHref != null) {
			lnHref = ModelHelper.getLink(lnHref);
		}
	}

	public String getLnLabel() {
		return lnLabel;
	}

	public String getLnHref() {
		return lnHref;
	}

	public String getLnTarget() {
		return lnTarget;
	}

	public String getLnTitle() {
		return lnTitle;
	}

}