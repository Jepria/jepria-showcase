package com.technology.jep.jepriashowcase.featureoperator.client.ui.form.detail;
 
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_DETAILS;
import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.FEATURE_OPERATOR_ID;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.FEATURE_OPERATOR_NAME;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.OPERATOR_NAME;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.async.TypingTimeoutAsyncCallback;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormPresenter;
import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.client.widget.event.JepEventType;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorClientFactoryImpl;
import com.technology.jep.jepriashowcase.featureoperator.shared.service.FeatureOperatorServiceAsync;

public class FeatureOperatorDetailFormPresenter
  extends DetailFormPresenter<FeatureOperatorDetailFormView, PlainEventBus, FeatureOperatorServiceAsync, FeatureOperatorClientFactoryImpl> {
    
  
  public FeatureOperatorDetailFormPresenter(Place place, FeatureOperatorClientFactoryImpl clientFactory) {
    super(place, clientFactory);
  }

  @Override
  public void bind() {
    super.bind();

    fields.get(FEATURE_OPERATOR_ID).addListener(JepEventType.TYPING_TIMEOUT_EVENT, new JepListener() {
      @Override
      public void handleEvent(final JepEvent event) {
        final String loginSearchPatern = ((JepComboBoxField) event.getSource()).getRawValue();
        service.getFeatureOperator(loginSearchPatern, new TypingTimeoutAsyncCallback<List<JepOption>>(event) {
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
    fields.setFieldVisible(FEATURE_OPERATOR_ID, CREATE.equals(workstate));
    fields.setFieldVisible(FEATURE_OPERATOR_NAME, SEARCH.equals(workstate) || VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(DATE_INS, VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(OPERATOR_NAME, VIEW_DETAILS.equals(workstate));
    fields.setFieldVisible(MAX_ROW_COUNT, SEARCH.equals(workstate));
    fields.setFieldAllowBlank(MAX_ROW_COUNT, !SEARCH.equals(workstate));
    fields.setFieldValue(MAX_ROW_COUNT, 25);
  }
}
