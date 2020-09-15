package com.technology.jep.jepriashowcase.feature.rest;

import com.technology.jep.jepriashowcase.feature.FeatureService;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.jepria.server.data.OptionDto;
import org.jepria.server.service.rest.MetaInfoResource;
import org.jepria.server.service.rest.XCacheControlFilter;
import org.jepria.server.service.rest.gson.JsonBindingProvider;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase.ExceptionMapperDefault;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase.ExceptionMapperJsonb;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase.ExceptionMapperUndeclaredThrowable;
import org.jepria.server.service.rest.jersey.validate.ExceptionMapperValidation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
class FeatureJaxrsAdapterNewTest extends JerseyTest {

  @Mock
  FeatureService service;

//  @Mock
//  FeatureServiceFactory factory;

  @Override
  protected Application configure() {
    enable(TestProperties.DUMP_ENTITY);
    enable(TestProperties.LOG_TRAFFIC);
//    ApplicationConfig config = new ApplicationConfig();
//    return config;
    ResourceConfig config = new ResourceConfig(FeatureJaxrsAdapter.class);

    config.register(JsonBindingProvider.class);
//    config.register(HttpBasicDynamicFeature.class);
//    config.register(RolesAllowedDynamicFeature.class);
//    config.register(JepSecurityContextBinder.class);
    config.register(XCacheControlFilter.class);
    config.register(ExceptionMapperJsonb.class);
    config.register(ExceptionMapperUndeclaredThrowable.class);
    config.register(ExceptionMapperDefault.class);
    config.register(MetaInfoResource.class);
    config.register(ExceptionMapperValidation.class);
//    config.register(JaxrsCorsFilter.class);
    return config;
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

  @Disabled
  @Test
  void getFeatureOperator() {
    List<OptionDto<Integer>> expectedOptions = new ArrayList<>();
    OptionDto<Integer> optionDto = new OptionDto<>();
    optionDto.setValue(12);
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(service.getFeatureOperator()).thenReturn(expectedOptions);

//    when(factory.getService()).thenReturn(service);

//    FeatureServerFactory.setService(this.service);

    Response response = target("/feature/option/feature-operator").request(MediaType.APPLICATION_JSON_TYPE).get();

    String respStr = response.readEntity(String.class);
    Assertions.assertEquals(200, response.getStatus(), "should return 200");
  }

  @Disabled
  @Test
  void getFeatureStatus() {
    List<OptionDto<String>> expectedOptions = new ArrayList<>();
    OptionDto<String>       optionDto       = new OptionDto<>();
    optionDto.setValue("Value");
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(service.getFeatureStatus()).thenReturn(expectedOptions);


    Response response = target("/feature/option/feature-status").request(MediaType.APPLICATION_JSON_TYPE).get();

    String respStr = response.readEntity(String.class);
    Assertions.assertEquals(200, response.getStatus(), "should return 200");
  }

  @Disabled
  @Test
  void getRecordById() {

    FeatureDto expectedDto = new FeatureDto();

    expectedDto.setFeatureName("FeatureName");
    expectedDto.setFeatureNameEn("FeatureNameEn");
    expectedDto.setDescription("Description");

    Response response = target("/feature/11").request(MediaType.APPLICATION_JSON_TYPE).get();
    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    FeatureDto dto = response.readEntity(FeatureDto.class);


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