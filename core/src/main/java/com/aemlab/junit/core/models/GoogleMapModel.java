package com.aemlab.junit.core.models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aemlab.junit.core.services.ProjectConfigurations;
import opennlp.tools.util.StringUtil;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GoogleMapModel {

    @Inject
    public String loc;

    @Inject
    private ProjectConfigurations projConfig;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String googleMapURI;
    

    private final String GOOGLE_MAP_URL = "https://www.google.com/maps/embed/v1/place";

    @PostConstruct
    protected void init() {
        String googleAPIKey = projConfig.getGoogleAPIKey();
        String location;
        if (loc != null && !StringUtil.isEmpty(googleAPIKey)) {
            try {
                location = URLEncoder.encode(loc, StandardCharsets.UTF_8.toString());
                googleMapURI = GOOGLE_MAP_URL + "?key=" + googleAPIKey + "&q=" + location;
            } catch (UnsupportedEncodingException e) {
                logger.error("Location is invalid! Not a valid URI - {}", e);
            }
        } else if (StringUtil.isEmpty(googleAPIKey)) {
            logger.warn("Google Map key is Empty");
        }
    }

    public String getGoogleMapURI() {
        return googleMapURI;
    }

    public String getLocation() {
        return loc;
    }

}
