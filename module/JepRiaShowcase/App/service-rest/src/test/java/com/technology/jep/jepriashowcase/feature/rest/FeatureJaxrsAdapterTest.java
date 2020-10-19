package com.technology.jep.jepriashowcase.feature.rest;

import com.google.gson.Gson;
import com.technology.jep.jepriashowcase.feature.FeatureRecordDefinition;
import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.FeatureServiceImpl;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.jepria.server.data.OptionDto;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.jepria.server.service.rest.jersey.ApplicationConfigBase;
import org.jepria.server.service.security.PrincipalImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureJaxrsAdapterTest extends JerseyTest {

  private static FeatureDao mockedDao;
  private static FeatureServerFactory mockedFactory;

  private class TestApplicationConfig extends ApplicationConfigBase {
    public TestApplicationConfig() {
      register(FeatureJaxrsAdapter.class);
      register(new AbstractBinder() {
        @Override
        protected void configure() {
          bind(mockedDao).to(FeatureDao.class);
        }
      });
      register(new AbstractBinder() {
        @Override
        protected void configure() {
          bind(mockedFactory).to(FeatureServerFactory.class);
        }
      });
      register((ContainerRequestFilter) containerRequestContext -> containerRequestContext.setSecurityContext(new SecurityContext() {
        @Override
        public Principal getUserPrincipal() {
          return new PrincipalImpl("user", 123);
        }

        @Override
        public boolean isUserInRole(String s) {
          return true;
        }

        @Override
        public boolean isSecure() {
          return false;
        }

        @Override
        public String getAuthenticationScheme() {
          return "BASIC";
        }
      }));
    }

    @Override
    protected void registerHttpBasicDynamicFeature() {
    }

    @Override
    protected void registerRolesAllowedDynamicFeature() {
    }
  }

  @Override
  protected Application configure() {
    enable(TestProperties.DUMP_ENTITY);
    enable(TestProperties.LOG_TRAFFIC);

    mockedDao = Mockito.mock(FeatureDao.class);
    mockedFactory = Mockito.mock(FeatureServerFactory.class);

    when(mockedFactory.getService()).thenReturn(new FeatureServiceImpl(mockedDao));
    when(mockedFactory.getEntityService()).thenReturn(new EntityServiceImpl(mockedDao, new FeatureRecordDefinition()));
//    when(mockedFactory.getSearchService()).thenReturn(new SearchServiceImpl(mockedDao));

    return new TestApplicationConfig();
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
    List<OptionDto<Integer>> expectedOptions = new ArrayList<>();
    OptionDto<Integer> optionDto = new OptionDto<>();
    optionDto.setValue(12);
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(mockedDao.getFeatureOperator()).thenReturn(expectedOptions);

    Response response = target("/feature/option/feature-operator").request(MediaType.APPLICATION_JSON_TYPE).get();

    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    String respStr = response.readEntity(String.class).replaceAll("\\s+", "");
    Gson gson = new Gson();
    Assertions.assertEquals(gson.toJson(expectedOptions), respStr);
  }

  @Test
  void getFeatureStatus() {
    List<OptionDto<String>> expectedOptions = new ArrayList<>();
    OptionDto<String> optionDto = new OptionDto<>();
    optionDto.setValue("Value");
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(mockedDao.getFeatureStatus()).thenReturn(expectedOptions);

    Response response = target("/feature/option/feature-status").request(MediaType.APPLICATION_JSON_TYPE).get();

    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    String respStr = response.readEntity(String.class).replaceAll("\\s+", "");
    Gson gson = new Gson();
    Assertions.assertEquals(gson.toJson(expectedOptions), respStr);
  }

  @Test
  void getRecordById() {
    FeatureDto expectedDto = new FeatureDto();

    expectedDto.setFeatureName("FeatureName");
    expectedDto.setFeatureNameEn("FeatureNameEn");
    expectedDto.setDescription("Description");
    List<FeatureDto> list = Collections.singletonList(expectedDto);

    when(mockedDao.findByPrimaryKey(any(), any())).thenReturn((List) list);

    Response response = target("/feature/11").request(MediaType.APPLICATION_JSON_TYPE).get();

    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    String respStr = response.readEntity(String.class).replaceAll("\\s+", "");
    Gson gson = new Gson();
    Assertions.assertEquals(gson.toJson(expectedDto), respStr);
  }

  @Test
  void create() {

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