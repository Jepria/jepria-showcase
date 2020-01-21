package com.technology.jep.jepriashowcase.arsenic.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.ARSENIC_MODULE_ID;

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
import com.technology.jep.jepriashowcase.arsenic.client.ui.form.detail.ArsenicDetailFormPresenter;
import com.technology.jep.jepriashowcase.arsenic.client.ui.form.detail.ArsenicDetailFormViewImpl;
import com.technology.jep.jepriashowcase.arsenic.client.ui.form.list.ArsenicListFormViewImpl;
import com.technology.jep.jepriashowcase.arsenic.shared.record.ArsenicRecordDefinition;
import com.technology.jep.jepriashowcase.arsenic.shared.service.ArsenicService;
import com.technology.jep.jepriashowcase.arsenic.shared.service.ArsenicServiceAsync;
 
public class ArsenicClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, ArsenicServiceAsync> {
 
  private static final IsWidget arsenicDetailFormView = new ArsenicDetailFormViewImpl();
  private static final IsWidget arsenicListFormView = new ArsenicListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public ArsenicClientFactoryImpl() {
    super(ARSENIC_MODULE_ID, ArsenicRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(ArsenicClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, ArsenicClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new ArsenicDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, ArsenicClientFactoryImpl> createListFormPresenter(Place place) {
    return new ListFormPresenter<ArsenicListFormViewImpl, PlainEventBus, ArsenicServiceAsync, ArsenicClientFactoryImpl>(place, this)/*TODO change to local LFP*/;
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return arsenicDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return arsenicListFormView;
  }
 
  @Override
  protected ArsenicServiceAsync createService() {
    return GWT.create(ArsenicService.class);
  }
}
