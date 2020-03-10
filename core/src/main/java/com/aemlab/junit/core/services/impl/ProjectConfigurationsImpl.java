package com.aemlab.junit.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.aemlab.junit.core.services.ProjectConfigurations;

@Component(service = ProjectConfigurations.class, immediate = true)
@Designate(ocd = ProjectConfigurationsImpl.Config.class)
public class ProjectConfigurationsImpl implements ProjectConfigurations {

    @ObjectClassDefinition(name = "Project Base Configuration", description = "Configure the Project Specific Settings")
    public @interface Config {

        @AttributeDefinition(name = "Google API Key", description = "Google API key to connect with google service e.g. Google Map",
                        type = AttributeType.STRING)
        String api_key() default "";
        @AttributeDefinition(name = "Other API Url", description = "Other API url to fetch results e.g./bin/api/other/1",
                type = AttributeType.STRING)
        String other_api() default "/bin/api/other/1";

    }


    private Config config;

    @Activate
    @Modified
    public void activate(Config config) {
        this.config = config;
    }


    @Override
    public String getGoogleAPIKey() {
        return config.api_key();
    }
    
    @Override
    public String getOtherAPI() {
        return config.other_api();
    }

}
