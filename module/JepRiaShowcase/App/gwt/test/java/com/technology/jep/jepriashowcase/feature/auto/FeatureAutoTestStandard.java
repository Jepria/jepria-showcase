package com.technology.jep.jepriashowcase.feature.auto;

import static com.technology.jep.jepria.auto.util.WebDriverFactory.getWait;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.LOGOUT_BUTTON_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.TOOLBAR_FIND_BUTTON_ID;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.EDIT;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_DETAILS;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_GRID_ID;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.module.JepRiaModuleAuto.SaveResultEnum;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;
import com.technology.jep.test.util.DataProviderArguments;
import com.technology.jep.test.util.JepFileDataProvider;

public class FeatureAutoTestStandard extends JepRiaShowcaseAutoTest {
  
  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(FeatureAutoTestStandard.class.getName());
  
  /**
   * Значение идентифицирующего поля записи (псевдо-ID, используется вместо PrimaryKey) 
   */
  protected final String KEY_FIELD_VALUE = "featureName_" + System.currentTimeMillis();
  
  @Override
  protected void beforeTestLaunch() {
    super.beforeTestLaunch();
    //Вход в модуль. Фактически переход по ссылке, указанной в ModuleDescription.
    enterModule(feature);
    //Логин по умолчанию (указаны в test.propertiest).
    if(!isLoggedIn()) loginDefault();
  }
  
  /**
   * Собственно тест создания
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/feature/auto/form.create.data")
  @Test(dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile",
      description = "Стандартный сценарий: Создание записи.")
  public void create(String featureName, String featureNameEn, String description) {
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.setWorkstate(CREATE);
    
    // Заполняем форму создания
    featureCut.fillCreateForm(
        featureName,
        featureNameEn,
        description);
    
    // Проверяем, что поля заполненны именно теми значениями, которыми мы их заполнили
    assertEquals(featureName, featureCut.getFeatureName());
    assertEquals(featureNameEn, featureCut.getFeatureNameEn());
    assertEquals(description, featureCut.getDescription());
    
    // Осуществляем сохранение записи
    SaveResultEnum saveResult = featureCut.save();
    assertEquals(SaveResultEnum.SUCCESS, saveResult);
    
    // Проверяем, что осуществился переход на форму просмотра после создания
    assertEquals(
        VIEW_DETAILS,
        featureCut.getWorkstateFromStatusBar());

     // Проверяем, что поля созданной записи имеют такие же значения, как и те, которыми мы их заполнили
    assertEquals(featureName, featureCut.getFeatureName());
    assertEquals(featureNameEn, featureCut.getFeatureNameEn());
    assertEquals(description, featureCut.getDescription());
  }
  
  /**
   * Тест операции поиска по шаблону
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/feature/auto/form.search.data")
  @Test(dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile",
      description = "Стандартный сценарий: Поиск по шаблону.")
  public void find(String featureId, String featureName, String featureNameEn, String fromDateIns, String toDateIns, String maxRowCount) {
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    featureCut.find();
    
    // Заполняем форму поиска
    featureCut.fillSearchForm(
        featureId,
        featureName,
        featureNameEn,
        "",
        "",
        fromDateIns,
        toDateIns,
        maxRowCount);
    
    // Проверяем, что поля заполненны именно теми значениями, которыми мы их заполнили
    assertEquals(featureId, featureCut.getFeatureId());
    assertEquals(featureName, featureCut.getFeatureName());
    assertEquals(featureNameEn, featureCut.getFeatureNameEn());
    assertEquals(fromDateIns, featureCut.getFromDateIns());
    assertEquals(toDateIns, featureCut.getToDateIns());
    assertEquals(maxRowCount, featureCut.getMaxRowCount());
      
    // Осуществляем поиск
    featureCut.doSearch();
    
    // Проверяем, что данные в списке имеют значения, соответствующие поисковому шаблону
    // В данном случае, проверяем только столбец "Наименование".
    //TODO: Реализовать автоматизацию для списочной формы. Получаение столбка по html-id (а не имени).
    List<String> headers = featureCut.getGridHeaders(FEATURE_GRID_ID);
    List<List<Object>> table = featureCut.getGridDataRowwise(FEATURE_GRID_ID);
    int index = headers.indexOf("Наименование"); //TODO extract hardcode [featureCut.getFeatureNameHeader()]
    for (List<Object> row: table) {
      assertTrue(((String)row.get(index)).contains(featureName));
    }
  }

  /**
   * Тест удаления записи
   * 
   * @param featureName - значение ключевого поля featureName, идентифицирующего тестовую запись
   */
  @Test(description = "Стандартный сценарий: Удаление записи.")
  public void delete() {
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    FeatureAutoTestHelper.createTestRecord(KEY_FIELD_VALUE, featureCut);
    
    // Проверяем, что мы находимся на форме просмотра после создания
    assertEquals(
        VIEW_DETAILS,
        featureCut.getWorkstateFromStatusBar());
    
    // Запоминаем feature_id созданной записи, чтобы далее проверить корректность удаления
    final String feature_id = featureCut.getFeatureId();
    
    // Нажимаем на кнопку удаление, подтверждаем удаление
    featureCut.deleteDetail();
    
    // Проверка, что фича не ищется через поиск.

    getWait().until(elementToBeClickable(By.id(TOOLBAR_FIND_BUTTON_ID)));
    
    featureCut.find();
    featureCut.setFeatureId(feature_id);
    featureCut.doSearch();
    
    assert (featureCut.getGridDataRowwise(FEATURE_GRID_ID).isEmpty());
  }
  
