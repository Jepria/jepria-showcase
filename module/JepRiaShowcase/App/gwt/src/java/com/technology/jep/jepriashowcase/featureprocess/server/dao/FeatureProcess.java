package com.technology.jep.jepriashowcase.featureprocess.server.dao;
 
import java.util.List;

import com.technology.jep.jepria.server.dao.JepDataStandard;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
 
public interface FeatureProcess extends JepDataStandard {
  /**
   * Получает список статусов фичи.
   * 
   * @return Список статусов.
   * @throws ApplicationException
   */
  public List<JepOption> getFeatureStatus() throws ApplicationException;
}
