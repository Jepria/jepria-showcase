package com.technology.jep.jepriashowcase.featureprocess.server.dao;
 
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_PROCESS_ID;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_STATUS_CODE;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_STATUS_NAME;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.OPERATOR_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.technology.jep.jepria.server.dao.JepDaoStandard;
import com.technology.jep.jepria.server.dao.ResultSetMapper;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepria.shared.util.Mutable;
import com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureStatusOptions;

public class FeatureProcessDao extends JepDaoStandard implements FeatureProcess {

  @Override
  public List<JepRecord> find(JepRecord templateRecord, Mutable<Boolean> autoRefreshFlag, Integer maxRowCount, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin  " 
        +  "? := pkg_jepriashowcase.findFeatureProcess("
          + "featureId => ?"
          + ", featureProcessId => ?"
          + ", featureStatusCode => ?"
          + ", maxRowCount => ? " 
          + ", operatorId => ? " 
        + ");"
     + " end;";
    return super.find(sqlQuery,
        new ResultSetMapper<JepRecord>() {
          public void map(ResultSet rs, JepRecord record) throws SQLException {
            
            JepOption jepOption = getOption(rs, FEATURE_STATUS_CODE, FEATURE_STATUS_NAME);
            record.set(FEATURE_PROCESS_ID, getInteger(rs, FEATURE_PROCESS_ID));
            record.set(FEATURE_STATUS_CODE, jepOption);
            record.set(FEATURE_STATUS_NAME, jepOption.getName());
            record.set(DATE_INS, getTimestamp(rs, DATE_INS));
            record.set(OPERATOR_NAME, rs.getString(OPERATOR_NAME));
          }
        }
        , templateRecord.get(FEATURE_ID)
        , templateRecord.get(FEATURE_PROCESS_ID)
        , JepOption.<String>getValue(templateRecord.get(FEATURE_STATUS_CODE))
        , maxRowCount 
        , operatorId);
  }

  @Override
  public void delete(JepRecord record, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin " 
        + "pkg_jepriashowcase.deleteFeatureProcess(" 
          + "featureProcessId => ?"
          + ", operatorId => ? " 
        + ");"
      + "end;";
    super.delete(sqlQuery 
        , record.get(FEATURE_PROCESS_ID)
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
        + "? := pkg_jepriashowcase.createFeatureProcess(" 
          + "featureId => ?"
          + ", featureStatusCode => ?"
          + ", operatorId => ? " 
        + ");"
      + "end;";
    return super.create(sqlQuery,
        Integer.class
        , record.get(FEATURE_ID)
        , JepOption.<String>getValue(record.get(FEATURE_STATUS_CODE))
        , operatorId);
  }
  
  @Override
  public List<JepOption> getFeatureStatus() throws ApplicationException {
    
    String sqlQuery = 
      " begin " 
      + " ? := pkg_jepriashowcase.getFeatureStatus;" 
      + " end;";
 
    return super.getOptions(
        sqlQuery,
        new ResultSetMapper<JepOption>() {
          public void map(ResultSet rs, JepOption dto) throws SQLException {
            dto.setValue(rs.getString(FeatureStatusOptions.FEATURE_STATUS_CODE));
            dto.setName(rs.getString(FeatureStatusOptions.FEATURE_STATUS_NAME));
          }
        }
    );
  }
  
}
