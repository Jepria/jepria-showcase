package com.technology.jep.jepriashowcase.featureprocess.dao;

import org.jepria.compat.server.dao.ResultSetMapper;
import com.technology.jep.jepriashowcase.featureprocess.FeatureProcessFieldNames;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessCreateDtoInternal;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessDto;
import com.technology.jep.jepriashowcase.featureprocess.dto.FeatureProcessSearchDtoInternal;
import org.jepria.server.data.DaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FeatureProcessDaoImpl implements FeatureProcessDao {

  @Override
  public List<FeatureProcessDto> find(Object template, Integer operatorId) {

    FeatureProcessSearchDtoInternal searchTemplate = (FeatureProcessSearchDtoInternal) template;

    final List<FeatureProcessDto> records;

    records = DaoSupport.getInstance().find(findSqlQuery,
            new FeatureProcessResultSetMapper()
            , FeatureProcessDto.class
            , searchTemplate.getFeatureProcessId()
            , null
            , searchTemplate.getFeatureId()
            , null
            , operatorId);

    return records;

  }

  private static final String findSqlQuery =
          " begin "
                  + " ? := pkg_jepriashowcase.findfeatureprocess("
                  + "featureprocessid => ? "
                  + ", featurestatuscode => ? "
                  + ", featureid => ? "
                  + ", maxRowCount => ? "
                  + ", operatorId => ? "
                  + ");"
                  + " end;";

  protected static class FeatureProcessResultSetMapper extends ResultSetMapper<FeatureProcessDto> {
    @Override
    public void map(ResultSet rs, FeatureProcessDto dto) throws SQLException {
      dto.setFeatureId(getInteger(rs, FeatureProcessFieldNames.FEATURE_ID));
      dto.setFeatureProcessId(getInteger(rs, FeatureProcessFieldNames.FEATURE_PROCESS_ID));
      dto.setFeatureStatusCode(rs.getString(FeatureProcessFieldNames.FEATURE_STATUS_CODE));
      dto.setFeatureStatusName(rs.getString(FeatureProcessFieldNames.FEATURE_STATUS_NAME));
      dto.setDateIns(rs.getDate(FeatureProcessFieldNames.DATE_INS));
    }
  }

  @Override
  public List<FeatureProcessDto> findByPrimaryKey(Map<String, ?> primaryKeyMap, Integer operatorId) {

    final List<FeatureProcessDto> records;

    records = DaoSupport.getInstance().find(findSqlQuery,
            new FeatureProcessResultSetMapper()
            , FeatureProcessDto.class
            , primaryKeyMap.get(FeatureProcessFieldNames.FEATURE_PROCESS_ID)
            , null
            , primaryKeyMap.get(FeatureProcessFieldNames.FEATURE_ID)
            , null
            , operatorId);

    return records;
  }

  @Override
  public Object create(Object record, Integer operatorId) {

    FeatureProcessCreateDtoInternal dto = (FeatureProcessCreateDtoInternal)record;

    String sqlQuery =
            "begin  "
                    + "? := pkg_jepriashowcase.createfeatureprocess("
                    + "featureId => ? "
                    + ", featurestatuscode => ? "
                    + ", operatorId => ? "
                    + ");"
                    + " end;";

    final Integer featureProcessId;

    featureProcessId = DaoSupport.getInstance().create(
            sqlQuery
            , Integer.class
            , dto.getFeatureId()
            , dto.getFeatureStatusCode()
            , operatorId);

    return featureProcessId;
  }

  @Override
  public void update(Map<String, ?> primaryKey, Object record, Integer operatorId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(Map<String, ?> primaryKey, Integer operatorId) {
    String sqlQuery =
            "begin "
                    + "pkg_jepriashowcase.deletefeatureprocess("
                    + "featureprocessid => ? "
                    + ", operatorId => ? "
                    + ");"
                    + "end;";

    DaoSupport.getInstance().delete(sqlQuery
            , primaryKey.get(FeatureProcessFieldNames.FEATURE_PROCESS_ID)
            , operatorId);
  }
}
