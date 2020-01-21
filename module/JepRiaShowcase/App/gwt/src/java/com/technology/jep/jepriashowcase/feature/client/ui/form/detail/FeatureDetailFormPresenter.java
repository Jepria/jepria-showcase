package com.technology.jep.jepriashowcase.feature.client.ui.form.detail;

import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.EDIT;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_DETAILS;
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_MAX_ROW_COUNT;
import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.feature.client.FeatureClientConstant.scopeModuleIds;
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
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE;

import java.util.Date;
import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.technology.jep.jepria.client.async.JepAsyncCallback;
import com.technology.jep.jepria.client.async.TypingTimeoutAsyncCallback;
import com.technology.jep.jepria.client.security.ClientSecurity;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormPresenter;
import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.client.widget.event.JepEventType;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepriashowcase.feature.client.FeatureClientFactoryImpl;
import com.technology.jep.jepriashowcase.feature.shared.service.FeatureServiceAsync;

public class FeatureDetailFormPresenter extends
    DetailFormPresenter<FeatureDetailFormViewImpl, PlainEventBus, FeatureServiceAsync, FeatureClientFactoryImpl> {

  public FeatureDetailFormPresenter(Place place, FeatureClientFactoryImpl clientFactory) {
    super(scopeModuleIds, place, clientFactory);
  }
  
  @Override
  protected void bind() {
    super.bind();
    
    service.getFeatureStatus(new JepAsyncCallback<List<JepOption>>() {
      public void onSuccess(List<JepOption> result){
        fields.setFieldOptions(FEATURE_STATUS_CODE_LIST, result);
      }
    });
    
    fields.get(RESPONSIBLE_ID).addListener(JepEventType.TYPING_TIMEOUT_EVENT, new JepListener() {
      @Override
      public void handleEvent(final JepEvent event) {
        final String loginSearchPatern = ((JepComboBoxField) event.getSource()).getRawValue();
        service.getFeatureOperator(loginSearchPatern, DEFAULT_MAX_ROW_COUNT, new TypingTimeoutAsyncCallback<List<JepOption>>(event) {
          @Override
          public void onSuccessLoad(List<JepOption> result) {
            ((JepComboBoxField) event.getSource()).setOptions(result);
          }
        });
      }
    });
    
    fields.get(AUTHOR_ID).addListener(JepEventType.TYPING_TIMEOUT_EVENT, new JepListener() {
      @Override
      public void handleEvent(final JepEvent event) {
        final String authorName = ((JepComboBoxField) event.getSource()).getRawValue();
        service.getFeatureOperator(authorName, DEFAULT_MAX_ROW_COUNT, new TypingTimeoutAsyncCallback<List<JepOption>>(event) {
          @Override
          public void onSuccessLoad(List<JepOption> result) {
            ((JepComboBoxField) event.getSource()).setOptions(result);
          }
        });
      }
    });
  }

  @Override
  protected void adjustToWorkstate(WorkstateEnum workstate) {
    super.adjustToWorkstate(workstate);
    
    fields.setFieldVisible(FEATURE_ID, SEARCH.equals(workstate) || EDIT.equals(workstate) || VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(DATE_INS_FROM, SEARCH.equals(workstate));
    fields.setFieldVisible(DATE_INS_TO, SEARCH.equals(workstate));
    fields.setFieldVisible(DATE_INS, VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(WORK_SEQUENCE_FROM, SEARCH.equals(workstate));
    fields.setFieldVisible(WORK_SEQUENCE_TO, SEARCH.equals(workstate));
    fields.setFieldVisible(WORK_SEQUENCE, EDIT.equals(workstate) || VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(DESCRIPTION, !SEARCH.equals(workstate));
    fields.setFieldVisible(AUTHOR_ID, SEARCH.equals(workstate) || VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(MAX_ROW_COUNT, SEARCH.equals(workstate));
    fields.setFieldVisible(FEATURE_STATUS_CODE_LIST, SEARCH.equals(workstate));
    fields.setFieldVisible(FEATURE_STATUS_NAME, VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(RESPONSIBLE_ID, SEARCH.equals(workstate) || EDIT.equals(workstate) || VIEW_DETAILS.equals(workstate));
    
    fields.setFieldAllowBlank(FEATURE_NAME, !(EDIT.equals(workstate) || CREATE.equals(workstate)));
    fields.setFieldAllowBlank(FEATURE_NAME_EN, !(EDIT.equals(workstate) || CREATE.equals(workstate)));
    fields.setFieldAllowBlank(MAX_ROW_COUNT, !SEARCH.equals(workstate));
    fields.setFieldValue(MAX_ROW_COUNT, 25);
    
    fields.setFieldEditable(FEATURE_ID, SEARCH.equals(workstate));
    fields.setFieldEditable(FEATURE_STATUS_NAME, false);
    fields.setFieldEditable(RESPONSIBLE_ID, SEARCH.equals(workstate) || 
        ClientSecurity.instance.isUserHaveRoles(ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE));
    fields.setFieldEditable(WORK_SEQUENCE, EDIT.equals(workstate) &&
        ClientSecurity.instance.isUserHaveRoles(ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE));
    if(this.searchTemplate == null) {
      Date theeMonthBack = new Date();
      CalendarUtil.addMonthsToDate(theeMonthBack, -6);
      fields.setFieldValue(DATE_INS_FROM, theeMonthBack);
    }
  }
}
