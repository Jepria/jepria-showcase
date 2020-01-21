package com.technology.jep.jepriashowcase.feature.auto;

import static com.technology.jep.jepria.client.ui.WorkstateEnum.EDIT;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATUREID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FROMDATEINS_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_GRID_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_ALL_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_FEATURE;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.model.user.User;
import com.technology.jep.jepria.auto.util.WebDriverFactory;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;

public class FeatureAutoTestDragAndDrop extends JepRiaShowcaseAutoTest {
  
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
    add(ROLE_JRS_ASSIGN_WORK_SEQUENCE_FEATURE);
  }};
  
  private String [] featureIds = new String[4];
  
  @Test(priority = 1,
      description = "Подготовка сценариев. Очистка 'мусорных' записей. Создание тестовых записей 'пользователем А'")
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
    
    for (int i = 0; i < 4; i++) {
      FeatureAutoTestHelper.createTestRecord("Запрос " + (i + 1), featureCut);
      // Запоминаем айди запроса,
      // понадобится для дальнейшего теста.
      featureIds[i] = featureCut.getFeatureId();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 2,
      description = "Сценарий 28: Проверка недоступности поля «Порядок выполнения» при отсутствии прав.")
  public void checkWorkSequenceWithoutPrivileges(){
    enterModule(feature);
    User userA = getUser(USER_A_LOGIN, userARoles);
    Reporter.log("Тест под пользователем A: " + userA.toString(), true);
    login(userA);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    featureCut.setFeatureId(featureIds[0]);
    featureCut.doSearch();
    featureCut.selectItem(0);
    
    featureCut.setWorkstate(WorkstateEnum.EDIT);
    // Проверяем, что осуществился переход на форму редактирования
    AssertJUnit.assertEquals(
        EDIT,
        featureCut.getWorkstateFromStatusBar());
    
    assertFalse(featureCut.isFieldEditable(FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 3,
      description = "Сценарий 29: Проверка доступности поля «Порядок выполенения» при наличии прав.")
  public void checkWorkSequenceWithPrivileges(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    featureCut.setFeatureId(featureIds[0]);
    featureCut.doSearch();
    featureCut.selectItem(0);
    
    featureCut.setWorkstate(WorkstateEnum.EDIT);
    // Проверяем, что осуществился переход на форму редактирования
    AssertJUnit.assertEquals(
        EDIT,
        featureCut.getWorkstateFromStatusBar());
    
    assertTrue(featureCut.isFieldEditable(FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 4,
      description = "Сценарий 30: Изменение порядка выполнения запроса.")
  public void editWorkSequence(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds[0]);
    }}, FEATURE_GRID_ID);
    featureCut.setWorkSequence("1");
    featureCut.save();
    assertTrue(featureCut.getWorkSequence().equals("1"));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 5,
      description = "Сценарий 31: Изменение запроса без изменения ответственного.")
  public void editFeatureRequestWithoutChangingResponsiblePerson(){
    enterModule(feature);
    User userA = getUser(USER_A_LOGIN, userARoles);
    Reporter.log("Тест под пользователем A: " + userA.toString(), true);
    login(userA);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds[0]);
    }}, FEATURE_GRID_ID);
    final String descriptionOld = featureCut.getDescription();
    featureCut.setDescription("Изменение запроса без изменения ответственного " + new Random().nextInt());
    featureCut.save();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertFalse(featureCut.getDescription().equals(descriptionOld));
    
    logout();
  }
  
  @Test(priority = 6,
      description = "Сценарий 32: Изменение порядка выполнения запроса используя drag-and-drop на списочной форме.")
  public void changeWorkSequenceWithDnd(){
    enterModule(feature);
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds[0]);
    }}, FEATURE_GRID_ID);
    featureCut.setWorkSequence("1");
    featureCut.save();
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds[1]);
    }}, FEATURE_GRID_ID);
    featureCut.setWorkSequence("2");
    featureCut.save();
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureIds[2]);
    }}, FEATURE_GRID_ID);
    featureCut.setWorkSequence("1");
    featureCut.save();
    final Date currentDate = new Date();
    
    featureCut.find();
    featureCut.setFeatureId("");
    featureCut.setFromDateIns(DateFormat.getDateInstance().format(currentDate));
    featureCut.setAuthorId(USER_A_LOGIN);
    featureCut.doSearch();
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    featureCut.sortByColumn(FEATURE_GRID_ID, 1);
    
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    featureCut.dragAndDropGridRowBeforeTarget(3, 2, FEATURE_GRID_ID);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    featureCut.dragAndDropGridRowAfterTarget(0, 2, FEATURE_GRID_ID);
    
    featureCut.find();
    featureCut.setFeatureId("");
    featureCut.setFromDateIns(DateFormat.getDateInstance().format(currentDate));
    featureCut.setAuthorId(USER_A_LOGIN);
    featureCut.doSearch();
    featureCut.sortByColumn(FEATURE_GRID_ID, 1);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List<List<Object>> gridData = featureCut.getGridDataRowwise(FEATURE_GRID_ID);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertTrue(gridData.get(0).get(3).equals("Запрос 3") && gridData.get(0).get(1).equals("1"));
    assertTrue(gridData.get(1).get(3).equals("Запрос 2") && gridData.get(1).get(1).equals("2"));
    assertTrue(gridData.get(2).get(3).equals("Запрос 4") && gridData.get(2).get(1).equals("3"));
    assertTrue(gridData.get(3).get(3).equals("Запрос 1") && gridData.get(3).get(1).equals("4"));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(priority = 7,
      description = "Удаление созданных тестовых записей.")
  public void deleteTestData(){
    enterModule(feature);
    User userA = getUser(USER_A_LOGIN, userARoles);
    Reporter.log("Тест под пользователем A: " + userA.toString(), true);
    login(userA);
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    final Date currentDate = new Date();
    
    featureCut.find();
    featureCut.setFromDateIns(DateFormat.getDateInstance().format(currentDate));
    featureCut.setAuthorId(USER_A_LOGIN);
    featureCut.doSearch();
    
    int rowCount = 4;
    while (rowCount > 0) {
      featureCut.selectItem(0, FEATURE_GRID_ID);
      featureCut.deleteSelectedRow();
      rowCount-=1;
    }
    logout();
  }
}
