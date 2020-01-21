package com.technology.jep.jepriashowcase.featureprocess.auto;

import com.technology.jep.jepria.auto.module.JepRiaModuleAutoImpl;
import com.technology.jep.jepria.auto.module.page.JepRiaModulePage;

import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessAutomationConstant.*;

public class FeatureProcessAutoImpl extends JepRiaModuleAutoImpl<JepRiaModulePage>
    implements FeatureProcessAuto {
  
  public FeatureProcessAutoImpl() {
    super(new JepRiaModulePage());
  }
  @Override
  public void setMaxRowCount(String maxRowCount) {
    setFieldValue(FEATUREPROCESS_MAXROWCOUNT_DETAILFORM_FIELD_ID, maxRowCount);
  }

  @Override
  public void setFeatureStatusCode(String featureStatusName) {
    selectComboBoxMenuItem(FEATUREPROCESS_FEATURE_STATUS_CODE_DETAILFORM_FIELD_ID, featureStatusName);
  }

  @Override
  public String getMaxRowCount() {
    return getFieldValue(FEATUREPROCESS_MAXROWCOUNT_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getFeatureStatusName() {
    return getFieldValue(FEATUREPROCESS_FEATURE_STATUS_CODE_DETAILFORM_FIELD_ID);
  }

  @Override
  public void fillSearchForm(
      String featureStatusName,
      String maxRowCount) {
    setFeatureStatusCode(featureStatusName);
    setMaxRowCount(maxRowCount);
  }

  @Override
  public void fillCreateForm(String featureStatusName) {
    setFeatureStatusCode(featureStatusName);
  }

}
