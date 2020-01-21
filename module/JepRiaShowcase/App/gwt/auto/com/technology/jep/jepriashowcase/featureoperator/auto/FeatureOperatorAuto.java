package com.technology.jep.jepriashowcase.featureoperator.auto;

import com.technology.jep.jepria.auto.module.JepRiaModuleAuto;

public interface FeatureOperatorAuto extends JepRiaModuleAuto {

  /**
   * Заполнение полей и получение их значений 
   */
  void setMaxRowCount(String maxRowCount);
  void setFeatureOperatorIdToCombobox(String featureOperatorName);
  void setFeatureOperatorNameToText(String featureOperatorName);
  
  String getMaxRowCount();
  String getFeatureOperatorNameFromText();
  String getFeatureOperatorNameFromComboBox();

  /**
   * Заполнение детальных форм
   */
  void fillSearchForm(
      String featureOperatorName,
      String maxRowCount);
  
  void fillCreateForm(
      String featureOperatorName);
}
