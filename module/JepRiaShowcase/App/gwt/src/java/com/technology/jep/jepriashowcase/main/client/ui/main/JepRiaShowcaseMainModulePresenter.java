package com.technology.jep.jepriashowcase.main.client.ui.main;

import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.ALLSHOPGOODS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREOPERATOR_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREPROCESS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATURE_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.GOODS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.REQUESTPROCESS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.REQUEST_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SHOPGOODS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SUPPLIER_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_ALL_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_OPERATOR_FEATURE;

import com.technology.jep.jepria.client.ui.eventbus.main.MainEventBus;
import com.technology.jep.jepria.client.ui.main.MainModulePresenter;
import com.technology.jep.jepria.client.ui.main.MainView;
import com.technology.jep.jepria.shared.service.JepMainServiceAsync;
import com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientFactoryImpl;

public class JepRiaShowcaseMainModulePresenter 
  extends MainModulePresenter<MainView, MainEventBus, JepMainServiceAsync, JepRiaShowcaseClientFactoryImpl>  {

  public JepRiaShowcaseMainModulePresenter(JepRiaShowcaseClientFactoryImpl clientFactory) {
    super(clientFactory);
    
    addModuleProtection(SUPPLIER_MODULE_ID, "JrsEditSupplier");
    addModuleProtection(GOODS_MODULE_ID, "JrsEditGoods");
    addModuleProtection(SHOPGOODS_MODULE_ID, "JrsEditShopGoods");
    addModuleProtection(ALLSHOPGOODS_MODULE_ID, "JrsEditShopGoods");
    addModuleProtection(REQUEST_MODULE_ID, "JrsEditRequest");
    addModuleProtection(REQUESTPROCESS_MODULE_ID, "JrsEditRequestProcess");
    addModuleProtection(FEATUREOPERATOR_MODULE_ID, ROLE_JRS_OPERATOR_FEATURE);
    addModuleProtection(FEATURE_MODULE_ID, ROLE_JRS_EDIT_FEATURE, ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE, ROLE_JRS_EDIT_ALL_FEATURE);
    addModuleProtection(FEATUREPROCESS_MODULE_ID, ROLE_JRS_EDIT_FEATURE, ROLE_JRS_EDIT_ALL_FEATURE);
    
    setProtectedModuleItemsVisibility(false);
  }
  
}


