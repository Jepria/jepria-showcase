package com.technology.jep.jepriashowcase.goods.client;
 
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.GOODS_MODULE_ID;

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
import com.technology.jep.jepriashowcase.goods.client.ui.form.detail.GoodsDetailFormPresenter;
import com.technology.jep.jepriashowcase.goods.client.ui.form.detail.GoodsDetailFormViewImpl;
import com.technology.jep.jepriashowcase.goods.client.ui.form.list.GoodsListFormViewImpl;
import com.technology.jep.jepriashowcase.goods.shared.record.GoodsRecordDefinition;
import com.technology.jep.jepriashowcase.goods.shared.service.GoodsService;
import com.technology.jep.jepriashowcase.goods.shared.service.GoodsServiceAsync;
 
public class GoodsClientFactoryImpl
  extends StandardClientFactoryImpl<PlainEventBus, GoodsServiceAsync> {
 
  private static final IsWidget goodsDetailFormView = new GoodsDetailFormViewImpl();
  private static final IsWidget goodsListFormView = new GoodsListFormViewImpl();
 
  public static PlainClientFactoryImpl<PlainEventBus, JepDataServiceAsync> instance = null;
 
  public GoodsClientFactoryImpl() {
    super(GOODS_MODULE_ID, GoodsRecordDefinition.instance);
  }
 
  static public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(GoodsClientFactoryImpl.class);
    }
    return instance;
  }
 
 
  @Override
  public JepPresenter<PlainEventBus, GoodsClientFactoryImpl> createDetailFormPresenter(Place place) {
    return new GoodsDetailFormPresenter(place, this);
  }
 
  @Override
  public JepPresenter<PlainEventBus, GoodsClientFactoryImpl> createListFormPresenter(Place place) {
    return new ListFormPresenter<GoodsListFormViewImpl, PlainEventBus, GoodsServiceAsync, GoodsClientFactoryImpl>(place, this);
  }
 
  @Override
  public IsWidget getDetailFormView() {
    return goodsDetailFormView;
  }
 
  @Override
  public IsWidget getListFormView() {
    return goodsListFormView;
  }
  
  @Override
  protected GoodsServiceAsync createService() {
    return GWT.create(GoodsService.class);
  }
}
