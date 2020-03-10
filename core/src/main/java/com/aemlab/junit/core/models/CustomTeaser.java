package com.aemlab.junit.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import com.adobe.cq.wcm.core.components.models.ListItem;
import com.adobe.cq.wcm.core.components.models.Teaser;

@Model(adaptables = SlingHttpServletRequest.class, adapters = {Teaser.class,CustomTeaser.class}, resourceType = JunitAppConstants.TEASER_RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomTeaser implements Teaser {

	@Self
	@Via(type = ResourceSuperType.class)
	private Teaser teaser;
	
	@ValueMapValue
    private String imgPos;
	
	
	@Override
	public boolean isActionsEnabled() {
		return teaser.isActionsEnabled();
	}

	@Override
	public List<ListItem> getActions() {
		return teaser.getActions();
	}

	@Override
	public String getLinkURL() {
		return teaser.getLinkURL();
	}

	@Override
	public Resource getImageResource() {
		return teaser.getImageResource();
	}

	@Override
	public boolean isImageLinkHidden() {
		return teaser.isImageLinkHidden();
	}

	@Override
	public String getTitle() {
		return teaser.getTitle();
	}

	@Override
	public boolean isTitleLinkHidden() {
		return teaser.isTitleLinkHidden();
	}

	@Override
	public String getDescription() {
		return teaser.getDescription();
	}

	@Override
	public String getTitleType() {
		return teaser.getTitleType();
	}

	@Override
	public String getExportedType() {
		return teaser.getExportedType();
	}


    public String getImgPos() {
        return imgPos;
    }
	
}
