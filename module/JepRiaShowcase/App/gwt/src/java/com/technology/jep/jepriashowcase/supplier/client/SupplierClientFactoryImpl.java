package com.technology.jep.jepriashowcase.supplier.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SUPPLIER_MODULE_ID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.ui.JepPresenter;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.plain.StandardClientFactoryImpl;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.supplier.client.ui.form.detail.SupplierDetailFormPresenter;
import com.technology.jep.jepriashowcase.supplier.client.ui.form.detail.SupplierDetailFormViewImpl;
import com.technology.jep.jepriashowcase.supplier.client.ui.form.list.SupplierListFormPresenter;
import com.technology.jep.jepriashowcase.supplier.client.ui.form.list.SupplierListFormViewImpl;
import com.technology.jep.jepriashowcase.supplier.shared.record.SupplierRecordDefinition;
import com.technology.jep.jepriashowcase.supplier.shared.service.SupplierService;
import com.technology.jep.jepriashowcase.supplier.shared.service.SupplierServiceAsync;
 
public class SupplierClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, SupplierServiceAsync> {
 
  private static final IsWidget supplierDetailFormView = new SupplierDetailFormViewImpl();
  private static final IsWidget supplierListFormView = new SupplierListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
  
  public SupplierClientFactoryImpl() {
    super(SUPPLIER_MODULE_ID, SupplierRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(SupplierClientFactoryImpl.class);
    }
    return instance;
  }
 
 
  @Override
  public JepPresenter<PlainEventBus, SupplierClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new SupplierDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, SupplierClientFactoryImpl> createListFormPresenter(Place place) {
    return new SupplierListFormPresenter(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return supplierDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return supplierListFormView;
  }
 
  @Override
  protected SupplierServiceAsync createService() {
    return GWT.create(SupplierService.class);
  }
}
