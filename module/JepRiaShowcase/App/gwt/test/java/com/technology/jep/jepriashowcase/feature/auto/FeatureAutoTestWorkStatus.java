package com.technology.jep.jepriashowcase.feature.auto;

import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_DETAILS;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATUREID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_GRID_ID;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_ALL_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_FEATURE;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.technology.jep.jepria.auto.model.user.User;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;
import com.technology.jep.jepriashowcase.featureprocess.auto.FeatureProcessAuto;

public class FeatureAutoTestWorkStatus extends JepRiaShowcaseAutoTest {
  /**
   * Пользователи, под которыми запускаются тесты:
   */
  private final static String USER_A_LOGIN = "JrsFeatureUserA";
  @SuppressWarnings("serial")
  private final static List<String> userARoles = new ArrayList<String>() {{
    add(ROLE_JRS_EDIT_FEATURE);
    add(ROLE_JRS_EDIT_ALL_FEATURE);
  }};
  private final static String USER_B_LOGIN = "JrsFeatureUserB";
  @SuppressWarnings("serial")
  private final static List<String> userBRoles = new ArrayList<String>() {{
    add(ROLE_JRS_EDIT_FEATURE);
    add(ROLE_JRS_EDIT_ALL_FEATURE);
  }};
  
  private ArrayList<String>featureIds = new ArrayList<>();
  
  @Test(priority = 1,
      description = "Подготовка сценариев. Очистка 'мусорных' записей. Создание тестовых записей.")
  public void createTestData(){
    enterModule(feature);
    User userA = getUser(USER_A_LOGIN, userARoles);
    Reporter.log("Тест под пользователем A: " + userA.toString(), true);
    login(userA);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    final Date currentDate = new Date();
    featureCut.find();
    featureCut.setFromDateIns(DateFormat.getDateInstance().format(currentDate));
    featureCut.doSearch();

    List<List<Object>> gridData = featureCut.getGridDataRowwise(FEATURE_GRID_ID);

    while (gridData.size() > 0) {
      featureCut.selectItem(0, FEATURE_GRID_ID);
      featureCut.deleteSelectedRow();
      gridData.remove(0);
    }
    
    FeatureAutoTestHelper.createTestRecord("Запрос " + 1, featureCut);
    featureIds.add(featureCut.getFeatureId());
    FeatureAutoTestHelper.createTestRecord("Запрос " + 2, featureCut);
    featureIds.add(featureCut.getFeatureId());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
    
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAutoTestHelper.createTestRecord("Запрос " + 3, featureCut);
    featureIds.add(featureCut.getFeatureId());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 2,
      description = "Сценарий 21: Поиск по автору создания запроса.")
  public void searchByAuthor(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    featureCut.setAuthorId(USER_A_LOGIN);
    featureCut.setFromDateIns(DateFormat.getDateInstance().format(new Date()));
    featureCut.doSearch();
    for (int i = 0; i < 2; i++) {
      featureCut.selectItem(i);
      
      featureCut.setWorkstate(WorkstateEnum.VIEW_DETAILS);
      // Проверяем, что осуществился переход на форму редактирования
      AssertJUnit.assertEquals(
          WorkstateEnum.VIEW_DETAILS,
          featureCut.getWorkstateFromStatusBar());
      
      assertTrue(featureCut.getFeatureId().equals(featureIds.get(0)) || featureCut.getFeatureId().equals(featureIds.get(1)));
      
      featureCut.setWorkstate(WorkstateEnum.VIEW_LIST);
      // Проверяем, что осуществился переход на форму редактирования
      AssertJUnit.assertEquals(
          WorkstateEnum.VIEW_LIST,
          featureCut.getWorkstateFromStatusBar());
    }

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 3,
      description = "Сценарий 22: Поиск по нескольким статусам запроса.")
  public void searchByStatusCode(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем A: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut(); 
    
    FeatureProcessAuto featureProcessCut = null;
    
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds.get(0));
    }}, FEATURE_GRID_ID);
    
    switchTab(featureProcess);
    featureProcessCut = this.<FeatureProcessAuto>getCut();
    featureProcessCut.setWorkstate(CREATE);
    featureProcessCut.fillCreateForm("Назначен");
    featureProcessCut.save();
    
    switchTab(feature);
    featureCut.save();
    
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds.get(2));
    }}, FEATURE_GRID_ID);
    
    switchTab(featureProcess);
    featureProcessCut = this.<FeatureProcessAuto>getCut();
    featureProcessCut.setWorkstate(CREATE);
    featureProcessCut.fillCreateForm("В работе");
    featureProcessCut.save();
    
    switchTab(feature);
    featureCut.save();
    
    featureCut.find();
    featureCut.setFeatureId("");
    featureCut.setFeatureStatusCodeList(new String[]{"Назначен", "В работе"});
    featureCut.setFromDateIns(DateFormat.getDateInstance().format(new Date()));
    featureCut.doSearch();
    for (int i = 0; i < 2; i++) {
      featureCut.selectItem(i);
      
      featureCut.setWorkstate(WorkstateEnum.VIEW_DETAILS);
      // Проверяем, что осуществился переход на форму редактирования
      AssertJUnit.assertEquals(
          WorkstateEnum.VIEW_DETAILS,
          featureCut.getWorkstateFromStatusBar());
      
      assertTrue(featureCut.getFeatureName().equals("Запрос 1") || featureCut.getFeatureName().equals("Запрос 3"));
      
      featureCut.setWorkstate(WorkstateEnum.VIEW_LIST);
      // Проверяем, что осуществился переход на форму редактирования
      AssertJUnit.assertEquals(
          WorkstateEnum.VIEW_LIST,
          featureCut.getWorkstateFromStatusBar());
    }

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 4,
      description = "Сценарий 23. Проверка установки параметров поиска по умолчанию.")
  public void checkSearchParameters(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    Date currentDate = new Date();
    CalendarUtil.addMonthsToDate(currentDate, -6);
    assertTrue(featureCut.getFromDateIns().equals(DateFormat.getDateInstance().format(currentDate))
        && featureCut.getMaxRowCount().equals("25"));
    featureCut.setFromDateIns("");
    featureCut.setMaxRowCount("30");
    featureCut.doSearch();
    featureCut.find();
    assertTrue(featureCut.getFromDateIns().equals("**.**.****") && featureCut.getMaxRowCount().equals("25"));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 4,
      description = "Удаление тестовых данных")
  public void deleteTestData(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut(); 
    
    FeatureProcessAuto featureProcessCut = null;
    
    while (featureIds.size() > 0) {
      featureCut.edit(new HashMap<String, String>(){{
        put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds.get(0));
      }}, FEATURE_GRID_ID);
      featureCut.setWorkstate(VIEW_DETAILS);
      if (!featureCut.getFeatureStatusName().equals("")) {
        switchTab(featureProcess);
        featureProcessCut = this.<FeatureProcessAuto>getCut();
        featureProcessCut.selectItem(0);
        featureProcessCut.deleteSelectedRow();
        switchTab(feature);
      }
      featureCut.deleteDetail();
      featureIds.remove(0);
    }
    logout();
  }
}
