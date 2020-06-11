package com.technology.jep.jepriashowcase.feature.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jepria.server.data.SearchRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

public class FeatureJaxrsAdapterTestIT {

  private static final String ENTITY_URL = "http://localhost:8080/jepriashowcase-service-rest/api/feature";
  private static final String OPERATOR_NAME = "name";
  private static final String OPERATOR_PASSWORD = "password";

  @Disabled
  @Test
  public void getRecordByIdTest() {
    Integer recordId = 1; //TODO: set record ID for test

    String testUrl = ENTITY_URL + "/feature/" + recordId;
    Response response = RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD).when().get(testUrl);
    Assertions.assertEquals(200, response.statusCode());

    //TODO: check response DTO
  }

  @Disabled
  @Test
  public void updateRecordTest() {
    Integer recordId = 0; //TODO: set record ID for test

    String testUrl = ENTITY_URL + "/" + recordId;

    FeatureDto testDto = new FeatureDto();
    //TODO: set DTO fields

    RequestSpecification requestPost =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("Content-Type", "application/json")
                    .body(testDto);

    Response postResponse = requestPost.post(testUrl);

    int statusCode = postResponse.statusCode();
    Assertions.assertEquals(200, statusCode);

    //TODO: check response DTO
  }

  @Disabled
  @Test
  public void deleteRecordTest() {
    Integer recordId = 0; //TODO: set record ID for test

    String testUrl = ENTITY_URL + "/" + recordId;

    RequestSpecification requestDelete =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD);

    Response deleteResponse = requestDelete.delete(testUrl);

    int statusCode = deleteResponse.statusCode();
    Assertions.assertEquals(200, statusCode); //TODO: maybe should be 204
  }

  @Disabled
  @Test
  public void createRecordTest() {
    String testUrl = ENTITY_URL;

    FeatureDto testDto = new FeatureDto();
    //TODO: set DTO fields

    RequestSpecification requestPost =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("Content-Type", "application/json")
                    .body(testDto);

    Response postResponse = requestPost.post(testUrl);

    int statusCode = postResponse.statusCode();
    Assertions.assertEquals(201, statusCode);
  }

  @Disabled
  @Test
  public void getFeatureOperatorTest() {
    String testUrl = ENTITY_URL + "/option/feature-operator";

    RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .when()
            .get(testUrl)
            .then()
            .assertThat()
            .statusCode(200);
  }

  @Disabled
  @Test
  public void getFeatureStatusTest() {
    String testUrl = ENTITY_URL + "/option/feature-status";

    RestAssured.given()
            .auth()
            .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
            .when()
            .get(testUrl)
            .then()
            .assertThat()
            .statusCode(200);
  }

  @Disabled
  @Test
  public void setFeatureResponsibleTest() {
    Integer recordId = 1; //TODO: set record ID for test
    Integer responsibleId = 1;

    String testUrl = ENTITY_URL + "/" + recordId + "/set-feature-responsible?responsibleId=" + responsibleId;

    RequestSpecification requestPost =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD);

    Response postResponse = requestPost.post();

    int statusCode = postResponse.statusCode();
    Assertions.assertEquals(200, statusCode);
  }

  private String postSearchRequest(SearchRequestDto<FeatureSearchDto> searchRequestDto, String location) {

    RequestSpecification request =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("content-type", MediaType.APPLICATION_JSON + ";charset=utf-8")
                    .body(searchRequestDto);

    Response postResponse = request.post(location);
    Assertions.assertEquals(201, postResponse.statusCode());

    return postResponse.getHeader("location");
  }

  private SearchRequestDto<FeatureSearchDto> getSearchRequest(String searchLocation) {
    RequestSpecification request =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD);

    Response response = request.get(searchLocation);
    Assertions.assertEquals(200, response.getStatusCode());

    return new Gson().fromJson(response.asString(), SearchRequestDto.class);
  }

  private Integer getFeatureSearchResultSetSize(String location) {
    String testUrl = location + "/resultset-size";

    RequestSpecification request =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD);

    Response response = request.get(testUrl);
    Assertions.assertEquals(200, response.getStatusCode());

    return response.getBody().as(Integer.TYPE);
  }

  private FeatureDto getFeatureResult(String location) {
    RequestSpecification request =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD);

    Response response = request.get(location);

    Assertions.assertEquals(200, response.statusCode());

    Gson gson = new GsonBuilder().create();
    return gson.fromJson(response.asString(), FeatureDto.class);
  }

  @Disabled
  @Test
  public void featureSearchTest() {
    String testUrl = ENTITY_URL + "/search";

    SearchRequestDto<FeatureSearchDto> searchRequestDto = new SearchRequestDto<>();

    FeatureSearchDto searchDto = new FeatureSearchDto();
    searchDto.setFeatureId(151);
    searchRequestDto.setTemplate(searchDto);

    String searchLocation = postSearchRequest(searchRequestDto, testUrl);

    SearchRequestDto<FeatureSearchDto> readRequest = getSearchRequest(searchLocation);
    //TODO: compare SearchRequestDto-s

    if (getFeatureSearchResultSetSize(searchLocation) > 0) {
      getFeatureResult(searchLocation);
    }

  }
}