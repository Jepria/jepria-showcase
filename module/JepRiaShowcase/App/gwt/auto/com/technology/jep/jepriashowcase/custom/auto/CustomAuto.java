package com.technology.jep.jepriashowcase.custom.auto;

import com.technology.jep.jepria.auto.application.entrance.EntranceAuto;
import com.technology.jep.jepriashowcase.goods.auto.GoodsAuto;

public interface CustomAuto extends EntranceAuto {

  /**
   * Открытие и получение примера с полноэкранными модулями
   */
  GoodsAuto openFullScreenModules();
  
  /**
   * Признак безуспесшности авторизации
   * 
   * @return флаг безуспешности
   */
  boolean isAuthorizedFailed();
}
