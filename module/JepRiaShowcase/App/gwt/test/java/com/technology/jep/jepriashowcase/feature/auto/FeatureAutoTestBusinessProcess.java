package com.technology.jep.jepriashowcase.feature.auto;

import static com.technology.jep.jepria.client.JepRiaAutomationConstant.CONFIRM_MESSAGEBOX_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.CONFIRM_MESSAGE_BOX_YES_BUTTON_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.ERROR_MESSAGEBOX_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.TOOLBAR_DELETE_BUTTON_ID;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_DETAILS;
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_MAX_ROW_COUNT;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATUREID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURENAME_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_GRID_ID;
import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorAutomationConstant.FEATURE_OPERATOR_GRID_ID;
import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessAutomationConstant.FEATUREPROCESS_GRID_ID;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_ALL_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_EDIT_FEATURE;
import static com.technology.jep.jepriashowcase.main.shared.JepRiaShowcaseConstant.ROLE_JRS_OPERATOR_FEATURE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.model.user.User;
import com.technology.jep.jepria.auto.module.JepRiaModuleAuto.SaveResultEnum;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;
import com.technology.jep.jepriashowcase.featureoperator.auto.FeatureOperatorAuto;
import com.technology.jep.jepriashowcase.featureprocess.auto.FeatureProcessAuto;

public class FeatureAutoTestBusinessProcess extends JepRiaShowcaseAutoTest {
  
  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(FeatureAutoTestBusinessProcess.class.getName());
  
  /**
   * Название фичи для теста
   */
  private String featureName = "Запрос 1";
  
  /**
   * Сохраняем id созданной фичи для использования в разных сценариями. 
   */
  private String featureId = null; 
  
  
  /**
   * Пользователя, под которыми запускаются тесты:
   */
  private final static String USER_A_LOGIN = "JrsFeatureUserA";
  @SuppressWarnings("serial")
  private final static List<String> userARoles = new ArrayList<String>() {{
    add(ROLE_JRS_EDIT_FEATURE);
  }};
  
  private final static String USER_C_LOGIN = "JrsFeatureUserC";
  @SuppressWarnings("serial")
  private final static List<String> userCRoles = new ArrayList<String>() {{
    add(ROLE_JRS_EDIT_ALL_FEATURE);
  }};
  
  private final static String USER_B_LOGIN = "JrsFeatureUserB";
  @SuppressWarnings("serial")
  private final static List<String> userBRoles = new ArrayList<String>() {{
    add(ROLE_JRS_ASSIGN_RESPONSIBLE_FEATURE);
    add(ROLE_JRS_EDIT_ALL_FEATURE);
  }};
  
  private final static String USER_FEATURE_OPERATOR_LOGIN = "JrsFeatureOperatorUser";
  @SuppressWarnings("serial")
  private final static List<String> userFeatureOperatorRoles = new ArrayList<String>() {{
    add(ROLE_JRS_OPERATOR_FEATURE);
  }};
  
  /**
   * Получает featureID, текущего теста, если null, то создает тестовую запись.
   * 
   * @param featureName - значение ключевого поля featureName, идентифицирующего тестовую запись
   * @param featureCut
   */
  private String getFeatureId(String featureName, FeatureAuto featureCut) {
    
    if(featureId == null){
      
      FeatureAutoTestHelper.createTestRecord(featureName, featureCut);
      
      // Запоминаем айди запроса,
      // понадобится для дальнейшего теста.
      featureId = featureCut.getFeatureId();
    }
    
    Reporter.log("getFeatureId: "+featureId);
    return featureId;
  }
  
  /**
   * Первый сценарий тестов, в котором создается тестовая фича.
   */
  @Test(priority = 1,
      description = "Сценарий 7: Проверка недоступности поля «Ответственный» при отсутствии прав.")
  public void checkResponsibleIdWithoutPrivileges(){
    
    enterModule(feature);
    
    User userA = getUser(USER_A_LOGIN, userARoles);
    Reporter.log("Тест под пользователем A: " + userA.toString(), true);
    login(userA);

    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureId = getFeatureId(featureName, featureCut);
    
    // Проверяем, что мы находимся на форме просмотра после создания
    assertEquals(
        VIEW_DETAILS,
        featureCut.getWorkstateFromStatusBar());
    
    assertFalse(FeatureAutoTestHelper.isResponsibleEditable(featureCut));
    
    logout();
  }
  
