package com.pas.zad2mvc;

import java.lang.annotation.Annotation;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.jaxrs.base.ProviderBase;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JsonEndpointConfig;
import com.fasterxml.jackson.jaxrs.json.JsonMapperConfigurator;

@Provider
@Consumes(MediaType.WILDCARD)
@Produces(MediaType.WILDCARD)
public class JacksonJsonProvider
        extends ProviderBase<JacksonJsonProvider,
        ObjectMapper,
        JsonEndpointConfig, JsonMapperConfigurator>
{
    public final static String MIME_JAVASCRIPT = "application/javascript";

    public final static String MIME_JAVASCRIPT_MS = "application/x-javascript";

    public final static Annotations[] BASIC_ANNOTATIONS = {
            Annotations.JACKSON
    };

    protected String _jsonpFunctionName;

    @Context
    protected Providers _providers;

    public JacksonJsonProvider() {
        this(null, BASIC_ANNOTATIONS);
    }

    public JacksonJsonProvider(Annotations... annotationsToUse) {
        this(null, annotationsToUse);
    }

    public JacksonJsonProvider(ObjectMapper mapper) {
        this(mapper, BASIC_ANNOTATIONS);
    }

    public JacksonJsonProvider(ObjectMapper mapper, Annotations[] annotationsToUse) {
        super(new JsonMapperConfigurator(mapper, annotationsToUse));
    }

    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }

    public void setJSONPFunctionName(String fname) {
        _jsonpFunctionName = fname;
    }

    @Override
    protected boolean hasMatchingMediaType(MediaType mediaType)
    {
        if (mediaType != null) {
            String subtype = mediaType.getSubtype();

            return "json".equalsIgnoreCase(subtype) || subtype.endsWith("+json")
                    || "javascript".equals(subtype)
                    || "x-javascript".equals(subtype)
                    || "x-json".equals(subtype)
                    ;
        }
        return true;
    }

    @Override
    protected ObjectMapper _locateMapperViaProvider(Class<?> type, MediaType mediaType)
    {
        if (_providers != null) {
            ContextResolver<ObjectMapper> resolver = _providers.getContextResolver(ObjectMapper.class, mediaType);
            if (resolver == null) {
                resolver = _providers.getContextResolver(ObjectMapper.class, null);
            }
            if (resolver != null) {
                return resolver.getContext(type);
            }
        }
        return null;
    }

    @Override
    protected JsonEndpointConfig _configForReading(ObjectReader reader,
                                                   Annotation[] annotations) {
        return JsonEndpointConfig.forReading(reader, annotations);
    }

    @Override
    protected JsonEndpointConfig _configForWriting(ObjectWriter writer,
                                                   Annotation[] annotations) {
        return JsonEndpointConfig.forWriting(writer, annotations,
                _jsonpFunctionName);
    }
}