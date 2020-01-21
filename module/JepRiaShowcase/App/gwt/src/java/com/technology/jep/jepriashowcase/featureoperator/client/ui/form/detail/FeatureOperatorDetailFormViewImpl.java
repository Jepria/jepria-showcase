package com.technology.jep.jepriashowcase.featureoperator.client.ui.form.detail;
 
import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorClientConstant.*;
import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorAutomationConstant.*;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.FEATURE_OPERATOR_ID;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.FEATURE_OPERATOR_NAME;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.OPERATOR_NAME;

import com.technology.jep.jepria.client.ui.form.detail.StandardDetailFormViewImpl;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.client.widget.field.multistate.JepDateField;
import com.technology.jep.jepria.client.widget.field.multistate.JepIntegerField;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextField;

public class FeatureOperatorDetailFormViewImpl extends StandardDetailFormViewImpl
  implements FeatureOperatorDetailFormView { 
  
   public FeatureOperatorDetailFormViewImpl() {
     
     int featureOperatorFieldWidth = 400;
     
     // "Дизайнерская" часть - freelayout.
    JepTextField featureOperatorNameTextField = new JepTextField(
        FEATUREOPERATOR_FEATUREOPERATORNAME_DETAILFORM_FIELD_ID,
        featureOperatorText.featureOperator_detail_feature_operator_name());
    featureOperatorNameTextField.setFieldWidth(featureOperatorFieldWidth);
    panel.add(featureOperatorNameTextField);
    
    JepComboBoxField featureOperatorIdComboBoxField = new JepComboBoxField(
        FEATUREOPERATOR_FEATUREOPERATORID_DETAILFORM_FIELD_ID,
        featureOperatorText.featureOperator_detail_feature_operator_id());
    featureOperatorIdComboBoxField.setEmptyText(featureOperatorText.featureOperator_detail_enter_first_symbols_operator_name());
    featureOperatorIdComboBoxField.setTypingTimeoutMinTextSize(FEATUREOPERATOR_OPERATOR_ID_COMBOBOX_MIN_CHAR);
    featureOperatorIdComboBoxField.setTypingTimeout(1000);
    featureOperatorIdComboBoxField.setFieldWidth(featureOperatorFieldWidth);
    panel.add(featureOperatorIdComboBoxField);
    
    JepDateField dateInsDateField = new JepDateField(featureOperatorText.featureOperator_detail_date_ins());
    panel.add(dateInsDateField);
    
    JepTextField operatorNameTextField = new JepTextField(featureOperatorText.featureOperator_detail_operator_name());
    operatorNameTextField.setFieldWidth(featureOperatorFieldWidth);
    panel.add(operatorNameTextField);
    
    JepIntegerField maxRowCountField = new JepIntegerField(
        FEATUREOPERATOR_MAXROWCOUNT_DETAILFORM_FIELD_ID,
        featureOperatorText.featureOperator_detail_max_row_count());
    maxRowCountField.setMaxLength(4);
    maxRowCountField.setFieldWidth(55);
    panel.add(maxRowCountField);
    
    // "Функциональная" часть - указываем управляющему классу по умолчанию с какими полями нужно работать.
    fields.put(FEATURE_OPERATOR_NAME, featureOperatorNameTextField);
    fields.put(FEATURE_OPERATOR_ID, featureOperatorIdComboBoxField);
    fields.put(DATE_INS, dateInsDateField);
    fields.put(OPERATOR_NAME, operatorNameTextField);
    fields.put(MAX_ROW_COUNT, maxRowCountField);
    
  }
  
}
