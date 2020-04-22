package com.technology.jep.jepriashowcase.feature.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.technology.jep.jepriashowcase.feature.dto.FeatureDto;
import com.technology.jep.jepriashowcase.feature.dto.FeatureSearchDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jepria.server.data.OptionDto;
import org.jepria.server.data.SearchRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.IntStream;

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

  private String createRecord(FeatureDto testDto) {
    RequestSpecification requestPost =
            RestAssured.given().auth()
                    .basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("Content-Type", "application/json")
                    .body(testDto);

    Response postResponse = requestPost.post(ENTITY_URL);

    Assertions.assertEquals(201, postResponse.statusCode());
    return postResponse.getHeader("Location");
  }

  private void updateRecord(FeatureDto testDto, Integer recordId) {
    updateRecord(testDto, ENTITY_URL + "/" + recordId);
  }

  private void updateRecord(FeatureDto testDto, String location) {
    RequestSpecification requestPost =
            RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD)
                    .header("Content-Type", "application/json")
                    .body(testDto);
    Response postResponse = requestPost.put(location);

    int statusCode = postResponse.statusCode();
    Assertions.assertEquals(200, statusCode);
  }

  private FeatureDto getRecord(String location) {
    Response response = RestAssured.given().auth().basic(OPERATOR_NAME, OPERATOR_PASSWORD).when().get(location);
    Assertions.assertEquals(200, response.statusCode());
    Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();
    return gson.fromJson(response.asString(), FeatureDto.class);
  }

  private FeatureDto getRecord(Integer featureId) {
    String testUrl = ENTITY_URL + "/feature/" + featureId;
    return getRecord(testUrl);
  }

  private void deleteRecord(int featureId) {
    deleteRecord(ENTITY_URL + "/" + featureId);
  }


  @Test
  public void featureCrudTest() {
    FeatureDto dto = createRandomMinimalFeatureDto();
    String location = createRecord(dto);
    FeatureDto createdDto = setMinimalFeatureDto(getRecord(location));
    Assertions.assertTrue(equalsEntities(dto, createdDto), "New dto and created dto are different");

    FeatureDto newDto = createRandomMinimalFeatureDto();
    updateRecord(newDto, location);
    FeatureDto updatedDto = setMinimalFeatureDto(getRecord(location));
    Assertions.assertTrue(equalsEntities(newDto, updatedDto), "New dto for updating and updated dto are different");

    deleteRecord(location);
  }

  private boolean equalsEntities(Object obj1, Object obj2) {
    if (obj1 == obj2) {
      return true;
    }

    FeatureDto entity1 = (FeatureDto) obj1;
    FeatureDto entity2 = (FeatureDto) obj2;

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
    return Objects.equals(entity1.getResponsible(), entity2.getResponsible());
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

  private void deleteRecord(String location) {
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

  @Disabled
  @Test
  public void featureGetTest() {
    FeatureDto createdDto = getRecord("sasfaf");

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
