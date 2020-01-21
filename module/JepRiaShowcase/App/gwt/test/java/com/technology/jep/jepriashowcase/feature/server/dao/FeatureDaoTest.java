package com.technology.jep.jepriashowcase.feature.server.dao;

import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DESCRIPTION;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME_EN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.technology.jep.jepria.server.dao.CallContext;
import com.technology.jep.jepria.server.test.DaoTestBase;
import com.technology.jep.jepria.server.test.DataSourceDef;
import com.technology.jep.jepria.server.test.ServerTestUtil;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.record.JepRecord;

/**
 * Тест DAO модуля Feature
 */
public class FeatureDaoTest extends DaoTestBase<Feature> {
  private static final String USERNAME = "USER_NAME";
  private static final String PASSWORD = "PASSWORD";
  
  private static final String MODULE_NAME = "FeatureTest";
  
  private static final String DESCRIPTION_VALUE = "DESCRIPTION_VALUE";
  private static final String FEATURE_NAME_VALUE = "FEATURE_NAME_VALUE";
  private static final String FEATURE_NAME_EN_VALUE = "FEATURE_NAME_EN_VALUE";
  
  private static final String DATA_SOURCE_NAME = "java:/comp/env/jdbc/ITMDS";

  @BeforeClass
  public static void setUpClass() throws Exception {
    ServerTestUtil.prepareDataSources(new ArrayList<DataSourceDef>(Arrays.asList(new DataSourceDef(
        DATA_SOURCE_NAME,
        "jdbc:oracle:thin:@//db-server:1521/Oracle",
        "itm_login",
        "password"
    ))));
    
    beforeClass(USERNAME, PASSWORD, FEATURE_ID);
  }
  
  @Before
  public void before() {
    dao = new FeatureDao();
  }

  @After
  public void after() {
    super.after();
    // TODO Нужен logoff
    // pkg_Operator.logoff(db);
  }
  
