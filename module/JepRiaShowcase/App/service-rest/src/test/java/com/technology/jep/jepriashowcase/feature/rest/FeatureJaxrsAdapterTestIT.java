package com.technology.jep.jepriashowcase.feature.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jepria.server.data.OptionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

public class FeatureJaxrsAdapterTestIT {

  private static final String ENTITY_URL = "http://localhost:8080/jepriashowcase-service-rest/api/feature";
  private static final String OPERATOR_NAME = "username";
  private static final String OPERATOR_PASSWORD = "password";

  /**
   * @param dto
   * @return {@code Location} response header value
   */
  private String createRecord(Object dto) {
    RequestSpecification requestPost =
            RestAssured.given().auth()
                    .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("Content-Type", "application/json")
                    .body(dto);

    Response postResponse = requestPost.post(ENTITY_URL);

    Assertions.assertEquals(201, postResponse.statusCode());
    return postResponse.getHeader("Location");
  }

  private void updateRecord(Object dto, String location) {
    RequestSpecification requestPost =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("Content-Type", "application/json")
                    .body(dto);
    Response postResponse = requestPost.put(location);

    int statusCode = postResponse.statusCode();
    Assertions.assertEquals(200, statusCode);
  }

  private FeatureDto getFeature(String location) {
    Response response = RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD).when().get(location);
    Assertions.assertEquals(200, response.statusCode());
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    return gson.fromJson(response.asString(), FeatureDto.class);
  }

  private void deleteFeature(String location) {
    RequestSpecification requestDelete =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD);

    List<Integer> featureProcessIdList = getFeatureProcessIdList(location);
    featureProcessIdList.sort(Comparator.reverseOrder());
    featureProcessIdList.forEach(featureProcessId -> {
      requestDelete.delete(location + "/feature-process/" + featureProcessId);
    });
    Response deleteResponse = requestDelete.delete(location);
    Assertions.assertEquals(200, deleteResponse.statusCode()); //TODO: maybe should be 204
  }

  @Test
  public void featureCrudTest() {
    FeatureDto dto = createRandomMinimalFeatureDto();
    String location = createRecord(dto);
    FeatureDto createdDto = setMinimalFeatureDto(getFeature(location));
    Assertions.assertTrue(equalsEntities(dto, createdDto), "New dto and created dto are different");

    FeatureDto newDto = createRandomMinimalFeatureDto();
    updateRecord(newDto, location);
    FeatureDto updatedDto = setMinimalFeatureDto(getFeature(location));
    Assertions.assertTrue(equalsEntities(newDto, updatedDto), "New dto for updating and updated dto are different");

    deleteFeature(location);
  }

  private boolean equalsEntities(FeatureDto entity1, FeatureDto entity2) {
    if (entity1 == entity2) {
      return true;
    }

    if (!Objects.equals(entity1.getFeatureId(), entity2.getFeatureId())) {
      return false;
    }
    if (!Objects.equals(entity1.getFeatureName(), entity2.getFeatureName())) {
      return false;
    }
    if (!Objects.equals(entity1.getFeatureNameEn(), entity2.getFeatureNameEn())) {
      return false;
    }
    if (!Objects.equals(entity1.getDescription(), entity2.getDescription())) {
      return false;
    }
    if (!Objects.equals(entity1.getFeatureStatus(), entity2.getFeatureStatus())) {
      return false;
    }
    if (!Objects.equals(entity1.getDateIns(), entity2.getDateIns())) {
      return false;
    }
    if (!Objects.equals(entity1.getAuthor(), entity2.getAuthor())) {
      return false;
    }
    if (!Objects.equals(entity1.getResponsible(), entity2.getResponsible())) {
      return false;
    }

    return true;
  }

  private FeatureDto createRandomFullFeatureDto() {
    FeatureDto featureDto = new FeatureDto();
    featureDto.setFeatureName(getGenerateFeatureString(10, false));
    featureDto.setFeatureNameEn(getGenerateFeatureString(10, false));
    featureDto.setDescription(getGenerateFeatureString(10, false));
    featureDto.setFeatureStatus(getGenerateOptionDtoString(getGenerateFeatureString(10, false)));
    featureDto.setDateIns(new Date(new Random().nextLong()));
    featureDto.setAuthor(getGenerateOptionDtoString(new Random().nextInt(1000)));
    featureDto.setResponsible(getGenerateOptionDtoString(new Random().nextInt(1000)));
    return featureDto;
  }

  private FeatureDto createRandomMinimalFeatureDto() {
    FeatureDto featureDto = new FeatureDto();
    featureDto.setFeatureName(getGenerateFeatureString(10, false));
    featureDto.setFeatureNameEn(getGenerateFeatureString(10, false));
    featureDto.setDescription(getGenerateFeatureString(10, false));
    return featureDto;
  }

  private FeatureDto setMinimalFeatureDto(FeatureDto dto) {
    dto.setFeatureId(null);
    dto.setFeatureStatus(null);
    dto.setDateIns(null);
    dto.setAuthor(null);
    dto.setResponsible(null);
    return dto;
  }

  private String getGenerateFeatureString(int length, boolean russian) {
    String alphabet = russian ? "АБВГДЕЁЖЭИЙКЛМНОПРСТУФХЦЧШЭЮЯ" : "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder sb = new StringBuilder();
    IntStream.range(0, length).forEach(i -> sb.append(alphabet.charAt((int) (Math.random() * alphabet.length()))));
    return sb.toString();
  }

  private <T> OptionDto<T> getGenerateOptionDtoString(T value) {
    OptionDto<T> optionDto = new OptionDto<>();
    optionDto.setName(getGenerateFeatureString(5, true));
    optionDto.setValue(value);
    return optionDto;
  }

  private List<Integer> getFeatureProcessIdList(String location) {
    Response response = RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .when()
            .get(location + "/feature-process");
    JsonElement responseAsJsonElement = new Gson().fromJson(response.asString(), JsonElement.class);
    List<Integer> list = new ArrayList<>();
    if (!responseAsJsonElement.isJsonNull() && responseAsJsonElement.isJsonArray()) {
      responseAsJsonElement.getAsJsonArray().forEach(json -> {
        if (json.isJsonObject()) {
          JsonObject jsonOb = json.getAsJsonObject();
          int featureProcessId = jsonOb.get("featureProcessId").getAsInt();
          list.add(featureProcessId);
        }
      });
      return list;
    }
    return list;
  }

}
