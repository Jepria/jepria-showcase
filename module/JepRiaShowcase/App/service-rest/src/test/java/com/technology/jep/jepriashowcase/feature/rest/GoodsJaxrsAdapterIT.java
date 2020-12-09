package com.technology.jep.jepriashowcase.feature.rest;

import com.technology.jep.jepriashowcase.goods.dto.GoodsCreateDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.jepria.compat.shared.JepRiaConstant.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoodsJaxrsAdapterIT {
  @Test
  public void uploadDownloadTest() throws IOException {
    GoodsCreateDto createDto = new GoodsCreateDto();
    createDto.setSupplierId(3);
    createDto.setGoodsName("test");
    createDto.setGoodsTypeCode("BOOK");
    createDto.setMotivationTypeCode("MONTH");
    createDto.setUnitCode("ITEM");
    createDto.setPurchasingPrice(BigDecimal.TEN);
    createDto.setGoodsPhotoExtension("jpeg");
    createDto.setGoodsPhotoMimeType("image/jpeg");
    createDto.setGoodsPortfolioExtension("jpeg");
    createDto.setGoodsPortfolioMimeType("image/jpeg");
    Response response = RestAssured.given()
        .auth()
        .basic("name", "password")
        .contentType(ContentType.JSON)
        .body(createDto)
        .post("http://localhost:8080/jepriashowcase-service-rest/api/goods");
    response.then().assertThat().statusCode(201);
    String createdUrl = response.getHeader("Location");
    String id = createdUrl.substring(createdUrl.lastIndexOf("/") + 1);
    File file = new File(GoodsJaxrsAdapterIT.class.getClassLoader().getResource("test.jpg").getFile());
    Response res = RestAssured.given()
        .formParam("primaryKey", "goods_id=" + id)
        .multiPart("goods_photo", file,"application/json")
        .when().post("http://localhost:8080/jepriashowcase-service-rest/api/goods/upload");
    res.then().assertThat().statusCode(201);
    Response res1 = RestAssured.given()
        .formParam("primaryKey", "goods_id=" + id)
        .multiPart("goods_portfolio", file,"application/json")
        .when().post("http://localhost:8080/jepriashowcase-service-rest/api/goods/upload");
    res1.then().assertThat().statusCode(201);
    Response res2 = RestAssured.given()
        .param(DOWNLOAD_FILE_NAME, "test")
        .param(DOWNLOAD_EXTENSION, "jpg")
        .param(DOWNLOAD_MIME_TYPE, "image/jpeg")
        .param(DOWNLOAD_RECORD_KEY, "goods_id=" + id)
        .param(DOWNLOAD_FIELD_NAME, "goods_photo")
        .param(DOWNLOAD_CONTENT_DISPOSITION, "attachment")
        .when().get("http://localhost:8080/jepriashowcase-service-rest/api/goods/download");
    res2.then().assertThat().statusCode(200);
    assertTrue(IOUtils.contentEquals(res2.asInputStream(), GoodsJaxrsAdapterIT.class.getClassLoader().getResourceAsStream("test.jpg")));
    Response res3 = RestAssured.given()
        .auth()
        .basic("name", "password")
        .when().delete("http://localhost:8080/jepriashowcase-service-rest/api/goods/" + id);
    res3.then().assertThat().statusCode(200);
  }
}
