package com.technology.jep.jepriashowcase.feature.server.dao;
 
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.AUTHOR_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.AUTHOR_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS_FROM;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS_TO;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DESCRIPTION;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME_EN;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_STATUS_CODE;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_STATUS_CODE_LIST;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_STATUS_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.RESPONSIBLE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.RESPONSIBLE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE_FROM;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE_TO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.technology.jep.jepria.server.dao.DaoSupport;
import com.technology.jep.jepria.server.dao.JepDaoStandard;
import com.technology.jep.jepria.server.dao.ResultSetMapper;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepria.shared.util.Mutable;
import com.technology.jep.jepriashowcase.main.shared.field.FeatureOperatorOptions;
import com.technology.jep.jepriashowcase.main.shared.field.FeatureStatusOptions;

public class FeatureDao extends JepDaoStandard implements Feature {
 
  @Override
  public List<JepRecord> find( JepRecord templateRecord, Mutable<Boolean> autoRefreshFlag, Integer maxRowCount, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin  " 
        +  "? := pkg_jepriashowcase.findFeature(" 
            + "featureid => ? " 
            + ", featureName => ? " 
            + ", featureNameEn => ? " 
            + ", workSequenceFrom => ? "
            + ", workSequenceTo => ? "
            + ", dateInsFrom => ? " 
            + ", dateInsTo => ? " 
            + ", authorid => ? "
            + ", responsibleId => ? " 
            + ", featureStatusCodeList => ? " 
            + ", maxRowCount => ? " 
            + ", operatorId => ? " 
        + ");"
     + " end;";
    
    return super.find( sqlQuery,
        new ResultSetMapper<JepRecord>() {
          public void map(ResultSet rs, JepRecord record) throws SQLException {
            record.set(FEATURE_ID, getInteger(rs, FEATURE_ID));
            record.set(FEATURE_NAME, rs.getString(FEATURE_NAME));
            record.set(FEATURE_NAME_EN, rs.getString(FEATURE_NAME_EN));
            record.set(WORK_SEQUENCE, getInteger(rs, WORK_SEQUENCE));
            record.set(DESCRIPTION, rs.getString(DESCRIPTION));
            record.set(DATE_INS, getDate(rs, DATE_INS));
            record.set(AUTHOR_ID, getOption(rs, AUTHOR_ID, AUTHOR_NAME));
            record.set(AUTHOR_NAME, rs.getString(AUTHOR_NAME));
            record.set(RESPONSIBLE_ID, getOption(rs, RESPONSIBLE_ID, RESPONSIBLE_NAME));
            record.set(RESPONSIBLE_NAME, rs.getString(RESPONSIBLE_NAME));
            record.set(FEATURE_STATUS_NAME,  rs.getString(FEATURE_STATUS_NAME));
            record.set(FEATURE_STATUS_CODE,  getOption(rs, FEATURE_STATUS_CODE, FEATURE_STATUS_NAME));
          }
        }
        , templateRecord.get(FEATURE_ID)
        , templateRecord.get(FEATURE_NAME)
        , templateRecord.get(FEATURE_NAME_EN)
        , templateRecord.get(WORK_SEQUENCE_FROM)
        , templateRecord.get(WORK_SEQUENCE_TO)
        , templateRecord.get(DATE_INS_FROM)
        , templateRecord.get(DATE_INS_TO)
        , JepOption.getValue(templateRecord.get(AUTHOR_ID))
        , JepOption.getValue(templateRecord.get(RESPONSIBLE_ID))
        , JepOption.getOptionValuesAsString((List<JepOption>)templateRecord.get(FEATURE_STATUS_CODE_LIST)).replaceAll(", ", ";")
        , maxRowCount 
        , operatorId);
  }
  
  public void setFeatureResponsible(JepRecord record, Integer operatorId) throws ApplicationException {
    
    String sqlQuery = 
        "begin " 
            + "pkg_jepriashowcase.setFeatureResponsible(" 
              + "featureId => ? " 
              + ", responsibleId => ? " 
              + ", operatorId => ? " 
            + ");"
            + "end;";
    
    DaoSupport.execute(sqlQuery 
        , record.get(FEATURE_ID)
        , JepOption.<Integer>getValue(record.get(RESPONSIBLE_ID))
        , operatorId);
  }
  
  public void delete(JepRecord record, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin " 
        + "pkg_jepriashowcase.deleteFeature(" 
            + "featureId => ? " 
          + ", operatorId => ? " 
        + ");"
      + "end;";
    super.delete(sqlQuery 
        , record.get(FEATURE_ID) 
        , operatorId);
  }
 
  @Override
  public void update(JepRecord record, Integer operatorId) throws ApplicationException {
    this.update(record, operatorId, false, false);
  }
  
  @Override
  public void update(JepRecord record, Integer operatorId, boolean isUpdateResponsible, boolean updateWorkSequence) throws ApplicationException {
    String sqlQuery = 
      "begin " 
      +  "pkg_jepriashowcase.updateFeature(" 
            + "featureId => ? " 
            + ", featureName => ? "
            + ", featureNameEn => ? "
            + ", operatorId => ? " 
      + ");"
     + "end;";
    
    super.update(sqlQuery 
        , record.get(FEATURE_ID)
        , record.get(FEATURE_NAME)
        , record.get(FEATURE_NAME_EN)
        , operatorId);
    if (updateWorkSequence) {
      setFeatureWorkSequence(record.<Integer>get(FEATURE_ID), record.<Integer>get(WORK_SEQUENCE), operatorId);
    }
    if(isUpdateResponsible){
      setFeatureResponsible(record, operatorId);
    }
  }
 
  public Integer create(JepRecord record, Integer operatorId) throws ApplicationException {
    String sqlQuery = 
      "begin  " 
        +  "? := pkg_jepriashowcase.createFeature(" 
            + "featureName => ? " 
            + ", featureNameEn => ? " 
            + ", operatorId => ? " 
        + ");"
     + " end;";
    return super.create(sqlQuery, 
        Integer.class 
        , record.get(FEATURE_NAME)
        , record.get(FEATURE_NAME_EN)
        , operatorId);
  }
  
  @Override
  public void setFeatureWorkSequence(Integer featureId, Integer workSequence, Integer operatorId) throws ApplicationException{
    String sqlQuery = 
        "begin  " 
        +  "pkg_jepriashowcase.setFeatureWorkSequence("
            + "featureId => ? "
            + ", workSequence => ? "
            + ", operatorId => ? "
          + ");"
      + " end;";
    DaoSupport.execute(
        sqlQuery,
        featureId,
        workSequence,
        operatorId);
  }

  @Override
  public List<JepOption> getFeatureOperator(String featureOperatorName, Integer maxRowCount, Integer operatorId)
      throws ApplicationException {
    
    String sqlQuery = 
        " begin " 
        + " ? := pkg_jepriashowcase.findFeatureOperator(" 
            + "featureOperatorId => ? " 
            + ", featureOperatorName => ? " 
            + ", maxRowCount => ? " 
            + ", operatorId => ? " 
        + ");"
     + " end;";
  
    return super.getOptions(
        sqlQuery,
        new ResultSetMapper<JepOption>() {
          public void map(ResultSet rs, JepOption dto) throws SQLException {
            dto.setValue(rs.getString(FeatureOperatorOptions.FEATURE_OPERATOR_CODE));
            dto.setName(rs.getString(FeatureOperatorOptions.FEATURE_OPERATOR_NAME));
          }
        },
        null,
        featureOperatorName + "%",
        maxRowCount,
        operatorId
    );
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
