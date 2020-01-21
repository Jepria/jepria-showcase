package com.technology.jep.jepriashowcase.featureoperator.server.dao;
 
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.RESPONSIBLE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.RESPONSIBLE_NAME;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.*;

import com.technology.jep.jepria.server.dao.JepDao;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepria.shared.util.Mutable;
import com.technology.jep.jepriashowcase.featureoperator.server.dao.FeatureOperator;
import com.technology.jep.jepriashowcase.featureoperator.shared.field.OperatorOptions;
import com.technology.jep.jepria.server.dao.ResultSetMapper;
import com.technology.jep.jepria.shared.field.option.JepOption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FeatureOperatorDao extends JepDao implements FeatureOperator {

  @Override
  public List<JepRecord> find(JepRecord templateRecord, Mutable<Boolean> autoRefreshFlag, Integer maxRowCount, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin  " 
        +  "? := pkg_jepriashowcase.findFeatureOperator(" 
          + "featureOperatorId => ?"
          + ", featureOperatorName => ?"
          + ", maxRowCount => ? " 
          + ", operatorId => ? " 
        + ");"
     + " end;";
    return super.find(sqlQuery,
        new ResultSetMapper<JepRecord>() {
          public void map(ResultSet rs, JepRecord record) throws SQLException {
            
            JepOption jepOption = getOption(rs, FEATURE_OPERATOR_ID, FEATURE_OPERATOR_NAME);
            record.set(FEATURE_OPERATOR_ID, jepOption);
            record.set(FEATURE_OPERATOR_NAME, jepOption.getName());
            record.set(OPERATOR_NAME, rs.getString(OPERATOR_NAME));
            record.set(DATE_INS, getTimestamp(rs, DATE_INS));
          }
        }
        , templateRecord.get(FEATURE_OPERATOR_ID)
        , templateRecord.get(FEATURE_OPERATOR_NAME)
        , maxRowCount 
        , operatorId);
  }

  @Override
  public void delete(JepRecord record, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin " 
        + "pkg_jepriashowcase.deleteFeatureOperator(" 
          + "featureOperatorId => ?"
          + ", operatorId => ? " 
        + ");"
      + "end;";
    super.delete(sqlQuery 
        , JepOption.<Integer>getValue(record.get(FEATURE_OPERATOR_ID))
        , operatorId);
  }

  @Override
  public void update(JepRecord record, Integer operatorId) throws ApplicationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Integer create(JepRecord record, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin " 
        + "? := pkg_jepriashowcase.createFeatureOperator(" 
          + "featureOperatorId => ?"
          + ", operatorId => ? " 
        + ");"
      + "end;";
    return super.create(sqlQuery,
        Integer.class 
        , JepOption.<String>getValue(record.get(FEATURE_OPERATOR_ID))
        , operatorId);
  }
  
  public List<JepOption> getFeatureOperator(String operatorName) throws ApplicationException {
    String sqlQuery = 
      " begin " 
      + " ? := pkg_Operator.getOperator(" 
          + "operatorName => ?"
        + ");"
      + "end;";
 
    return super.getOptions(
        sqlQuery,
        new ResultSetMapper<JepOption>() {
          public void map(ResultSet rs, JepOption dto) throws SQLException {
            String operatorName = rs.getString(OperatorOptions.OPERATOR_NAME);
            Integer operatorID  = getInteger(rs, OperatorOptions.OPERATOR_ID);
            dto.setValue(operatorID);
            dto.setName(operatorName + " " + operatorID);
          }
        },
        operatorName + "%"
    );
  }
}
