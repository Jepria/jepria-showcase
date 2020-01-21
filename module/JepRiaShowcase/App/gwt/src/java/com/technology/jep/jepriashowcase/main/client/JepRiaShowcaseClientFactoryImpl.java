package com.technology.jep.jepriashowcase.main.client;

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

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.technology.jep.jepria.client.async.LoadAsyncCallback;
import com.technology.jep.jepria.client.async.LoadPlainClientFactory;
import com.technology.jep.jepria.client.ui.eventbus.main.MainEventBus;
import com.technology.jep.jepria.client.ui.eventbus.plain.PlainEventBus;
import com.technology.jep.jepria.client.ui.main.MainClientFactory;
import com.technology.jep.jepria.client.ui.main.MainClientFactoryImpl;
import com.technology.jep.jepria.client.ui.main.MainModulePresenter;
import com.technology.jep.jepria.client.ui.main.MainView;
import com.technology.jep.jepria.client.ui.plain.PlainClientFactory;
import com.technology.jep.jepria.shared.service.JepMainServiceAsync;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
import com.technology.jep.jepriashowcase.allshopgoods.client.AllShopGoodsClientFactoryImpl;
import com.technology.jep.jepriashowcase.arsenic.client.ArsenicClientFactoryImpl;
import com.technology.jep.jepriashowcase.custom.client.CustomClientFactoryImpl;
import com.technology.jep.jepriashowcase.feature.client.FeatureClientFactoryImpl;
import com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorClientFactoryImpl;
import com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessClientFactoryImpl;
import com.technology.jep.jepriashowcase.goods.client.GoodsClientFactoryImpl;
import com.technology.jep.jepriashowcase.main.client.ui.main.JepRiaShowcaseMainModulePresenter;
import com.technology.jep.jepriashowcase.main.client.ui.main.JepRiaShowcaseMainViewImpl;
import com.technology.jep.jepriashowcase.request.client.RequestClientFactoryImpl;
import com.technology.jep.jepriashowcase.requestprocess.client.RequestProcessClientFactoryImpl;
import com.technology.jep.jepriashowcase.search.client.SearchClientFactoryImpl;
import com.technology.jep.jepriashowcase.shopgoods.client.ShopGoodsClientFactoryImpl;
import com.technology.jep.jepriashowcase.simple.client.SimpleClientFactoryImpl;
import com.technology.jep.jepriashowcase.supplier.client.SupplierClientFactoryImpl;

public class JepRiaShowcaseClientFactoryImpl
  extends MainClientFactoryImpl<MainEventBus, JepMainServiceAsync> {
  
  private static final IsWidget mainView = new JepRiaShowcaseMainViewImpl();
  
  public static MainClientFactory<MainEventBus, JepMainServiceAsync> getInstance() {
    if(instance == null) {
      instance = GWT.create(JepRiaShowcaseClientFactoryImpl.class);
    }
    return instance;
  }

  private JepRiaShowcaseClientFactoryImpl() {
    super(
        CUSTOM_MODULE_ID,
        SEARCH_MODULE_ID,
        SIMPLE_MODULE_ID,
        SUPPLIER_MODULE_ID,
        GOODS_MODULE_ID,
        ARSENIC_MODULE_ID,
        SHOPGOODS_MODULE_ID,
        ALLSHOPGOODS_MODULE_ID,
        REQUEST_MODULE_ID,
        REQUESTPROCESS_MODULE_ID,
        FEATURE_MODULE_ID,
        FEATUREOPERATOR_MODULE_ID,
        FEATUREPROCESS_MODULE_ID
    );
  }
  
  
  @Override
  public MainModulePresenter<? extends MainView, MainEventBus, JepMainServiceAsync, JepRiaShowcaseClientFactoryImpl> createMainModulePresenter() {
    return new JepRiaShowcaseMainModulePresenter(this);
  }

  @Override
  public void getPlainClientFactory(String moduleId, final LoadAsyncCallback<PlainClientFactory<PlainEventBus, JepDataServiceAsync>> callback) {
    // Для эффективного кодоразделения при GWT-компиляции (см. http://www.gwtproject.org/doc/latest/DevGuideCodeSplitting.html)
    // необходимо получать инстанс каждой plain-фабрики модуля с помощью GWT.runAsync, в отдельной ветке if-else, зависящей от ID модуля.
    
    if(CUSTOM_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + CUSTOM_MODULE_ID);
          return CustomClientFactoryImpl.getInstance();
        }
      });
    }
    else if(SEARCH_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + SEARCH_MODULE_ID);
          return SearchClientFactoryImpl.getInstance();
        }
      });
    } 
    else if(SIMPLE_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + SIMPLE_MODULE_ID);
          return SimpleClientFactoryImpl.getInstance();
        }
      });
    } 
    else if(SUPPLIER_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + SUPPLIER_MODULE_ID);
          return SupplierClientFactoryImpl.getInstance();
        }
      });
    }
    else if(GOODS_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + GOODS_MODULE_ID);
          return GoodsClientFactoryImpl.getInstance();
        }
      });
    }
    else if(ARSENIC_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + ARSENIC_MODULE_ID);
          return ArsenicClientFactoryImpl.getInstance();
        }
      });
    }
    else if(SHOPGOODS_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + SHOPGOODS_MODULE_ID);
          return ShopGoodsClientFactoryImpl.getInstance();
        }
      });
    }
    else if(ALLSHOPGOODS_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + ALLSHOPGOODS_MODULE_ID);
          return AllShopGoodsClientFactoryImpl.getInstance();
        }
      });
    }
    else if(REQUEST_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + REQUEST_MODULE_ID);
          return RequestClientFactoryImpl.getInstance();
        }
      });
    }
    else if(REQUESTPROCESS_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + REQUESTPROCESS_MODULE_ID);
          return RequestProcessClientFactoryImpl.getInstance();
        }
      });
    }
    else if(FEATURE_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + FEATURE_MODULE_ID);
          return FeatureClientFactoryImpl.getInstance();
        }
      });
    }
    else if(FEATUREOPERATOR_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + FEATUREOPERATOR_MODULE_ID);
          return FeatureOperatorClientFactoryImpl.getInstance();
        }
      });
    }
    else if(FEATUREPROCESS_MODULE_ID.equals(moduleId)) {
      GWT.runAsync(new LoadPlainClientFactory(callback) {
        public PlainClientFactory<PlainEventBus, JepDataServiceAsync> getPlainClientFactory() {
          Log.trace(JepRiaShowcaseClientFactoryImpl.this.getClass() + ".getPlainClientFactory: moduleId = " + FEATUREPROCESS_MODULE_ID);
          return FeatureProcessClientFactoryImpl.getInstance();
        }
      });
    }
  }

  @Override
  public IsWidget getMainView() {
    return mainView;
  }
  
}
