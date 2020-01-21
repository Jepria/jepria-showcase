package com.technology.jep.jepriashowcase.main.client.ui.main;

import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.ALLSHOPGOODS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.ARSENIC_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.CUSTOM_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREOPERATOR_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREPROCESS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATURE_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.GOODS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.REQUESTPROCESS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.REQUEST_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SEARCH_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SHOPGOODS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SIMPLE_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.SUPPLIER_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.jepRiaShowcaseText;

import java.util.ArrayList;
import java.util.List;

import com.technology.jep.jepria.client.ui.main.MainViewImpl;
import com.technology.jep.jepria.client.ui.main.ModuleConfiguration;

public class JepRiaShowcaseMainViewImpl extends MainViewImpl {

  @Override
  protected List<ModuleConfiguration> getModuleConfigurations() {
    List<ModuleConfiguration> ret = new ArrayList<>();
    ret.add(new ModuleConfiguration(CUSTOM_MODULE_ID, jepRiaShowcaseText.submodule_custom_title()));
    ret.add(new ModuleConfiguration(SEARCH_MODULE_ID, jepRiaShowcaseText.submodule_simple_title()));
    ret.add(new ModuleConfiguration(SIMPLE_MODULE_ID, jepRiaShowcaseText.submodule_search_title()));
    ret.add(new ModuleConfiguration(SUPPLIER_MODULE_ID, jepRiaShowcaseText.submodule_supplier_title()));
    ret.add(new ModuleConfiguration(GOODS_MODULE_ID, jepRiaShowcaseText.submodule_goods_title()));
    ret.add(new ModuleConfiguration(ARSENIC_MODULE_ID, jepRiaShowcaseText.submodule_arsenic_title()));
    ret.add(new ModuleConfiguration(SHOPGOODS_MODULE_ID, jepRiaShowcaseText.submodule_shopgoods_title()));
    ret.add(new ModuleConfiguration(ALLSHOPGOODS_MODULE_ID, jepRiaShowcaseText.submodule_allshopgoods_title()));
    ret.add(new ModuleConfiguration(REQUEST_MODULE_ID, jepRiaShowcaseText.submodule_request_title()));
    ret.add(new ModuleConfiguration(REQUESTPROCESS_MODULE_ID, jepRiaShowcaseText.submodule_requestprocess_title()));
    ret.add(new ModuleConfiguration(FEATURE_MODULE_ID, jepRiaShowcaseText.submodule_feature_title()));
    ret.add(new ModuleConfiguration(FEATUREOPERATOR_MODULE_ID, jepRiaShowcaseText.submodule_featureoperator_title()));
    ret.add(new ModuleConfiguration(FEATUREPROCESS_MODULE_ID, jepRiaShowcaseText.submodule_featureprocess_title()));
    return ret;
  }
}