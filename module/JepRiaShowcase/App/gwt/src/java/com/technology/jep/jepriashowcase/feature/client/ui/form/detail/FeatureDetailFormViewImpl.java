package com.technology.jep.jepriashowcase.feature.client.ui.form.detail;

import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_AUTHOR_ID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_DESCRIPTION_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATUREID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURENAMEEN_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURENAME_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURE_STATUS_CODE_LIST_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURE_STATUS_NAME_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FROMDATEINS_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_MAXROWCOUNT_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_TODATEINS_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCEFROM_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCETO_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureClientConstant.FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR;
import static com.technology.jep.jepriashowcase.feature.client.FeatureClientConstant.featureText;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.AUTHOR_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS_FROM;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS_TO;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DESCRIPTION;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME_EN;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_STATUS_CODE_LIST;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_STATUS_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.RESPONSIBLE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE_FROM;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE_TO;

import com.technology.jep.jepria.client.ui.form.detail.StandardDetailFormViewImpl;
import com.technology.jep.jepria.client.widget.field.FieldManager;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.client.widget.field.multistate.JepDateField;
import com.technology.jep.jepria.client.widget.field.multistate.JepIntegerField;
import com.technology.jep.jepria.client.widget.field.multistate.JepListField;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextAreaField;
import com.technology.jep.jepria.client.widget.field.multistate.JepTextField;

public class FeatureDetailFormViewImpl extends StandardDetailFormViewImpl {

  public FeatureDetailFormViewImpl() {
    super(new FieldManager());
    
    JepIntegerField featureIdIntegerField = new JepIntegerField(FEATURE_FEATUREID_DETAILFORM_FIELD_ID,
        featureText.feature_detail_feature_id());
    
    JepTextField featureStatusName = new JepTextField(
        FEATURE_FEATURE_STATUS_NAME_FIELD_ID, featureText.feature_detail_feature_status_code());
    
    JepTextField featureNameTextField = new JepTextField(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID,
        featureText.feature_detail_feature_name());
    featureNameTextField.setMaxLength(255);
    
    JepTextField featureNameEnTextField = new JepTextField(FEATURE_FEATURENAMEEN_DETAILFORM_FIELD_ID,
        featureText.feature_detail_feature_name_en());
    featureNameEnTextField.setMaxLength(255);
    
    JepIntegerField workSequenceFromField = new JepIntegerField(FEATURE_WORKSEQUENCEFROM_DETAILFORM_FIELD_ID,
        featureText.feature_detail_work_sequence_from());
    
    JepIntegerField workSequenceToField = new JepIntegerField(FEATURE_WORKSEQUENCETO_DETAILFORM_FIELD_ID,
        featureText.feature_detail_work_sequence_to());
    
    JepIntegerField workSequenceField = new JepIntegerField(FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID,
        featureText.feature_detail_work_sequence());
    
    JepDateField dateInsFromTextField = new JepDateField(FEATURE_FROMDATEINS_DETAILFORM_FIELD_ID,
        featureText.feature_detail_date_ins_from());
    
    JepDateField dateInsToTextField = new JepDateField(FEATURE_TODATEINS_DETAILFORM_FIELD_ID,
        featureText.feature_detail_date_ins_to());
    
    JepDateField dateInsTextField = new JepDateField(featureText.feature_detail_date_ins());
    
    JepTextAreaField descriptionTextAreaField = new JepTextAreaField(FEATURE_DESCRIPTION_DETAILFORM_FIELD_ID,
        featureText.feature_detail_description());
    descriptionTextAreaField.setFieldHeight(120);
    descriptionTextAreaField.setFieldWidth(400);
    
    JepComboBoxField authorComboBoxField = new JepComboBoxField(FEATURE_AUTHOR_ID_DETAILFORM_FIELD_ID,
        featureText.feature_detail_author_id());
    authorComboBoxField.setEmptyText(featureText.feature_detail_enter_first_symbols_responsible());
    authorComboBoxField.setTypingTimeoutMinTextSize(FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR);
    authorComboBoxField.setTypingTimeout(1000);
    authorComboBoxField.setFieldWidth(400);
    
    JepIntegerField maxRowCountIntegerField = new JepIntegerField(FEATURE_MAXROWCOUNT_DETAILFORM_FIELD_ID,
        featureText.feature_detail_max_row_count());
    
    JepListField featureStatusCodeListField = new JepListField(
        FEATURE_FEATURE_STATUS_CODE_LIST_DETAILFORM_FIELD_ID, featureText.feature_detail_feature_status_code());

    JepComboBoxField responsibleIdComboBoxField = new JepComboBoxField(
        FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID, featureText.feature_detail_responsible_id());
    responsibleIdComboBoxField.setEmptyText(featureText.feature_detail_enter_first_symbols_responsible());
    responsibleIdComboBoxField.setTypingTimeoutMinTextSize(FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR);
    responsibleIdComboBoxField.setTypingTimeout(1000);
    responsibleIdComboBoxField.setFieldWidth(400);
    
    panel.add(featureIdIntegerField);
    panel.add(featureStatusName);
    panel.add(featureStatusCodeListField);
    panel.add(featureNameTextField);
    panel.add(featureNameEnTextField);
    panel.add(workSequenceFromField);
    panel.add(workSequenceToField);
    panel.add(dateInsFromTextField);
    panel.add(dateInsToTextField);
    panel.add(dateInsTextField);
    panel.add(descriptionTextAreaField);
    panel.add(authorComboBoxField);
    panel.add(workSequenceField);
    panel.add(responsibleIdComboBoxField);
    panel.add(maxRowCountIntegerField);
 
    fields.put(FEATURE_ID, featureIdIntegerField);
    fields.put(FEATURE_STATUS_NAME, featureStatusName);
    fields.put(FEATURE_STATUS_CODE_LIST, featureStatusCodeListField);
    fields.put(FEATURE_NAME, featureNameTextField);
    fields.put(FEATURE_NAME_EN, featureNameEnTextField);
    fields.put(WORK_SEQUENCE_FROM, workSequenceFromField);
    fields.put(WORK_SEQUENCE_TO, workSequenceToField);
    fields.put(DATE_INS_FROM, dateInsFromTextField);
    fields.put(DATE_INS_TO, dateInsToTextField);
    fields.put(DATE_INS, dateInsTextField);
    fields.put(DESCRIPTION, descriptionTextAreaField);
    fields.put(AUTHOR_ID, authorComboBoxField);
    fields.put(WORK_SEQUENCE, workSequenceField);
    fields.put(RESPONSIBLE_ID, responsibleIdComboBoxField);
    fields.put(MAX_ROW_COUNT, maxRowCountIntegerField);
  }
}