  @Test(priority = 2,
      description = "Сценарий 8: Проверка доступности поля «Ответственный» при наличии прав.")
  public void checkResponsibleIdWithPrivileges(){
    
    enterModule(feature);
    
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureId = getFeatureId(featureName, featureCut);
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureId);
      put(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID  , featureName);
    }}, FEATURE_GRID_ID);
    assertTrue(FeatureAutoTestHelper.isResponsibleEditable(featureCut));
    
    logout();
  }
  
  @Test(priority = 2,
      description = "Подготовительный сценарий: Добавляем тестового пользователя в список пользователей запросов.")
  public void addUserAToFeatureOperator(){
   
    enterModule(featureOperator);
    
    User userFeatureOperator = getUser(USER_FEATURE_OPERATOR_LOGIN, userFeatureOperatorRoles);
    Reporter.log("Тест под пользователем: " + userFeatureOperator.toString(), true);
    login(userFeatureOperator);
        
    FeatureOperatorAuto featureOperatorCut = this.<FeatureOperatorAuto>getCut();
    
    //Проверим, что запись еще не создана
    
    featureOperatorCut.fillSearchForm(USER_A_LOGIN, Integer.toString(DEFAULT_MAX_ROW_COUNT));
    featureOperatorCut.doSearch();
    
    boolean isUserCreated = false;
    try{
      featureOperatorCut.selectItem(0, FEATURE_OPERATOR_GRID_ID);
      featureOperatorCut.setWorkstate(VIEW_DETAILS);
      
      //проверяем найденную запись
      if(featureOperatorCut.getFeatureOperatorNameFromComboBox().startsWith(USER_A_LOGIN)) {
        isUserCreated = true;
      }
      
    } catch(NoSuchElementException e){
      //грид пустой
    }
    
    if(isUserCreated == false){
      //Создаем запись
      featureOperatorCut.setWorkstate(CREATE);
      featureOperatorCut.fillCreateForm(USER_A_LOGIN);
      featureOperatorCut.save();
    }
    
    logout();
  }
  
  @Test(priority = 3, 
      description = "Сценарий 9: Изменение ответственного за запрос.")
  public void editResponsibleId(){
    
    enterModule(feature);
    
    User userB = getUser(USER_B_LOGIN, userBRoles);
    Reporter.log("Тест под пользователем B: " + userB.toString(), true);
    login(userB);
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureId = getFeatureId(featureName, featureCut);
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureId);
      put(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID  , featureName);
    }}, FEATURE_GRID_ID);
    
    assertTrue(FeatureAutoTestHelper.isResponsibleEditable(featureCut));
    
    featureCut.setResponsibleId(USER_A_LOGIN);
    
    // Осуществляем сохранение записи
    SaveResultEnum saveResult = featureCut.save();
    assertEquals(SaveResultEnum.SUCCESS, saveResult);
    
    // Проверяем, что осуществился переход на форму просмотра после редактирования
    assertEquals(
        VIEW_DETAILS,
        featureCut.getWorkstateFromStatusBar());
    
    // Проверяем, что поля отредактированной записи имеют такие же значения, как и те, которыми мы их заполнили
    assertTrue(featureCut.getResponsibleName().contains(USER_A_LOGIN));
  }
  
  @Test(priority = 4, 
      description = "Сценарий 10: Изменение запроса без изменения ответственного.")
  public void editWithoutResponsibleId() {
    
    enterModule(feature);
    
    User userA = getUser(USER_A_LOGIN, userARoles);
    Reporter.log("Тест под пользователем A: " + userA.toString(), true);
    login(userA);
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureId = getFeatureId(featureName, featureCut);
    
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureId);
      put(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID  , featureName);
    }}, FEATURE_GRID_ID);
    
    assertFalse(FeatureAutoTestHelper.isResponsibleEditable(featureCut));
    
    String description = "Новое описание - Изменение запроса без изменения ответственного - "
        + System.currentTimeMillis();
    
    featureCut.setDescription(description);
    
    assertEquals(description, featureCut.getDescription());
    
    //Запоминает значение "Ответственного" до сохранения записи.
    String responsibleName = featureCut.getResponsibleName();
    
    // Осуществляем сохранение записи
    SaveResultEnum saveResult = featureCut.save();
    assertEquals(SaveResultEnum.SUCCESS, saveResult);
    
    // Проверяем, что осуществился переход на форму просмотра после редактирования
    assertEquals(
        VIEW_DETAILS,
        featureCut.getWorkstateFromStatusBar());
    
    // Проверяем, что поля отредактированной записи имеют такие же значения, как и те, которыми мы их заполнили
    assertEquals(featureId, featureCut.getFeatureId());
    assertEquals(featureName, featureCut.getFeatureName());
    assertEquals(responsibleName, featureCut.getResponsibleName());
    assertEquals(description, featureCut.getDescription());
  }
  
  @Test(priority = 5, 
      description = "Сценарий 16: Установка статуса запроса пользователем с правами радактирования всех запросов.")
  public void createStatusForFeature(){
    
    enterModule(feature);
    
    User userC = getUser(USER_C_LOGIN, userCRoles);
    Reporter.log("Тест под пользователем A: " + userC.toString(), true);
    login(userC);
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureId = getFeatureId(featureName, featureCut);
    
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureId);
      put(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID  , featureName);
    }}, FEATURE_GRID_ID);
    
    switchTab(featureProcess);
    
    FeatureProcessAuto featureProcessCut = this.<FeatureProcessAuto>getCut();
    featureProcessCut.setWorkstate(CREATE);
    featureProcessCut.fillCreateForm(FeatureAutoTestHelper.FEATURE_STATUS_NAME_REMARK_WORKS);
    
    assertEquals(featureProcessCut.save(), SaveResultEnum.SUCCESS);
  }
  
  @Test(priority = 6, 
      description = "Сценарий 17: Удаление статуса запроса пользователем с правами радактирования всех запросов.")
  public void deleteStatusForFeature(){
    
    enterModule(feature);
    
    User userC = getUser(USER_C_LOGIN, userCRoles);
    Reporter.log("Тест под пользователем A: " + userC.toString(), true);
    login(userC);
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureId = getFeatureId(featureName, featureCut);
    
    featureCut.edit(new HashMap<String, String>(){{
      put(FEATURE_FEATUREID_DETAILFORM_FIELD_ID  , featureId);
      put(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID  , featureName);
    }}, FEATURE_GRID_ID);

    switchTab(featureProcess);

    FeatureProcessAuto featureProcessCut = this.<FeatureProcessAuto>getCut();
    
    featureProcessCut.setWorkstate(SEARCH);
    featureProcessCut.fillSearchForm(
        FeatureAutoTestHelper.FEATURE_STATUS_NAME_REMARK_WORKS, Integer.toString(DEFAULT_MAX_ROW_COUNT));
    featureProcessCut.doSearch();
    
    featureProcessCut.selectItem(0, FEATUREPROCESS_GRID_ID);
    
    // Нажимаем на кнопку удаление, подтверждаем удаление
    featureProcessCut.clickButton(TOOLBAR_DELETE_BUTTON_ID);
    assertEquals(true, featureProcessCut.checkMessageBox(CONFIRM_MESSAGEBOX_ID));
    featureProcessCut.clickButton(CONFIRM_MESSAGE_BOX_YES_BUTTON_ID);
    
    assertEquals(false, featureProcessCut.checkMessageBox(ERROR_MESSAGEBOX_ID));
    //TODO: добавить проверки дата и время создания статуса
  }
}
