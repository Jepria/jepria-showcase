package com.technology.jep.jepriashowcase.feature.dto;

import org.jepria.server.data.OptionDto;
import org.jepria.server.data.PrimaryKey;

import java.util.Date;

public class FeatureDto implements Cloneable{
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FeatureDto)) return false;

    FeatureDto that = (FeatureDto) o;

    if (getFeatureId() != null ? !getFeatureId().equals(that.getFeatureId()) : that.getFeatureId() != null)
      return false;
    if (getFeatureName() != null ? !getFeatureName().equals(that.getFeatureName()) : that.getFeatureName() != null)
      return false;
    if (getFeatureNameEn() != null ? !getFeatureNameEn().equals(that.getFeatureNameEn()) : that.getFeatureNameEn() != null)
      return false;
    if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
      return false;
    if (getFeatureStatus() != null ? !getFeatureStatus().equals(that.getFeatureStatus()) : that.getFeatureStatus() != null)
      return false;
    if (getDateIns() != null ? !getDateIns().equals(that.getDateIns()) : that.getDateIns() != null) return false;
    if (getAuthor() != null ? !getAuthor().equals(that.getAuthor()) : that.getAuthor() != null) return false;
    return getResponsible() != null ? getResponsible().equals(that.getResponsible()) : that.getResponsible() == null;
  }

  @Override
  public int hashCode() {
    int result = getFeatureId() != null ? getFeatureId().hashCode() : 0;
    result = 31 * result + (getFeatureName() != null ? getFeatureName().hashCode() : 0);
    result = 31 * result + (getFeatureNameEn() != null ? getFeatureNameEn().hashCode() : 0);
    result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
    result = 31 * result + (getFeatureStatus() != null ? getFeatureStatus().hashCode() : 0);
    result = 31 * result + (getDateIns() != null ? getDateIns().hashCode() : 0);
    result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
    result = 31 * result + (getResponsible() != null ? getResponsible().hashCode() : 0);
    return result;
  }
}
