package com.technology.jep.jepriashowcase.feature.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATURE_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.plain.StandardClientFactoryImpl;
import com.technology.jep.jepria.client.ui.plain.StandardModulePresenter;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.feature.client.ui.form.detail.FeatureDetailFormPresenter;
import com.technology.jep.jepriashowcase.feature.client.ui.form.detail.FeatureDetailFormViewImpl;
import com.technology.jep.jepriashowcase.feature.client.ui.form.list.FeatureListFormPresenter;
import com.technology.jep.jepriashowcase.feature.client.ui.form.list.FeatureListFormViewImpl;
import com.technology.jep.jepriashowcase.feature.shared.record.FeatureRecordDefinition;
import com.technology.jep.jepriashowcase.feature.shared.service.FeatureService;
import com.technology.jep.jepriashowcase.feature.shared.service.FeatureServiceAsync;
 
public class FeatureClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, FeatureServiceAsync> {
 
  private static final IsWidget featureDetailFormView = new FeatureDetailFormViewImpl();
  private static final IsWidget featureListFormView = new FeatureListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public FeatureClientFactoryImpl() {
    super(FEATURE_MODULE_ID, FeatureRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(FeatureClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, FeatureClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new FeatureDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, FeatureClientFactoryImpl> createListFormPresenter(Place place) {
    return new FeatureListFormPresenter(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return featureDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return featureListFormView;
  }
 
  @Override
  protected FeatureServiceAsync createService() {
    return GWT.create(FeatureService.class);
  }
}
