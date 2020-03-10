package com.aemlab.junit.core.models;

public final class JunitAppConstants {

    private JunitAppConstants() {
        throw new IllegalStateException("Constants file [Utility class]");
    }

    public static final String READ_SUB_SERVICE = "readService";
    public static final String TEST_PAGE_ROOT = "/content/JunitApp/us/en";
    public static final String TEST_CONTENT_ROOT = JunitAppConstants.TEST_PAGE_ROOT + "/jcr:content/root/responsivegrid";
    public static final String TEST_PAGE_ROOT2 = "/content/JunitApp/us/de";
    public static final String TEST_CONTENT_ROOT2 = JunitAppConstants.TEST_PAGE_ROOT2 + "/jcr:content/root/responsivegrid";
    public static final String TEASER_RESOURCE_TYPE = "JunitApp/components/core/teaser";
    public static final String GENERAL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    // AEM date Formatter "2011-11-10T10:20:59.400+01:00"
    public static final String AEM_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String DISPLAY_FORMAT = "MMM dd YYYY";
    public static final String YYYY_MM_DD_FORMATTER = "yyyy-MM-dd";
    public static final String SLASH = "/"; 
    public static final String TEMPLATE_PATH="/conf/JunitApp/settings/wcm/templates/content-page-template";

}
