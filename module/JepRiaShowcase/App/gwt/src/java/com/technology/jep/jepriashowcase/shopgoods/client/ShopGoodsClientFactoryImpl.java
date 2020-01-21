package com.technology.jep.jepriashowcase.shopgoods.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SHOPGOODS_MODULE_ID;

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
import com.technology.jep.jepriashowcase.shopgoods.client.ui.form.detail.ShopGoodsDetailFormPresenter;
import com.technology.jep.jepriashowcase.shopgoods.client.ui.form.detail.ShopGoodsDetailFormViewImpl;
import com.technology.jep.jepriashowcase.shopgoods.client.ui.form.list.ShopGoodsListFormViewImpl;
import com.technology.jep.jepriashowcase.shopgoods.shared.record.ShopGoodsRecordDefinition;
import com.technology.jep.jepriashowcase.shopgoods.shared.service.ShopGoodsService;
import com.technology.jep.jepriashowcase.shopgoods.shared.service.ShopGoodsServiceAsync;
 
public class ShopGoodsClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, ShopGoodsServiceAsync> {
 
  private static final IsWidget shopGoodsDetailFormView = new ShopGoodsDetailFormViewImpl();
  private static final IsWidget shopGoodsListFormView = new ShopGoodsListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public ShopGoodsClientFactoryImpl() {
    super(SHOPGOODS_MODULE_ID, ShopGoodsRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(ShopGoodsClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, ShopGoodsClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new ShopGoodsDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, ShopGoodsClientFactoryImpl> createListFormPresenter(Place place) {
    return new ListFormPresenter<ShopGoodsListFormViewImpl, PlainEventBus, ShopGoodsServiceAsync, ShopGoodsClientFactoryImpl>(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return shopGoodsDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return shopGoodsListFormView;
  }

  @Override
  protected ShopGoodsServiceAsync createService() {
    return GWT.create(ShopGoodsService.class);
  }
}
