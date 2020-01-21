package com.technology.jep.jepriashowcase.featureprocess.dto;

import org.jepria.server.data.PrimaryKey;

import java.util.Date;

public class FeatureProcessDto {
  @PrimaryKey
  private Integer featureProcessId;
  @PrimaryKey
  private Integer featureId;
  private String featureStatusCode;
  private String featureStatusName;
  private Date dateIns;

  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }

  public Integer getFeatureProcessId() {
    return featureProcessId;
  }

  public void setFeatureProcessId(Integer featureProcessId) {
    this.featureProcessId = featureProcessId;
  }

  public String getFeatureStatusCode() {
    return featureStatusCode;
  }

  public void setFeatureStatusCode(String featureStatusCode) {
    this.featureStatusCode = featureStatusCode;
  }

  public String getFeatureStatusName() {
    return featureStatusName;
  }

  public void setFeatureStatusName(String featureStatusName) {
    this.featureStatusName = featureStatusName;
  }

  public Date getDateIns() {
    return dateIns;
  }

  public void setDateIns(Date dateIns) {
    this.dateIns = dateIns;
  }
}
