package com.technology.jep.jepriashowcase.allshopgoods.client.ui.form.list;
 
import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.list.ListFormPresenter;
import com.technology.jep.jepriashowcase.allshopgoods.client.AllShopGoodsClientFactoryImpl;
import com.technology.jep.jepriashowcase.allshopgoods.shared.service.AllShopGoodsServiceAsync; 
public class AllShopGoodsListFormPresenter 
  extends ListFormPresenter<AllShopGoodsListFormViewImpl, PlainEventBus, AllShopGoodsServiceAsync, AllShopGoodsClientFactoryImpl> { 
 
  public AllShopGoodsListFormPresenter(Place place, AllShopGoodsClientFactoryImpl clientFactory) {
    super(place, clientFactory);
  }
 
}
