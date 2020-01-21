package com.technology.jep.jepriashowcase.shopgoods.client.ui.form.detail;
 
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.EDIT;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepriashowcase.shopgoods.client.ShopGoodsClientConstant.scopeModuleIds;
import static com.technology.jep.jepriashowcase.shopgoods.client.ShopGoodsClientConstant.shopGoodsText;
import static com.technology.jep.jepriashowcase.shopgoods.shared.ShopGoodsConstant.OPTION_COUNT;
import static com.technology.jep.jepriashowcase.shopgoods.shared.field.ShopGoodsFieldNames.GOODS_ID;
import static com.technology.jep.jepriashowcase.shopgoods.shared.field.ShopGoodsFieldNames.GOODS_QUANTITY;
import static com.technology.jep.jepriashowcase.shopgoods.shared.field.ShopGoodsFieldNames.SELL_PRICE;
import static com.technology.jep.jepriashowcase.shopgoods.shared.field.ShopGoodsFieldNames.SHOP_ID;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.technology.jep.jepria.client.async.TypingTimeoutAsyncCallback;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.form.detail.DetailFormPresenter;
import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.client.widget.event.JepEventType;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.client.widget.field.multistate.JepComboBoxField;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepriashowcase.shopgoods.client.ShopGoodsClientFactoryImpl;
import com.technology.jep.jepriashowcase.shopgoods.shared.service.ShopGoodsServiceAsync;
 
public class ShopGoodsDetailFormPresenter
    extends DetailFormPresenter<ShopGoodsDetailFormView, PlainEventBus, ShopGoodsServiceAsync, ShopGoodsClientFactoryImpl> { 
 
  public ShopGoodsDetailFormPresenter(Place place, ShopGoodsClientFactoryImpl clientFactory) {
    super(scopeModuleIds, place, clientFactory);
  }
 
  @Override
  public void bind() {
    super.bind();
    // Здесь размещается код связывания presenter-а и view
    
    fields.addFieldListener(SHOP_ID, JepEventType.TYPING_TIMEOUT_EVENT, new JepListener() {
      @Override
      public void handleEvent(JepEvent event) {
        String shopName = fields.get(SHOP_ID).getRawValue();
        service.getShop("%" + shopName + "%", new TypingTimeoutAsyncCallback<List<JepOption>>(event) {
          @Override
          public void onSuccessLoad(List<JepOption> result) {
            boolean isMoreThanTen = result.size() > OPTION_COUNT;
            ((JepComboBoxField) fields.get(SHOP_ID)).setLastOptionText(isMoreThanTen ? shopGoodsText.shopGoods_detail_shop_id_lastOptionText() : null);
            fields.setFieldOptions(SHOP_ID, isMoreThanTen ? result.subList(0, OPTION_COUNT) : result);
          }
        });
      }
    });
  }
 
  @Override
  protected void adjustToWorkstate(WorkstateEnum workstate) {
    fields.setFieldVisible(SHOP_ID, !EDIT.equals(workstate));
    fields.setFieldVisible(GOODS_ID, SEARCH.equals(workstate));
    fields.setFieldVisible(GOODS_QUANTITY, !SEARCH.equals(workstate));
    fields.setFieldVisible(SELL_PRICE, !SEARCH.equals(workstate));
    
    fields.setFieldAllowBlank(SHOP_ID, !CREATE.equals(workstate));
    fields.setFieldAllowBlank(GOODS_QUANTITY, !CREATE.equals(workstate) && !EDIT.equals(workstate));
    fields.setFieldAllowBlank(SELL_PRICE, !CREATE.equals(workstate) && !EDIT.equals(workstate));
  }
 
}
