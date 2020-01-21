package com.technology.jep.jepriashowcase.featureprocess.dto;

public class FeatureProcessCreateDtoInternal {
  private Integer featureId; // foreign key
  private String featureStatusCode;

  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }

  public String getFeatureStatusCode() {
    return featureStatusCode;
  }

  public void setFeatureStatusCode(String featureStatusCode) {
    this.featureStatusCode = featureStatusCode;
  }
}
