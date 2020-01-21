package com.technology.jep.jepriashowcase.featureprocess.auto;

import com.technology.jep.jepria.auto.module.JepRiaModuleAuto;

public interface FeatureProcessAuto extends JepRiaModuleAuto {

  /**
   * Заполнение полей и получение их значений 
   */
  void setMaxRowCount(String maxRowCount);
  void setFeatureStatusCode(String featureStatusCode);
  
  String getMaxRowCount();
  String getFeatureStatusName();

  /**
   * Заполнение детальных форм
   */
  void fillSearchForm(
      String featureStatusName,
      String maxRowCount);
  
  void fillCreateForm(
      String featureStatusName);
}
