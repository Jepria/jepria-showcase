package com.technology.jep.jepriashowcase.allshopgoods.client.ui.form.detail;
 
import static com.technology.jep.jepriashowcase.allshopgoods.client.AllShopGoodsClientConstant.scopeModuleIds;

import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormPresenter;
import com.technology.jep.jepriashowcase.allshopgoods.client.AllShopGoodsClientFactoryImpl;
import com.technology.jep.jepriashowcase.allshopgoods.shared.service.AllShopGoodsServiceAsync;
 
public class AllShopGoodsDetailFormPresenter 
    extends DetailFormPresenter<AllShopGoodsDetailFormViewImpl, PlainEventBus, AllShopGoodsServiceAsync, AllShopGoodsClientFactoryImpl> { 
 
  public AllShopGoodsDetailFormPresenter(Place place, AllShopGoodsClientFactoryImpl clientFactory) {
    super(scopeModuleIds, place, clientFactory);
  }
 
  /* public void bind() {
    super.bind();
    // Здесь размещается код связывания presenter-а и view 
  }
  */ 
 
  protected void adjustToWorkstate(WorkstateEnum workstate) {
  }
 
}