  /**
   * Собственно тест редактирования
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/feature/auto/form.edit.data")
  @Test(dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile",
      description = "Стандартный сценарий: Редактирование записи.")
  public void edit(String featureName, String featureNameEn) {
    
    FeatureAuto featureCut = this.<FeatureAuto>getCut();
    
    // создаем запись, которую собираемся редактировать
    FeatureAutoTestHelper.createTestRecord("ABCDE", featureCut);
    
    // переходим в состояние редактирования
    featureCut.setWorkstate(EDIT);
    
    // Редактируем поля
    featureCut.fillEditForm(
        featureName,
        featureNameEn,
        "");
    
    // Проверяем, что поля заполнены именно теми значениями, которыми мы их заполнили
    assertEquals(featureName, featureCut.getFeatureName());
    assertEquals(featureNameEn, featureCut.getFeatureNameEn());
    
    // Осуществляем сохранение записи
    SaveResultEnum saveResult = featureCut.save();
    assertEquals(SaveResultEnum.SUCCESS, saveResult);
    
    // Проверяем, что осуществился переход на форму просмотра после редактирования
    assertEquals(
        VIEW_DETAILS,
        featureCut.getWorkstateFromStatusBar());
    
    // Проверяем, что поля отредактированной записи имеют такие же значения, как и те, которыми мы их заполнили
    assertEquals(featureName, featureCut.getFeatureName());
    assertEquals(featureNameEn, featureCut.getFeatureNameEn());
  }
  
  @Test(description = "Проверка изменения числа записей на списочной форме.")
  public void gridRowNumberChangeTest() {
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    featureCut.doSearch();
    featureCut.setGridRowCount("40");
    assertTrue(
        featureCut.getGridRowCount().equals("40"));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }

  @Test(description = "Тестирование блокирования интерфейса во время выполнение запроса на удаление записи(ей) на списочной форме.")
  public void deleteGlassMaskTestListView(){
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    featureCut.setMaxRowCount("5");
    featureCut.doSearch();
    int rowCount = featureCut.getGridDataRowwise(FEATURE_GRID_ID).size();
    for (int i = 1; i < rowCount; i++) {
      featureCut.selectItems(i, FEATURE_GRID_ID);
    }
    featureCut.deleteSelectedRow();
    assertFalse(featureCut.isDeleteButtonClickable());
    getWait().until(elementToBeClickable(By.id(LOGOUT_BUTTON_ID)));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
  
  @Test(description = "Тестирование блокирования интерфейса во время выполнение запроса на удаление записи(ей) на детальной форме.")
  public void deleteGlassMaskTestDetailView(){
    FeatureAuto featureCut = this.<FeatureAuto>getCut();

    featureCut.find();
    featureCut.setMaxRowCount("5");
    featureCut.doSearch();
    featureCut.selectItem(0);
    featureCut.setWorkstate(EDIT);
    featureCut.deleteDetail();
    assertFalse(featureCut.isDeleteButtonClickable());
    getWait().until(elementToBeClickable(By.id(LOGOUT_BUTTON_ID)));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logout();
  }
}
