package com.technology.jep.jepriashowcase.feature.rest;

import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.FeatureService;
import com.technology.jep.jepriashowcase.feature.dto.FeatureCreateDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.main.rest.jersey.ApplicationConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.jepria.server.data.OptionDto;
import org.jepria.server.service.rest.MetaInfoResource;
import org.jepria.server.service.rest.XCacheControlFilter;
import org.jepria.server.service.rest.gson.JsonBindingProvider;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase.ExceptionMapperDefault;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase.ExceptionMapperJsonb;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase.ExceptionMapperUndeclaredThrowable;
import org.jepria.server.service.rest.jersey.JepSecurityContextBinder;
import org.jepria.server.service.rest.jersey.validate.ExceptionMapperValidation;
import org.jepria.server.service.security.Credential;
import org.jepria.server.service.security.HttpBasicDynamicFeature;
import org.jepria.server.service.security.JaxrsCorsFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class FeatureJaxrsAdapterTest extends JerseyTest {

  private static final String ENTITY_URL        = "/feature";
  private static final String OPERATOR_NAME     = "username";
  private static final String OPERATOR_PASSWORD = "password";

  private class FeatureServiceImpl implements FeatureService {

    @Override
    public void setFeatureResponsible(Integer featureId, Integer responsibleId, Credential credential) {

    }

    @Override
    public List<OptionDto<Integer>> getFeatureOperator() {
      return null;
    }

    @Override
    public List<OptionDto<String>> getFeatureStatus() {
      OptionDto<String> optionDto = new OptionDto<String>();
      optionDto.setName("NAme");
      optionDto.setValue("value");
      List<OptionDto<String>> options = new ArrayList<>();
      options.add(optionDto);
      return options;
    }

  }

  @Override
  protected Application configure() {
    enable(TestProperties.DUMP_ENTITY);
    enable(TestProperties.LOG_TRAFFIC);
//    ApplicationConfig config = new ApplicationConfig();
//    return config;
    ResourceConfig config = new ResourceConfig(FeatureJaxrsAdapter.class);

    config.register(JsonBindingProvider.class);
    config.register(HttpBasicDynamicFeature.class);
    config.register(RolesAllowedDynamicFeature.class);
    config.register(JepSecurityContextBinder.class);
    config.register(XCacheControlFilter.class);
    config.register(ExceptionMapperJsonb.class);
    config.register(ExceptionMapperUndeclaredThrowable.class);
    config.register(ExceptionMapperDefault.class);
    config.register(MetaInfoResource.class);
    config.register(ExceptionMapperValidation.class);
    config.register(JaxrsCorsFilter.class);
    return new ResourceConfig(FeatureJaxrsAdapter.class);
  }

  @BeforeEach
  void before() throws Exception {
    super.setUp();
  }

  @AfterEach
  void after() throws Exception {
    super.tearDown();
  }

  @Test
  void setFeatureResponsible() {

  }

  @Test
  void getFeatureOperator() {
  }

  @Test
  void getFeatureStatus() {

    FeatureServiceImpl service = new FeatureServiceImpl();

    FeatureServerFactory.setService(service);

    Response response = target("/feature/option/feature-status").request().get();

    Assertions.assertEquals(200, response.getStatus(), "should return 200");
  }

  @Test
  void getRecordById() {
    Response response = target("/feature/1").request().get();
    Assertions.assertEquals(200, response.getStatus(), "should return 200");
  }

  @Test
  void create() {
    /*FeatureCreateDto dto = new FeatureCreateDto();
    dto.setDescription("fwef");
    dto.setFeatureName("name");
    dto.setFeatureNameEn("nameEng");
    Entity<FeatureCreateDto> entity = new Entity<FeatureCreateDto>();
    Response response = target("/feature").request().post();
    Assertions.assertEquals(201, response.getStatus(), "should return 201");*/
  }

  @Test
  void deleteRecordById() {
  }

  @Test
  void update() {
  }

  @Test
  void postSearch() {
  }

  @Test
  void getSearchRequest() {
  }

  @Test
  void getSearchResultsetSize() {
  }

  @Test
  void getResultset() {
  }

  @Test
  void getResultsetPaged() {
  }

}