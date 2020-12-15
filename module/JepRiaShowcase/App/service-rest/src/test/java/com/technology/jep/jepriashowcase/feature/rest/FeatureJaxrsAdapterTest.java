package com.technology.jep.jepriashowcase.feature.rest;

import com.google.gson.Gson;
import com.technology.jep.jepriashowcase.feature.FeatureFieldNames;
import com.technology.jep.jepriashowcase.feature.FeatureRecordDefinition;
import com.technology.jep.jepriashowcase.feature.FeatureServerFactory;
import com.technology.jep.jepriashowcase.feature.FeatureServiceImpl;
import com.technology.jep.jepriashowcase.feature.dao.FeatureDao;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.main.rest.JepriaTest;
import com.technology.jep.jepriashowcase.main.rest.TestApplicationConfig;
import com.technology.jep.jepriashowcase.main.rest.api.FeatureApiImpl;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.test.TestProperties;
import org.jepria.server.data.OptionDto;
import org.jepria.server.service.rest.EntityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.*;

import static org.mockito.Mockito.when;

class FeatureJaxrsAdapterTest extends JepriaTest {

  @Mock
  private FeatureDao mockedDao;

  @Override
  protected Application configure() {
    MockitoAnnotations.openMocks(this);

    // TODO: Where is the log?
    enable(TestProperties.DUMP_ENTITY);
    enable(TestProperties.LOG_TRAFFIC);

    // TODO: Необходимо найти способ, чтобы мокать только getDao, а не сервисы.
    final FeatureServerFactory mockedFactory = Mockito.mock(FeatureServerFactory.class);
    when(mockedFactory.getService()).thenReturn(new FeatureServiceImpl(mockedDao));
    when(mockedFactory.getEntityService()).thenReturn(new EntityServiceImpl(mockedDao, new FeatureRecordDefinition()));
//    when(mockedFactory.getSearchService()).thenReturn(new SearchServiceImpl(mockedDao));

    return new TestApplicationConfig(JepriaTest.credential) {
      {
        register(FeatureJaxrsAdapter.class);

        register(new AbstractBinder() {
          @Override
          protected void configure() {
            bind(mockedFactory).to(FeatureServerFactory.class);
          }
        });
      }
    };
  }

  @Test
  void success_getFeatureOperator() {
    // arrange
    List<OptionDto<Integer>> expectedOptions = new ArrayList<>();
    OptionDto<Integer> optionDto = new OptionDto<>();
    optionDto.setValue(12);
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(mockedDao.getFeatureOperator()).thenReturn(expectedOptions);

    // act
    Response response = new FeatureApiImpl(target()).getFeatureOperator();

    // assert
    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    String actual = response.readEntity(String.class).replaceAll("\\s+", "");
    Assertions.assertEquals(new Gson().toJson(expectedOptions), actual);
  }

  @Test
  void success_getFeatureStatus() {
    // arrange
    List<OptionDto<String>> expectedOptions = new ArrayList<>();
    OptionDto<String> optionDto = new OptionDto<>();
    optionDto.setValue("Value");
    optionDto.setName("Name");
    expectedOptions.add(optionDto);

    when(mockedDao.getFeatureStatus()).thenReturn(expectedOptions);

    // act
    Response response = new FeatureApiImpl(target()).getFeatureStatus();

    // assert
    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    String actual = response.readEntity(String.class).replaceAll("\\s+", "");
    Assertions.assertEquals(new Gson().toJson(expectedOptions), actual);
  }

  @Test
  void success_getRecordById() {
    // arrange
    FeatureDto expectedDto = new FeatureDto();
    expectedDto.setFeatureName("FeatureName");
    expectedDto.setFeatureNameEn("FeatureNameEn");
    expectedDto.setDescription("Description");
    List<FeatureDto> list = Collections.singletonList(expectedDto);

    final Integer featureId = 11;
    final Map<String, Integer> primaryKey = new HashMap<>();
    primaryKey.put(FeatureFieldNames.FEATURE_ID, featureId);
    when(mockedDao.findByPrimaryKey(
        Mockito.eq(primaryKey), Mockito.eq(credential.getOperatorId())
    )).thenReturn((List) list);

    // act
    Response response = new FeatureApiImpl(target()).getRecordById(featureId);

    // assert
    Assertions.assertEquals(200, response.getStatus(), "should return 200");

    String actual = response.readEntity(String.class).replaceAll("\\s+", "");
    Assertions.assertEquals(new Gson().toJson(expectedDto), actual);
  }


  @Disabled
  @Test
  void setFeatureResponsible() {
  }

  @Disabled
  @Test
  void create() {
  }

  @Disabled
  @Test
  void deleteRecordById() {
  }

  @Disabled
  @Test
  void update() {
  }

  @Disabled
  @Test
  void postSearch() {
  }

  @Disabled
  @Test
  void getSearchRequest() {
  }

  @Disabled
  @Test
  void getSearchResultsetSize() {
  }

  @Disabled
  @Test
  void getResultset() {
  }

  @Disabled
  @Test
  void getResultsetPaged() {
  }
}