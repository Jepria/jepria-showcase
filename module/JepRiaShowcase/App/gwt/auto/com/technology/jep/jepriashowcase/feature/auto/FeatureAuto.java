package com.technology.jep.jepriashowcase.feature.auto;

import com.technology.jep.jepria.auto.module.JepRiaModuleAuto;
import com.technology.jep.jepria.client.ui.WorkstateEnum;

/**
 * Автоматизация модуля Feature.
 */
public interface FeatureAuto extends JepRiaModuleAuto {

  /**
   * Заполнение полей и получение их значений 
   */
  void setFeatureId(String featureId);
  void setFeatureName(String featureName);
  void setFeatureNameEn(String featureNameEn);
  void setWorkSequenceFrom(String workSequenceFrom);
  void setWorkSequenceTo(String workSequenceTo);
  void setWorkSequence(String workSequence);
  void setFromDateIns(String fromDateIns);
  void setToDateIns(String toDateIns);
  void setDescription(String description);
  void setMaxRowCount(String maxRowCount);
  void setAuthorId(String authorId);
  void setResponsibleId(String responsibleName);
  void setFeatureStatusCode(String StatusCode);
  void setFeatureStatusCodeList(String[] menuItems);
  
  String getFeatureId();
  String getFeatureName();
  String getFeatureNameEn();
  String getWorkSequenceFrom();
  String getWorkSequenceTo();
  String getWorkSequence();
  String getFromDateIns();
  String getToDateIns();
  String getDescription();
  String getMaxRowCount();
  String getAuthorId();
  String getResponsibleName();
  String getFeatureStatusName();

  /**
   * Заполнение детальных форм
   */
  void fillSearchForm(
      String featureId,
      String featureName,
      String featureNameEn,
      String workSequenceFrom,
      String workSequenceTo,
      String fromDateIns,
      String toDateIns,
      String maxRowCount);
  
  void fillCreateForm(
      String featureName,
      String featureNameEn,
      String description);

  void fillEditForm(
      String featureName,
      String featureNameEn,
      String workSequence);
  
  WorkstateEnum confirmErrorMessageBox();
  boolean isDeleteButtonClickable();
}
