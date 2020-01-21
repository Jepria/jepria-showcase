package com.technology.jep.jepriashowcase.featureoperator.server.dao;
 
import com.technology.jep.jepria.server.dao.JepDataStandard;
import java.util.List;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
 
public interface FeatureOperator extends JepDataStandard {
  List<JepOption> getFeatureOperator(String operatorName) throws ApplicationException;
}
