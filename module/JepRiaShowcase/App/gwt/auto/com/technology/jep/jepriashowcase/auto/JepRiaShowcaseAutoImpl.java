package com.technology.jep.jepriashowcase.auto;

import com.technology.jep.jepria.auto.application.JepRiaApplicationAuto;
import com.technology.jep.jepriashowcase.arsenic.auto.ArsenicAuto;
import com.technology.jep.jepriashowcase.arsenic.auto.ArsenicAutoImpl;
import com.technology.jep.jepriashowcase.custom.auto.CustomAuto;
import com.technology.jep.jepriashowcase.feature.auto.FeatureAuto;
import com.technology.jep.jepriashowcase.feature.auto.FeatureAutoImpl;
import com.technology.jep.jepriashowcase.featureoperator.auto.FeatureOperatorAuto;
import com.technology.jep.jepriashowcase.featureoperator.auto.FeatureOperatorAutoImpl;
import com.technology.jep.jepriashowcase.featureprocess.auto.FeatureProcessAuto;
import com.technology.jep.jepriashowcase.featureprocess.auto.FeatureProcessAutoImpl;
import com.technology.jep.jepriashowcase.goods.auto.GoodsAuto;
import com.technology.jep.jepriashowcase.goods.auto.GoodsAutoImpl;

public class JepRiaShowcaseAutoImpl extends JepRiaApplicationAuto implements JepRiaShowcaseAuto {
  private CustomAuto customAuto;
  private GoodsAuto goodsAuto;
  private ArsenicAuto arsenicAuto;
  private FeatureAuto featureAuto;
  private FeatureOperatorAuto featureOperatorAuto;
  private FeatureProcessAuto featureProcessAuto;

  public JepRiaShowcaseAutoImpl(String baseUrl,
      String browserName,
      String browserVersion,
      String browserPlatform,
      String browserPath,
      String driverPath,
      String jepriaVersion,
      String username,
      String password, 
      String dbURL,
      String dbUser,
      String dbPassword) {
    
    super(baseUrl, browserName, browserVersion, browserPlatform, browserPath, driverPath, jepriaVersion, username,
        password, dbURL, dbUser, dbPassword);
  }
  

  @Override
  public CustomAuto getCustomAuto() {
    return customAuto;
  }

  @Override
  public GoodsAuto getGoodsAuto(boolean newInstance) {
    if(goodsAuto == null || newInstance) {
      goodsAuto = new GoodsAutoImpl();
    }
    return goodsAuto;
  }
  
  @Override
  public ArsenicAuto getArsenicAuto(boolean newInstance) {
    if(arsenicAuto == null || newInstance) {
      arsenicAuto = new ArsenicAutoImpl();
    }
    return arsenicAuto;
  }

  @Override
  public FeatureAuto getFeatureAuto(boolean newInstance) {
    if(featureAuto == null || newInstance) {
      featureAuto = new FeatureAutoImpl();
    }
    return featureAuto;
  }

  @Override
  public FeatureProcessAuto getFeatureProcessAuto(boolean newInstance) {
    if(featureProcessAuto == null || newInstance) {
      featureProcessAuto = new FeatureProcessAutoImpl();
    }
    return featureProcessAuto;
  }

  @Override
  public FeatureOperatorAuto getFeatureOperatorAuto(boolean newInstance) {
    if(featureOperatorAuto == null || newInstance) {
      featureOperatorAuto = new FeatureOperatorAutoImpl();
    }
    return featureOperatorAuto;
  }

}
