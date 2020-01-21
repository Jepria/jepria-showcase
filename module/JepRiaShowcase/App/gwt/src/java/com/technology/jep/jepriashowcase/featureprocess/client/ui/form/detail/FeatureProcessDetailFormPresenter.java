package com.technology.jep.jepriashowcase.featureprocess.client.ui.form.detail;
 
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_DETAILS;
import static com.technology.jep.jepria.shared.field.JepFieldNames.MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_STATUS_CODE;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.async.FirstTimeUseAsyncCallback;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormPresenter;
import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.client.widget.event.JepEventType;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessClientFactoryImpl;
import com.technology.jep.jepriashowcase.featureprocess.shared.service.FeatureProcessServiceAsync;

public class FeatureProcessDetailFormPresenter
  extends DetailFormPresenter<FeatureProcessDetailFormView, PlainEventBus, FeatureProcessServiceAsync, FeatureProcessClientFactoryImpl> {
    
  
  public FeatureProcessDetailFormPresenter(Place place, FeatureProcessClientFactoryImpl clientFactory) {
    super(place, clientFactory);
  }  
  
  @Override
  public void bind() {
    super.bind();
    
    fields.addFieldListener(FEATURE_STATUS_CODE, JepEventType.FIRST_TIME_USE_EVENT, new JepListener() {
      @Override
      public void handleEvent(final JepEvent event) {
        service.getFeatureStatus(new FirstTimeUseAsyncCallback<List<JepOption>>(event) {
          @Override
          public void onSuccessLoad(List<JepOption> result){
            fields.setFieldOptions(FEATURE_STATUS_CODE, result);
          }
        });
      }
    });
  }
 
  @Override
  protected void adjustToWorkstate(WorkstateEnum workstate) {
    fields.setFieldVisible(FEATURE_STATUS_CODE,
        SEARCH.equals(workstate) || CREATE.equals(workstate) || VIEW_DETAILS.equals(workstate));
    fields.setFieldAllowBlank(FEATURE_STATUS_CODE, !CREATE.equals(workstate));
    fields.setFieldVisible(MAX_ROW_COUNT, SEARCH.equals(workstate));
    fields.setFieldAllowBlank(MAX_ROW_COUNT, !SEARCH.equals(workstate));
    fields.setFieldValue(MAX_ROW_COUNT, 25);
  }
}
