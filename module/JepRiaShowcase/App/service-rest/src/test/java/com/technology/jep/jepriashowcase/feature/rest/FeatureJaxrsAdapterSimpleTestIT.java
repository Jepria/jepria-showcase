package com.technology.jep.jepriashowcase.feature.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class FeatureJaxrsAdapterSimpleTestIT {

  private static final String baseUrl          = "http://localhost:8080/jepriashowcase-service-rest/api";
  private static final String operatorName     = "operatorName";
  private static final String operatorPassword = "123";

  private void testValidateGetResponse(String testUrl) {
    RestAssured.given()
        .auth()
        .basic(operatorName, operatorPassword)
        .when()
        .get(testUrl)
        .then()
        .assertThat()
        .statusCode(200);
  }

  @Test
  public void getFeatureOperator() {
    String testUrl = baseUrl + "/feature/option/feature-operator";
    testValidateGetResponse(testUrl);
  }

  @Test
  public void getFeatureStatus() {
    String testUrl = baseUrl + "/feature/option/feature-status";
    testValidateGetResponse(testUrl);
  }

  @Test
  public void getRecordByIdSimpleTest() {
    String testUrl = baseUrl + "/feature/1";
    testValidateGetResponse(testUrl);
  }

}
