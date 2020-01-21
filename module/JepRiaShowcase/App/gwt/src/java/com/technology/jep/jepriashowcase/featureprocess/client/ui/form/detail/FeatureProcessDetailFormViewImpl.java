package com.technology.jep.jepriashowcase.featureprocess.client.ui.form.detail;
 
import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessAutomationConstant.FEATUREPROCESS_FEATURE_STATUS_CODE_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessAutomationConstant.FEATUREPROCESS_MAXROWCOUNT_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessClientConstant.featureProcessText;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_STATUS_CODE;

import com.technology.jep.jepria.client.ui.form.detail.StandardDetailFormViewImpl;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.client.widget.field.multistate.JepIntegerField;

public class FeatureProcessDetailFormViewImpl extends StandardDetailFormViewImpl
  implements FeatureProcessDetailFormView { 
  
   public FeatureProcessDetailFormViewImpl() {
     
     // "Дизайнерская" часть - freelayout.
    JepComboBoxField featureStatusCodeComboBoxField = new JepComboBoxField(
        FEATUREPROCESS_FEATURE_STATUS_CODE_DETAILFORM_FIELD_ID,
        featureProcessText.featureProcess_detail_feature_status_code());
    panel.add(featureStatusCodeComboBoxField);
    
    JepIntegerField maxRowCountField = new JepIntegerField(
        FEATUREPROCESS_MAXROWCOUNT_DETAILFORM_FIELD_ID,
        featureProcessText.featureProcess_detail_row_count());
    maxRowCountField.setMaxLength(4);
    maxRowCountField.setFieldWidth(55);
    panel.add(maxRowCountField);
    
    // "Функциональная" часть - указываем управляющему классу по умолчанию с какими полями нужно работать.
    fields.put(FEATURE_STATUS_CODE, featureStatusCodeComboBoxField);
    fields.put(MAX_ROW_COUNT, maxRowCountField);
    
  }
  
}
