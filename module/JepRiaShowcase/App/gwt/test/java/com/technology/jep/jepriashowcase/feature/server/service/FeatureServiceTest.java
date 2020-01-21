package com.technology.jep.jepriashowcase.feature.server.service;

import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DESCRIPTION;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME_EN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.technology.jep.jepria.server.test.DataSourceDef;
import com.technology.jep.jepria.server.test.JepRiaServiceTestBase;
import com.technology.jep.jepria.server.test.ServerTestUtil;
import com.technology.jep.jepria.shared.exceptions.ApplicationException;
import com.technology.jep.jepria.shared.load.FindConfig;
import com.technology.jep.jepria.shared.load.PagingConfig;
import com.technology.jep.jepria.shared.load.PagingResult;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepriashowcase.feature.server.dao.Feature;

/**
 * Пример теста сервисного класса JepRia
 */
@GwtModule("com.technology.jep.jepriashowcase.feature.Feature")
public class FeatureServiceTest extends JepRiaServiceTestBase<Feature> {

  @BeforeClass
  public static void setUpClass() throws Exception {
    ServerTestUtil.prepareDataSources(new ArrayList<DataSourceDef>(Arrays.asList(new DataSourceDef(
        "java:/comp/env/jdbc/ITMDS",
        "jdbc:oracle:thin:@//db-server:1521/ORACLE",
        "itm_login",
        "password"
    ))));
  }  

  @Before
  public void before() {
    beforeServiceTest(new FeatureServiceImpl());
  }

  @After
  public void after() {
    afterServiceTest();
  }

  private static final String DESCRIPTION_VALUE = "DESCRIPTION_VALUE";
  private static final String FEATURE_NAME_VALUE = "FEATURE_NAME_VALUE";
  private static final String FEATURE_NAME_EN_VALUE = "FEATURE_NAME_EN_VALUE";

  @Test
  public void createShouldReturnResultRecord() throws ApplicationException {
    JepRecord testRecord = ServerTestUtil.createRecord(getDefaultFieldMap());
    // TODO FindConfig нужно переименовать
    FindConfig createConfig = new FindConfig(testRecord);
      
    JepRecord resultRecord = service.create(createConfig);

    clearAfterTest(createConfig);
    
    assertNotNull(resultRecord);
    assertTrue(ServerTestUtil.isFieldValueSubSet(testRecord, resultRecord));
    assertNotNull(resultRecord.get(FEATURE_ID));
    assertNotNull(resultRecord.get(DATE_INS));
  }

  @Test
  public void findByIdShouldReturnJustOneRecordWithSameId() throws ApplicationException {
    JepRecord featureRecord = createRecordInDb(true, getDefaultFieldMap());
    Object recordId = featureRecord.get(FEATURE_ID);
    JepRecord templateRecord = new JepRecord();
    templateRecord.set(FEATURE_ID, recordId);
    PagingConfig pagingConfig = new PagingConfig(templateRecord);
    
    PagingResult<JepRecord> pagingResult = service.find(pagingConfig);
    
    assertNotNull(pagingResult);
    // Почему в PagingResult Integer size, а не int size ? Этот же вопрос и по другим членам.
    assertEquals((Integer)1, pagingResult.getSize());
    List<JepRecord> records = pagingResult.getData();
    assertEquals(1, records.size());
    JepRecord resultRecord = records.get(0);
    
//    assertTrue(ServerTestUtil.isFieldValueSubSet(featureRecord, resultRecord));
    assertEquals(recordId, resultRecord.get(FEATURE_ID));
  }
  
