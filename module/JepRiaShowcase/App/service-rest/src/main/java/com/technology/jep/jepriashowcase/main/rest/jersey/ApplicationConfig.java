package com.technology.jep.jepriashowcase.main.rest.jersey;

import com.technology.jep.jepriashowcase.feature.FeatureServerFactoryImpl;
import com.technology.jep.jepriashowcase.feature.FeatureServiceImpl;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDaoImpl;
import com.technology.jep.jepriashowcase.feature.rest.FeatureJaxrsAdapter;
import com.technology.jep.jepriashowcase.featureprocess.rest.FeatureProcessJaxrsAdapter;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.jepria.compat.shared.exceptions.ApplicationException;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationConfig extends ApplicationConfigBase {
  
  public ApplicationConfig() {
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(FeatureDaoImpl.class).to(FeatureDaoImpl.class);
      }
    });
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(FeatureServerFactoryImpl.class).to(FeatureServerFactoryImpl.class);
      }
    });
    register(FeatureJaxrsAdapter.class);
    register(FeatureProcessJaxrsAdapter.class);

    register(new ExceptionMapper<ApplicationException>() {
      @Override
      public Response toResponse(ApplicationException e) {
        e.printStackTrace();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String stackTraceString = baos.toString();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(stackTraceString)
                .type("text/plain;charset=UTF-8").build();
      }
    });
  }
}