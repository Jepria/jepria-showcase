package com.technology.jep.jepriashowcase.feature.server.dao;
 
import java.util.List;

import com.technology.jep.jepria.server.dao.JepDataStandard;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.record.JepRecord;
 
public interface Feature extends JepDataStandard {
  
  /**
   * Получает список статусов фичи.
   * 
   * @return Список статусов.
   * @throws ApplicationException
   */
  public List<JepOption> getFeatureStatus() throws ApplicationException;
  
  /**
   * Получает список операторов фичи.
   * 
   * @param featureOperatorName - Имя оператора.
   * @param maxRowCount - Количество записей.
   * @param operatorId идентификатор пользователя
   * @return Список операторов.
   * @throws ApplicationException
   */
  public List<JepOption> getFeatureOperator(String featureOperatorName, Integer maxRowCount, Integer operatorId) throws ApplicationException;

  public void setFeatureWorkSequence(Integer featureId, Integer workSequence,
      Integer operatorId) throws ApplicationException;
  /**
   * 
   * @param record
   * @param operatorId
   * @param isUpdateResponsible обновление ответсвенного.
   * @param updateWorkSequence обновление порядка выполнения.
   * @throws ApplicationException
   */
  void update(JepRecord record, Integer operatorId, boolean isUpdateResponsible,
      boolean updateWorkSequence) throws ApplicationException;
}
