package com.technology.jep.jepriashowcase.allshopgoods.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.ALLSHOPGOODS_MODULE_ID;

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
import com.technology.jep.jepriashowcase.allshopgoods.client.ui.form.detail.AllShopGoodsDetailFormPresenter;
import com.technology.jep.jepriashowcase.allshopgoods.client.ui.form.detail.AllShopGoodsDetailFormViewImpl;
import com.technology.jep.jepriashowcase.allshopgoods.client.ui.form.list.AllShopGoodsListFormPresenter;
import com.technology.jep.jepriashowcase.allshopgoods.client.ui.form.list.AllShopGoodsListFormViewImpl;
import com.technology.jep.jepriashowcase.allshopgoods.shared.record.AllShopGoodsRecordDefinition;
import com.technology.jep.jepriashowcase.allshopgoods.shared.service.AllShopGoodsService;
import com.technology.jep.jepriashowcase.allshopgoods.shared.service.AllShopGoodsServiceAsync;
 
public class AllShopGoodsClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, AllShopGoodsServiceAsync> {
 
  private static final IsWidget allShopGoodsDetailFormView = new AllShopGoodsDetailFormViewImpl();
  private static final IsWidget allShopGoodsListFormView = new AllShopGoodsListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public AllShopGoodsClientFactoryImpl() {
    super(ALLSHOPGOODS_MODULE_ID, AllShopGoodsRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(AllShopGoodsClientFactoryImpl.class);
    }
    return instance;
  }
 
  @Override
  public JepPresenter<PlainEventBus, AllShopGoodsClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new AllShopGoodsDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, AllShopGoodsClientFactoryImpl> createListFormPresenter(Place place) {
    return new AllShopGoodsListFormPresenter(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return allShopGoodsDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return allShopGoodsListFormView;
  }
 
  @Override
  protected AllShopGoodsServiceAsync createService() {
    return GWT.create(AllShopGoodsService.class);
  }
}
