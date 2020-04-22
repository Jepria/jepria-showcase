package com.technology.jep.jepriashowcase.feature.dto;

import org.jepria.server.data.OptionDto;
import org.jepria.server.data.PrimaryKey;

import java.util.Date;

public class FeatureDto {
  @PrimaryKey
  private Integer featureId;
  private String featureName;
  private String featureNameEn;
  private String description;
  private OptionDto<String> featureStatus;
  private Date dateIns;
  private OptionDto<Integer> author;
  private OptionDto<Integer> responsible;

  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }

  public String getFeatureName() {
    return featureName;
  }

  public void setFeatureName(String featureName) {
    this.featureName = featureName;
  }

  public String getFeatureNameEn() {
    return featureNameEn;
  }

  public void setFeatureNameEn(String featureNameEn) {
    this.featureNameEn = featureNameEn;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OptionDto<String> getFeatureStatus() {
    return featureStatus;
  }

  public void setFeatureStatus(OptionDto<String> featureStatus) {
    this.featureStatus = featureStatus;
  }

  public Date getDateIns() {
    return dateIns;
  }

  public void setDateIns(Date dateIns) {
    this.dateIns = dateIns;
  }

  public OptionDto<Integer> getAuthor() {
    return author;
  }

  public void setAuthor(OptionDto<Integer> author) {
    this.author = author;
  }

  public OptionDto<Integer> getResponsible() {
    return responsible;
  }

  public void setResponsible(OptionDto<Integer> responsible) {
    this.responsible = responsible;
  }

}