  @Test
  public void createShouldReturnRecordId() throws ApplicationException, SQLException {
    JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
    
    try {
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      Integer recordId = (Integer) dao.create(testRecord, operatorId);
      assertNotNull(recordId);
      List<JepRecord> records = findById(recordId);
      assertEquals(1, records.size());
      assertEquals(recordId, records.get(0).get(FEATURE_ID));
//    assertTrue(ServerTestUtil.isFieldValueSubSet(testRecord, records.get(0)));
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }
  
  @Test
  public void createShouldThrowApplicationExceptionWhenFieldsNotSet() throws ApplicationException, SQLException {
    Map<String, Object> fieldMap = new HashMap<String, Object>();
//    fieldMap.put(DESCRIPTION, DESCRIPTION_VALUE); // Clob
    JepRecord testRecord = ServerTestUtil.createRecord(fieldMap);
  
    try {
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      dao.create(testRecord, operatorId);
    } catch(ApplicationException ex) {
      // Ожидаемое исключение
    } catch(Throwable th) {
      fail("Unexpected fail: " + th.getMessage());
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }
  
  // Возможно, данный тест следует удалить за неактуальностью.
  @Ignore("Поле DESCRIPTION_VALUE не возвращается в курсоре функции findFeature()")
  @Test
  public void createShouldSupportSimpleClobFields() throws ApplicationException, SQLException {
    JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
    
    try {
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      Integer recordId = (Integer) dao.create(testRecord, operatorId);
      List<JepRecord> records = findById(recordId);
      assertEquals(DESCRIPTION_VALUE, records.get(0).get(DESCRIPTION)); // Простое поле Clob 
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }

  @Test
  public void findByIdShouldReturnJustOneRecordWithSameId() throws ApplicationException, SQLException {
    JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
    try {
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      Object recordId = dao.create(testRecord, operatorId);
      
      // Поиск по шаблону
      JepRecord templateRecord = new JepRecord();
      templateRecord.set(FEATURE_ID, recordId);
      List<JepRecord> resultRecords = dao.find(templateRecord, null, 100, operatorId);

      // Проверка
      assertEquals(1, resultRecords.size());
      assertEquals(recordId, resultRecords.get(0).get(FEATURE_ID));
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }
  
  @Test
  public void findByTemplateRecordShouldReturnRecordsWithSuperSetFieldValues() throws ApplicationException, SQLException {
    String currentTimeInMillis =  "_" + System.currentTimeMillis();
    
    // Создание записи с уникальными полями
    // Уникальные значения полей нужны, чтобы тестовую запись не перепутать с другими записями базы
    String uniqueDescriptionValue = DESCRIPTION_VALUE + currentTimeInMillis;
    String uniqueFeatureNameValue = FEATURE_NAME_VALUE + currentTimeInMillis;
    String uniqueFeatureNameEnValue = FEATURE_NAME_EN_VALUE + currentTimeInMillis;
    
    Map<String, Object> uniqueFieldMap = new HashMap<String, Object>();
    uniqueFieldMap.put(DESCRIPTION, uniqueDescriptionValue);
    uniqueFieldMap.put(FEATURE_NAME, uniqueFeatureNameValue);
    uniqueFieldMap.put(FEATURE_NAME_EN, uniqueFeatureNameEnValue);
    JepRecord uniqueRecord = ServerTestUtil.createRecord(uniqueFieldMap);
    
    try {
      CallContext.begin(DATA_SOURCE_NAME, "Feature");
      dao.create(uniqueRecord, operatorId);
      
      // Поиск по шаблону
      JepRecord templateRecord = new JepRecord();
//      templateRecord.set(DESCRIPTION, uniqueDescriptionValue); пока Clob не работает в DAO
      templateRecord.set(FEATURE_NAME, uniqueFeatureNameValue);
      templateRecord.set(FEATURE_NAME_EN, uniqueFeatureNameEnValue);
      List<JepRecord> resultRecords = dao.find(templateRecord, null, 100, operatorId);
      
      // Проверка
      assertEquals(1, resultRecords.size());
      assertTrue(ServerTestUtil.isFieldValueSubSet(templateRecord, resultRecords.get(0)));      
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }

  @Test
  public void deleteShouldRemoveRecordFromDb() throws ApplicationException, SQLException {
    JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
    try {
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      Object recordId = dao.create(testRecord, operatorId);      
      dao.delete(ServerTestUtil.createRecordWithRecordId(FEATURE_ID, recordId), operatorId);
      assertEquals(0, findById(recordId).size());
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }

  @Test
  public void createShouldSupportRollback() throws ApplicationException, SQLException {
    JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
    try{
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      (new FeatureDao()).create(testRecord, operatorId);
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }

  @Test
  public void updateShouldReturnResultRecordWithSameRecordIdAndProperlyChangedValues() throws ApplicationException, SQLException {
    try {
      CallContext.begin(DATA_SOURCE_NAME, MODULE_NAME);
      JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
      Object recordId = dao.create(testRecord, operatorId);
      Map<String, Object> fieldMapForUpdate = new HashMap<String, Object>();
      fieldMapForUpdate.put(FEATURE_ID, recordId);
//      fieldMapForUpdate.put(DESCRIPTION, DESCRIPTION_VALUE + "_CHANGED"); // Clob
      fieldMapForUpdate.put(FEATURE_NAME, FEATURE_NAME_VALUE + "_CHANGED");
      fieldMapForUpdate.put(FEATURE_NAME_EN, FEATURE_NAME_EN_VALUE + "_CHANGED");
      
      JepRecord recordForUpdate = ServerTestUtil.createRecord(fieldMapForUpdate);
      
      // Update
      dao.update(recordForUpdate, operatorId);

      // Проверка
      List<JepRecord> records = findById(recordId);
      assertEquals(1, records.size());
      JepRecord resultRecord = records.get(0);
      assertEquals(recordId, resultRecord.get(FEATURE_ID));
//      assertEquals(DESCRIPTION_VALUE + "_CHANGED", resultRecord.get(DESCRIPTION)); // Clob
      assertEquals(FEATURE_NAME_VALUE + "_CHANGED", resultRecord.get(FEATURE_NAME));
      assertEquals(FEATURE_NAME_EN_VALUE + "_CHANGED", resultRecord.get(FEATURE_NAME_EN));      
    } finally {
      CallContext.rollback();
      CallContext.end();
    }
  }

  
  private Map<String, Object> getDefaultFieldMap() {
    Map<String, Object> fieldMap = new HashMap<String, Object>();
    fieldMap.put(DESCRIPTION, DESCRIPTION_VALUE);
    fieldMap.put(FEATURE_NAME, FEATURE_NAME_VALUE);
    fieldMap.put(FEATURE_NAME_EN, FEATURE_NAME_EN_VALUE);
    
    return fieldMap;
  }
}
