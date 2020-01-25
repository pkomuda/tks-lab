package com.pas.zad2mvc;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
        register(JacksonFeature.class);
    }
}
