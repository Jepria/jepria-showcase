package com.technology.jep.jepriashowcase.featureoperator.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREOPERATOR_MODULE_ID;

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
import com.technology.jep.jepriashowcase.featureoperator.client.ui.form.detail.FeatureOperatorDetailFormPresenter;
import com.technology.jep.jepriashowcase.featureoperator.client.ui.form.detail.FeatureOperatorDetailFormViewImpl;
import com.technology.jep.jepriashowcase.featureoperator.client.ui.form.list.FeatureOperatorListFormViewImpl;
import com.technology.jep.jepriashowcase.featureoperator.client.ui.toolbar.FeatureOperatorToolBarPresenter;
import com.technology.jep.jepriashowcase.featureoperator.client.ui.toolbar.FeatureOperatorToolBarViewImpl;
import com.technology.jep.jepriashowcase.featureoperator.shared.record.FeatureOperatorRecordDefinition;
import com.technology.jep.jepriashowcase.featureoperator.shared.service.FeatureOperatorService;
import com.technology.jep.jepriashowcase.featureoperator.shared.service.FeatureOperatorServiceAsync;
 
public class FeatureOperatorClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, FeatureOperatorServiceAsync> {
 
  private static final IsWidget featureOperatorDetailFormView = new FeatureOperatorDetailFormViewImpl();
  private static final IsWidget featureOperatorToolBarView = new FeatureOperatorToolBarViewImpl();
  private static final IsWidget featureOperatorListFormView = new FeatureOperatorListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public FeatureOperatorClientFactoryImpl() {
    super(FEATUREOPERATOR_MODULE_ID, FeatureOperatorRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(FeatureOperatorClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, FeatureOperatorClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new FeatureOperatorDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, FeatureOperatorClientFactoryImpl> createListFormPresenter(Place place) {
    return new ListFormPresenter<FeatureOperatorListFormViewImpl, PlainEventBus, FeatureOperatorServiceAsync, FeatureOperatorClientFactoryImpl>(place, this);
  }
  
  @Override
  public JepPresenter<PlainEventBus, FeatureOperatorClientFactoryImpl> createToolBarPresenter(Place place) {
    return new FeatureOperatorToolBarPresenter<FeatureOperatorToolBarViewImpl, PlainEventBus, FeatureOperatorServiceAsync, FeatureOperatorClientFactoryImpl>(place, this);
  }
   
  @Override
  public IsWidget getToolBarView() {
    return featureOperatorToolBarView;
  }
   
  @Override
  public IsWidget getDetailFormView() {
    return featureOperatorDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return featureOperatorListFormView;
  }
 
  @Override
  protected FeatureOperatorServiceAsync createService() {
    return GWT.create(FeatureOperatorService.class);
  }
}