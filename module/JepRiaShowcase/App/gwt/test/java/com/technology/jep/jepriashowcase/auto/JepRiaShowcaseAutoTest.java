package com.technology.jep.jepriashowcase.auto;

import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTestConstant.ENTRANCE_URL_ARSENIC_MODULE;
import static com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTestConstant.ENTRANCE_URL_FEATUREOPERATOR_MODULE;
import static com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTestConstant.ENTRANCE_URL_FEATURE_MODULE;
import static com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTestConstant.ENTRANCE_URL_GOODS_MODULE;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.ARSENIC_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREOPERATOR_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATUREPROCESS_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.FEATURE_MODULE_ID;
import static com.technology.jep.jepriashowcase.main.client.JepRiaShowcaseClientConstant.GOODS_MODULE_ID;

import org.apache.log4j.Logger;

import com.technology.jep.jepria.auto.model.module.ModuleDescription;
import com.technology.jep.jepria.auto.test.JepRiaApplicationAutoTest;
import com.technology.jep.jepriashowcase.arsenic.auto.ArsenicAuto;
import com.technology.jep.jepriashowcase.feature.auto.FeatureAuto;
import com.technology.jep.jepriashowcase.featureoperator.auto.FeatureOperatorAuto;
import com.technology.jep.jepriashowcase.featureprocess.auto.FeatureProcessAuto;
import com.technology.jep.jepriashowcase.goods.auto.GoodsAuto;

/**
 * Класс для написания тестов на уровне приложения. <br />
 * Примеры-образцы различных тестов: <br/>
 * <ul>
 *  <li>Стандартные (add/edit/delete/find) тесты: /JepRiaShowcase/Trunk/App/test/java/com/technology/jep/jepriashowcase/feature/auto/FeatureAutoTestStandard.java</li>
 *  <li>Демострация использования API тестирования JepRia-виджетов: /JepRiaShowcase/Trunk/App/test/java/com/technology/jep/jepriashowcase/arsenic/auto/ArsenicAutoTest.java</li>
 *  <li>Тест кастомной авторизации: /Navigation/Trunk/App/test/java/com/technology/jep/navigation/navigation/auto/NavigationAutoTest.java</li>
 *  <li>Тест вложенных модулей: /Cms/Trunk/App/test/java/com/technology/communicationsite/cms/auto/site/PagesAutoTest.java</li>
 * </ul>
 */
public class JepRiaShowcaseAutoTest extends JepRiaApplicationAutoTest<JepRiaShowcaseAutoImpl> {

  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(JepRiaShowcaseAutoTest.class.getName());
  
  /**
   * Описание модуля Feature.
   */
  protected ModuleDescription<FeatureAuto> feature = null;
  
  /**
   * Описание модуля Goods.
   */
  protected ModuleDescription<GoodsAuto> goods = null;
  
  /**
   * Описание модуля Arsenic.
   */
  protected ModuleDescription<ArsenicAuto> arsenic = null;
  
  /**
   * Описание модуля FeatureProcess.
   */
  protected ModuleDescription<FeatureProcessAuto> featureProcess = null;
  
  /**
   * Описание модуля FeatureOperator.
   */
  protected ModuleDescription<FeatureOperatorAuto> featureOperator = null;
  
  
  @Override
  protected JepRiaShowcaseAutoImpl provideAutomationManager(String baseUrl, String browserName, String browserVersion,
      String browserPlatform, String browserPath, String driverPath, String jepriaVersion, String username,
      String password, String dbUrl, String dbUser, String dbPassword) {

    return new JepRiaShowcaseAutoImpl(baseUrl, browserName, browserVersion,
        browserPlatform, browserPath, driverPath, jepriaVersion, username, password, dbUrl, dbUser, dbPassword);
  }
  
  @Override
  protected void beforeTestLaunch() {
    if(feature == null) {
      feature = new ModuleDescription<FeatureAuto>(FEATURE_MODULE_ID, 
          ENTRANCE_URL_FEATURE_MODULE,
          SEARCH,
          applicationAuto.getFeatureAuto(true));
    }

    
    if(goods == null) {
      goods = new ModuleDescription<GoodsAuto>(GOODS_MODULE_ID, 
          ENTRANCE_URL_GOODS_MODULE,
          SEARCH,
          applicationAuto.getGoodsAuto(true));
    }
    
    if(arsenic == null) {
      arsenic = new ModuleDescription<ArsenicAuto>(ARSENIC_MODULE_ID,
          ENTRANCE_URL_ARSENIC_MODULE,
          SEARCH,
          applicationAuto.getArsenicAuto(true));
    }
    
    if(featureProcess == null) {
      featureProcess = new ModuleDescription<FeatureProcessAuto>(FEATUREPROCESS_MODULE_ID,
          null,
          SEARCH,
          applicationAuto.getFeatureProcessAuto(true));
    }

    if(featureOperator == null) {
      featureOperator = new ModuleDescription<FeatureOperatorAuto>(FEATUREOPERATOR_MODULE_ID,
          ENTRANCE_URL_FEATUREOPERATOR_MODULE,
          SEARCH,
          applicationAuto.getFeatureOperatorAuto(true));
    }
  }
}
