package com.technology.jep.jepriashowcase.main.rest.jersey;

import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.FeatureServerFactoryImpl;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDaoImpl;
import com.technology.jep.jepriashowcase.feature.rest.FeatureJaxrsAdapter;
import com.technology.jep.jepriashowcase.featureprocess.FeatureProcessServerFactory;
import com.technology.jep.jepriashowcase.featureprocess.FeatureProcessServerFactoryImpl;
import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDao;
import com.technology.jep.jepriashowcase.featureprocess.dao.FeatureProcessDaoImpl;
import com.technology.jep.jepriashowcase.featureprocess.rest.FeatureProcessJaxrsAdapter;
import com.technology.jep.jepriashowcase.goods.GoodsServerFactory;
import com.technology.jep.jepriashowcase.goods.GoodsServerFactoryImpl;
import com.technology.jep.jepriashowcase.goods.dao.GoodsDao;
import com.technology.jep.jepriashowcase.goods.dao.GoodsDaoImpl;
import com.technology.jep.jepriashowcase.goods.rest.GoodsJaxrsAdapter;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jepria.compat.shared.exceptions.ApplicationException;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationConfig extends ApplicationConfigBase {
  
  public ApplicationConfig() {
    registerFeature();
    registerFeatureProcess();
    registerGoods();

    register((ExceptionMapper<ApplicationException>) e -> {
      e.printStackTrace();

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      e.printStackTrace(new PrintStream(baos));
      String stackTraceString = baos.toString();

      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity(stackTraceString)
              .type("text/plain;charset=UTF-8").build();
    });
  }
  
  protected void registerFeature() {
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(FeatureDaoImpl.class).to(FeatureDao.class).in(RequestScoped.class);
      }
    });
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(FeatureServerFactoryImpl.class).to(FeatureServerFactory.class).in(RequestScoped.class);
      }
    });
    register(FeatureJaxrsAdapter.class);
    register(new OpenApiResource().configLocation("src/api-spec/testgen/feature/ApiConfig.yaml"));
  }
  
  protected void registerFeatureProcess() {
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(FeatureProcessDaoImpl.class).to(FeatureProcessDao.class).in(RequestScoped.class);
      }
    });
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(FeatureProcessServerFactoryImpl.class).to(FeatureProcessServerFactory.class).in(RequestScoped.class);
      }
    });
    register(FeatureProcessJaxrsAdapter.class);
  }

  protected void registerGoods() {
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(GoodsDaoImpl.class).to(GoodsDao.class).in(RequestScoped.class);
      }
    });
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(GoodsServerFactoryImpl.class).to(GoodsServerFactory.class).in(RequestScoped.class);
      }
    });
    register(GoodsJaxrsAdapter.class);
    register(new OpenApiResource().configLocation("src/api-spec/testgen/goods/ApiConfig.yaml"));
  }
}