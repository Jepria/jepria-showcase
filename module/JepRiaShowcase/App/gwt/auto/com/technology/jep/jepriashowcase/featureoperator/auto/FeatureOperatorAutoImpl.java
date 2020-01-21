package com.technology.jep.jepriashowcase.featureoperator.auto;

import com.technology.jep.jepria.auto.module.JepRiaModuleAutoImpl;
import com.technology.jep.jepria.auto.module.page.JepRiaModulePage;

import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorAutomationConstant.*;
import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorClientConstant.*;

public class FeatureOperatorAutoImpl extends JepRiaModuleAutoImpl<JepRiaModulePage>
    implements FeatureOperatorAuto {
  
  public FeatureOperatorAutoImpl() {
    super(new JepRiaModulePage());
  }
  @Override
  public void setMaxRowCount(String maxRowCount) {
    setFieldValue(FEATUREOPERATOR_MAXROWCOUNT_DETAILFORM_FIELD_ID, maxRowCount);
  }

  @Override
  public String getMaxRowCount() {
    return getFieldValue(FEATUREOPERATOR_MAXROWCOUNT_DETAILFORM_FIELD_ID);
  }

  @Override
  public void fillSearchForm(
      String featureOperatorName,
      String maxRowCount) {
    setFeatureOperatorNameToText(featureOperatorName);
    setMaxRowCount(maxRowCount);
  }

  @Override
  public void fillCreateForm(String featureOperatorName) {
    setFeatureOperatorIdToCombobox(featureOperatorName);
  }
  @Override
  public void setFeatureOperatorIdToCombobox(String featureOperatorName) {
    selectComboBoxMenuItemWithCharByCharReloadingOptions(
        FEATUREOPERATOR_FEATUREOPERATORID_DETAILFORM_FIELD_ID, 
        featureOperatorName,
        FEATUREOPERATOR_OPERATOR_ID_COMBOBOX_MIN_CHAR);
  }
  @Override
  public void setFeatureOperatorNameToText(String featureOperatorName) {
    setFieldValue(FEATUREOPERATOR_FEATUREOPERATORNAME_DETAILFORM_FIELD_ID, featureOperatorName);
  }
  @Override
  public String getFeatureOperatorNameFromText() {
    return getFieldValue(FEATUREOPERATOR_FEATUREOPERATORNAME_DETAILFORM_FIELD_ID);
  }
  @Override
  public String getFeatureOperatorNameFromComboBox() {
    return getFieldValue(FEATUREOPERATOR_FEATUREOPERATORID_DETAILFORM_FIELD_ID);
  }

}
