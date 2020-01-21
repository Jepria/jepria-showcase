package com.technology.jep.jepriashowcase.auto;

import com.technology.jep.jepriashowcase.arsenic.auto.ArsenicAuto;
import com.technology.jep.jepriashowcase.custom.auto.CustomAuto;
import com.technology.jep.jepriashowcase.feature.auto.FeatureAuto;
import com.technology.jep.jepriashowcase.featureoperator.auto.FeatureOperatorAuto;
import com.technology.jep.jepriashowcase.featureprocess.auto.FeatureProcessAuto;
import com.technology.jep.jepriashowcase.goods.auto.GoodsAuto;

public interface JepRiaShowcaseAuto {
  CustomAuto getCustomAuto();
  
  GoodsAuto getGoodsAuto(boolean newInstance);
  ArsenicAuto getArsenicAuto(boolean newInstance);
  FeatureAuto getFeatureAuto(boolean newInstance);
  FeatureProcessAuto getFeatureProcessAuto(boolean newInstance);
  FeatureOperatorAuto getFeatureOperatorAuto(boolean newInstance);
}
