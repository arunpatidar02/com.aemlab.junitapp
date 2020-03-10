package com.aemlab.junit.core.models;

public class MockPageItem {

	private MockPageItem() {}


	public static PageItem getPageItem() {
		PageItem item = new PageItem();
		item.setDescription("Junit Page Creation Test");
		item.setLanguageCode("en-US");
		item.setTitle("Junit Test Page");

		return item;
	}
	
	public static PageItem getPageItem2() {
		PageItem item = new PageItem();
		item.setDescription("");
		item.setLanguageCode("en-US");
		item.setTitle("Junit Test Page");

		return item;
	}
	
	public static PageItem getPageItem3() {
		PageItem item = new PageItem();
		item.setDescription("Junit Page Creation Test");
		item.setLanguageCode("en-US");
		item.setTitle("Junit Test Page Other");

		return item;
	}

}
