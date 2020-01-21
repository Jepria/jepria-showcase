package com.technology.jep.jepriashowcase.feature.client.ui.form.list;

import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE;

import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.async.JepAsyncCallback;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.list.ListFormPresenter;
import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.shared.load.PagingConfig;
import com.technology.jep.jepria.shared.load.PagingResult;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepriashowcase.feature.client.FeatureClientFactoryImpl;
import com.technology.jep.jepriashowcase.feature.client.ui.form.list.event.DropEvent;
import com.technology.jep.jepriashowcase.feature.shared.service.FeatureServiceAsync;

public class FeatureListFormPresenter
  extends ListFormPresenter<FeatureListFormViewImpl, PlainEventBus, FeatureServiceAsync, FeatureClientFactoryImpl> {

  public FeatureListFormPresenter(Place place, FeatureClientFactoryImpl clientFactory) {
    super(place, clientFactory);
  }

  @Override
  public void bind(){
    super.bind();
    ((FeatureGridManager) list).addDropListener(new JepListener(){
      @Override
      public void handleEvent(JepEvent event) {
        DropEvent e = (DropEvent)event;
        final JepRecord record = e.getRecord();
        final JepRecord newPositionRecord = e.getNewPositionRecord();
        final boolean insertAfter = e.isInsertAfter();
        final Integer workSequence = insertAfter ? newPositionRecord.<Integer>get(WORK_SEQUENCE) + 1 : newPositionRecord.<Integer>get(WORK_SEQUENCE);
        service.setFeatureWorkSequence(record.<Integer>get(FEATURE_ID), workSequence, 
            new JepAsyncCallback<Void>() {
          @Override
          public void onSuccess(Void result) {
            JepRecord findTemplate = new JepRecord();
            findTemplate.set(FEATURE_ID, record.get(FEATURE_ID));
            service.find(new PagingConfig(findTemplate), new JepAsyncCallback<PagingResult<JepRecord>>() {
              @Override
              public void onSuccess(PagingResult<JepRecord> result) {
                record.set(WORK_SEQUENCE, result.getData().get(0).get(WORK_SEQUENCE));
                list.update(record);
              }
            });
          }
          
          @Override
          public void onFailure(Throwable caught) {
            super.onFailure(caught);
          }
        });
      }
    });
  }
  
}