  @Test
  public void findByTemplateRecordShouldReturnRecordsWithSuperSetFieldValues() throws ApplicationException {
    String currentTimeInMillis =  "_" + System.currentTimeMillis();
    
    Map<String, Object> uniqueFieldMap = new HashMap<String, Object>();
    uniqueFieldMap.put(DESCRIPTION, DESCRIPTION_VALUE + currentTimeInMillis);
    uniqueFieldMap.put(FEATURE_NAME, FEATURE_NAME_VALUE + currentTimeInMillis);
    uniqueFieldMap.put(FEATURE_NAME_EN, FEATURE_NAME_EN_VALUE + currentTimeInMillis);

    JepRecord featureRecord = createRecordInDb(uniqueFieldMap);

    JepRecord templateRecord = new JepRecord();
    templateRecord.set(DESCRIPTION, DESCRIPTION_VALUE + currentTimeInMillis);
    templateRecord.set(FEATURE_NAME, FEATURE_NAME_VALUE + currentTimeInMillis);
    templateRecord.set(FEATURE_NAME_EN, FEATURE_NAME_EN_VALUE + currentTimeInMillis);
    PagingConfig pagingConfig = new PagingConfig(templateRecord);
    
    PagingResult<JepRecord> pagingResult = service.find(pagingConfig);
    
    assertNotNull(pagingResult);
    // Почему в PagingResult Integer size, а не int size ? Этот же вопрос и по другим членам.
    assertEquals((Integer)1, pagingResult.getSize());
    List<JepRecord> records = pagingResult.getData();
    assertEquals(1, records.size());
    JepRecord resultRecord = records.get(0);
    
    assertTrue(ServerTestUtil.isFieldValueSubSet(featureRecord, resultRecord));
  }

  @Test
  public void deleteShouldRemoveRecordFromDb() throws ApplicationException {
    JepRecord featureRecord = createRecordInDb(false, getDefaultFieldMap());

    //assertEquals((Integer) 1, findById(featureRecord, FEATURE_ID).getSize());
    
    service.delete(new FindConfig(featureRecord));
    
    assertEquals((Integer) 0, findById(FEATURE_ID, featureRecord.get(FEATURE_ID)).getSize());
  }

  @Test
  public void updateShouldReturnResultRecordWithSameRecordIdAndProperlyChangedValues() throws ApplicationException {
    JepRecord testRecord = createRecordInDb(getDefaultFieldMap());
    
    Map<String, Object> newFieldMap = new HashMap<String, Object>();
    newFieldMap.put(DESCRIPTION, DESCRIPTION_VALUE + "_CHANGED");
    newFieldMap.put(FEATURE_NAME, FEATURE_NAME_VALUE + "_CHANGED");
    newFieldMap.put(FEATURE_NAME_EN, FEATURE_NAME_EN_VALUE + "_CHANGED");
    
    JepRecord resultRecord = service.update(new FindConfig(ServerTestUtil.updateRecord(testRecord, newFieldMap)));
    
    assertEquals(testRecord.get(FEATURE_ID), resultRecord.get(FEATURE_ID));
    assertNotNull(resultRecord.get(FEATURE_ID));
    assertNotNull(resultRecord.get(DATE_INS));
    
    PagingResult<JepRecord> pagingResult = findById(FEATURE_ID, testRecord.get(FEATURE_ID));
    assertEquals((Integer) 1, pagingResult.getSize());
    assertEquals(DESCRIPTION_VALUE + "_CHANGED", pagingResult.getData().get(0).get(DESCRIPTION));
    assertEquals(FEATURE_NAME_VALUE + "_CHANGED", pagingResult.getData().get(0).get(FEATURE_NAME));
    assertEquals(FEATURE_NAME_EN_VALUE + "_CHANGED", pagingResult.getData().get(0).get(FEATURE_NAME_EN));
  }

  private Map<String, Object> getDefaultFieldMap() {
    Map<String, Object> fieldMap = new HashMap<String, Object>();
    fieldMap.put(DESCRIPTION, DESCRIPTION_VALUE);
    fieldMap.put(FEATURE_NAME, FEATURE_NAME_VALUE);
    fieldMap.put(FEATURE_NAME_EN, FEATURE_NAME_EN_VALUE);
    
    return fieldMap;
  }
}
