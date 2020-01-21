package com.technology.jep.jepriashowcase.featureprocess.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREPROCESS_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.list.ListFormPresenter;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.plain.StandardClientFactoryImpl;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.featureprocess.client.ui.form.detail.FeatureProcessDetailFormPresenter;
import com.technology.jep.jepriashowcase.featureprocess.client.ui.form.detail.FeatureProcessDetailFormViewImpl;
import com.technology.jep.jepriashowcase.featureprocess.client.ui.form.list.FeatureProcessListFormViewImpl;
import com.technology.jep.jepriashowcase.featureprocess.client.ui.toolbar.FeatureProcessToolBarViewImpl;
import com.technology.jep.jepriashowcase.featureprocess.shared.record.FeatureProcessRecordDefinition;
import com.technology.jep.jepriashowcase.featureprocess.shared.service.FeatureProcessService;
import com.technology.jep.jepriashowcase.featureprocess.shared.service.FeatureProcessServiceAsync;
 
public class FeatureProcessClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, FeatureProcessServiceAsync> {
 
  private static final IsWidget featureProcessDetailFormView = new FeatureProcessDetailFormViewImpl();
  private static final IsWidget featureProcessToolBarView = new FeatureProcessToolBarViewImpl();
  private static final IsWidget featureProcessListFormView = new FeatureProcessListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public FeatureProcessClientFactoryImpl() {
    super(FEATUREPROCESS_MODULE_ID, FeatureProcessRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(FeatureProcessClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, FeatureProcessClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new FeatureProcessDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, FeatureProcessClientFactoryImpl> createListFormPresenter(Place place) {
    return new ListFormPresenter<FeatureProcessListFormViewImpl, PlainEventBus, FeatureProcessServiceAsync, FeatureProcessClientFactoryImpl>(place, this);
  }
  
  @Override
  public IsWidget getToolBarView() {
    return featureProcessToolBarView;
  }
   
  @Override
  public IsWidget getDetailFormView() {
    return featureProcessDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return featureProcessListFormView;
  }
 
  @Override
  protected FeatureProcessServiceAsync createService() {
    return GWT.create(FeatureProcessService.class);
  }
}