package com.aemlab.junit.core.services.impl;


public class Link {

    private String title;
    private String description;
    private String url;
    private boolean openInNewTab;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public boolean isOpenInNewTab() {
		return openInNewTab;
	}

	public void setOpenInNewTab(boolean openInNewTab) {
		this.openInNewTab = openInNewTab;
	}


}
