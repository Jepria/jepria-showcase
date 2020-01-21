package com.technology.jep.jepriashowcase.goods.auto;

import static com.technology.jep.jepria.client.JepRiaAutomationConstant.ERROR_MESSAGE_BOX_OK_BUTTON_ID;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.EDIT;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SEARCH;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SELECTED;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_LIST;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_GOODS_NAME_TEXT_FIELD_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.exception.WrongOptionException;
import com.technology.jep.jepria.auto.module.JepRiaModuleAuto.SaveResultEnum;
import com.technology.jep.jepria.shared.exceptions.NotImplementedYetException;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;
import com.technology.jep.test.util.DataProviderArguments;
import com.technology.jep.test.util.JepFileDataProvider;
import com.technology.jep.test.util.JepTestUtil;

@SuppressWarnings("serial")
@Deprecated
public class GoodsAutoTest extends JepRiaShowcaseAutoTest {
  
  private static Logger logger = Logger.getLogger(GoodsAutoTest.class.getName());
  
  /**
   * ID Web-элемента идентифицирующего поля записи - 'Наименование товара' 
   */
  private final String KEY_FIELD_WEB_ELEMENT_ID = GOODS_GOODS_NAME_TEXT_FIELD_ID;
  /**
   * Значение идентифицирующего поля записи (псевдо-ID, используется вместо PrimaryKey) 
   */
  private final String KEY_FIELD_VALUE = "TestName_" + System.currentTimeMillis();
  

  /**
   * Тест операции поиска по пустому шаблону
   */
  //TODO: find waitTextToBeChanged and restore test
//  @Test(groups = "find")
//  public void find() {
//    getCut().find();
//    
//    String statusBarTextBefore = getCut().getStatusBarText();
//    getCut().doSearch();
//    getCut().waitTextToBeChanged(getCut().getStatusBar(), statusBarTextBefore);
//        
//    assertEquals(
//        Util.getResourceString("workstate.viewList"),
//        getCut().getStatusBarText());
//  }

  /**
   * Тест операции поиска по заданному шаблону
   */
  @Test(groups = "find")
  public void findByTemplate(Map<String, String> template) {
    fail("Тест не реализован");
  }

  /**
   * Тест перехода на форму поиска
   */
  @Test(groups = "goto!")
  public void goToSearch() {
    getCut().find();
        
    assertEquals(
        SEARCH,
        getCut().getWorkstateFromStatusBar());
  }

  /**
   * Тест перехода на форму создания
   */
  @Test(groups = {"create", "goto!"})
  public void goToCreate() {
    getCut().setWorkstate(CREATE);
        
    assertEquals(
        CREATE,
        getCut().getWorkstateFromStatusBar());
  }
  
  /*
   * Создание 
   */
  /**
   * Тест заполнения формы поиска
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/form.search.data")
  @Test(groups="find", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void fillSearchForm(String goodsName, String goodsType, String unit, String goodsSegment, String strGoodsCatalogSections) {
    getCut().find();
        
    Set<String> goodsCatalogSections = JepTestUtil.parseCSV(strGoodsCatalogSections);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
/*    goodsCut.fillSearchForm(
            goodsName,
            goodsType,
            unit,
            goodsSegment,
            goodsCatalogSections);*/
        
      assertEquals(goodsName, goodsCut.getGoodsName());
      assertEquals(goodsType, goodsCut.getGoodsType());
      assertEquals(unit, goodsCut.getUnit());
      assertEquals(goodsSegment, goodsCut.getSegment());
      assertEquals(goodsCatalogSections, goodsCut.getCatalogSections());
  }

  
  /**
   * Тест заполнения формы создания
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/form.create.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void fillCreateForm(String goodsName, String goodsType, String unit, String motivation, String purchasingPrice) {
    getCut().setWorkstate(CREATE);
    
    final String GOODS_PHOTO = "...";      // TODO Поддержать выбор файла
    final String GOODS_SPECIFICATION = "...";  // TODO Поддержать выбор файла
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.fillCreateForm(
        goodsName,
        goodsType,
        unit,
        motivation,
        purchasingPrice,
        GOODS_PHOTO,
        GOODS_SPECIFICATION);
    
    assertEquals(goodsName, goodsCut.getGoodsName());
    assertEquals(goodsType, goodsCut.getGoodsType());
    assertEquals(unit, goodsCut.getUnit());
    assertEquals(motivation, goodsCut.getMotivation());
    assertEquals(purchasingPrice, goodsCut.getPurchasingPrice());
  }
  

  /**
   * Тест установки/получения поля 'Наименование товара' на форме поиска
   * 
   * @param goodsNameNewValue - устанавливаемое значение поля 'Наименование товара'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.goodsName.data")
  @Test(groups= "setAndGetFields", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetGoodsNameOnSearch(String goodsNameNewValue) {
    getCut().find();
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setGoodsName(goodsNameNewValue);
        
    assertEquals(goodsNameNewValue, goodsCut.getGoodsName());
  }

  /**
   * Тест установки/получения поля 'Наименование товара' на форме создания
   * 
   * @param goodsNameNewValue - устанавливаемое значение поля 'Наименование товара'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.goodsName.data")
  @Test(groups={"create", "setAndGetFields"}, dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetGoodsNameOnCreate(String goodsNameNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setGoodsName(goodsNameNewValue);
        
    assertEquals(goodsNameNewValue, goodsCut.getGoodsName());
  }
  
  /**
   * Тест установки/получения поля 'Тип товара' на форме создания
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.goodsType.data")
  @Test(groups={"create", "setAndGetFields"}, dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetGoodsTypeOnCreate(String goodsTypeNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setGoodsType(goodsTypeNewValue);
        
    assertEquals(goodsTypeNewValue, goodsCut.getGoodsType());
  }
  
  /**
   * Тест установки некорретного значения поля 'Тип товара' на форме создания
   * 
   * @param typeNewValue - устанавливаемое  значение поля 'Тип товара'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.goodsType.incorrect.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = WrongOptionException.class)
  public void setWrongTypeOnCreate(String wrongTypeNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setGoodsType(wrongTypeNewValue);
  }

  /**
   * Тест установки/получения поля 'Единицы измерения' на форме создания
   * 
   * @param unitNewValue - устанавливаемое значение поля 'Единицы измерения'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.unit.data ")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetUnitOnCreate(String unitNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setUnit(unitNewValue);
        
    assertEquals(unitNewValue, goodsCut.getUnit());
  }
  
  /**
   * Тест установки некорретного значения поля 'Единицы измерения' на форме создания
   * 
   * @param unitNewValue - устанавливаемое  значение поля 'Единицы измерения'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.unit.incorrect.create.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = WrongOptionException.class)
  public void setWrongUnitOnCreate(String wrongUnitNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setUnit(wrongUnitNewValue);
  }
  

  /**
   * Тест установки/получения поля 'Мотивация' на форме создания
   * 
   * @param motivationNewValue - устанавливаемое значение поля 'Мотивация'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.motivation.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetMotivationOnCreate(String motivationNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setMotivation(motivationNewValue);
        
    assertEquals(motivationNewValue, goodsCut.getMotivation());
  }
  
  /**
   * Тест установки некорретного значения поля 'Мотивация' на форме создания
   * 
   * @param motivationNewValue - устанавливаемое значение поля 'Мотивация'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.motivation.incorrect.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = WrongOptionException.class)
  public void setWrongMotivationOnCreate(String wrongMotivationNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setMotivation(wrongMotivationNewValue);
  }
  
  /**
   * Тест установки/получения значения поля 'Закупочная цена' на форме создания
   * 
   * @param purchasingPriceNewValue - устанавливаемое  значение поля 'Закупочная цена'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.purchasingPrice.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setPurchasingPriceOnCreate(String purchasingPriceNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setPurchasingPrice(purchasingPriceNewValue);
        
    assertEquals(purchasingPriceNewValue, goodsCut.getPurchasingPrice());
  }
  
  
  /**
   * Тест установки некорретного значения поля 'Закупочная цена' на форме создания
   * 
   * @param purchasingPriceNewValue - устанавливаемое значение поля 'Закупочная цена'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.purchasingPrice.incorrect.data")
  @Test(groups="create", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setWrongPurchasingPriceOnCreate(String wrongPurchasingPriceNewValue) {
    getCut().setWorkstate(CREATE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setPurchasingPrice(wrongPurchasingPriceNewValue);
        
    assertFalse(wrongPurchasingPriceNewValue.equals(goodsCut.getPurchasingPrice()));
  }

  /**
   * Тест перехода на форму редактирования
   *  
   * @param goodsName - значение поля 'Наименование товара', идентифицирующее тестовую запись
   */
  @Test(groups = "edit")
  @Parameters({"KEY_FIELD_VALUE"})
  public void edit(String goodsName) {
    Map<String, String> key = new HashMap<String, String>();
    key.put(GOODS_GOODS_NAME_TEXT_FIELD_ID, goodsName);
    getCut().edit(key);
        
    assertEquals(
        EDIT,
        getCut().getWorkstateFromStatusBar());
  }

  /**
   * Тест перехода на списочную форму 
   * @param goodsName - значение поля 'Наименование товара', идентифицирующее тестовую запись
   */
  @Test(groups = {"list", "goto!"})
//  @Parameters({"KEY_FIELD_VALUE"})
  public void goToList(final String goodsName) {
    getCut().doSearch(new HashMap<String, String>(){{
      put(GOODS_GOODS_NAME_TEXT_FIELD_ID, goodsName);
    }});
        
    assertEquals(
        VIEW_LIST,
        getCut().getWorkstateFromStatusBar());
  }

  /**
   * Тест выделения первого элемента списка списочной формы
   * @param goodsName - значение поля 'Наименование товара', идентифицирующее тестовую запись
   */
  @Test(groups = "list")
//  @Parameters({"KEY_FIELD_VALUE"})
  public void selectFirstItem(final String goodsName) {
    getCut().doSearch(new HashMap<String, String>(){{
      put(GOODS_GOODS_NAME_TEXT_FIELD_ID, goodsName);
    }});
    
    getCut().selectItem(0);
    
    assertEquals(
        SELECTED,
        getCut().getWorkstateFromStatusBar());
  }
  
  /**
   * Тест установки/получения поля 'Наименование товара' на форме редактирования
   * 
   * @param goodsNameNewValue - значение поля 'Наименование товара'
   */
//TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.goodsName.data")
//  @Test(groups = {"edit", "setAndGetFields"}, dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
//  public void setAndGetGoodsNameOnEdit(String goodsNameNewValue) {
//    
//    testSetAndGetTextFieldValueOnEdit(
//        GOODS_GOODS_NAME_TEXT_FIELD_ID,
//        goodsNameNewValue);
//  }
  
  /**
   * Тест установки/получения поля 'Тип товара' на форме редактирования
   * 
   * @param typeFieldValue - значение поля 'Тип товара'
   */
//TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/setAndGetTypeOnEdit.method.data")
//  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
//  public void setAndGetTypeOnEdit(String typeFieldValue) {
//    
//    testSetAndGetComboBoxFieldValueOnEdit(
//        GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID,
//        typeFieldValue);
//  }

  /**
   * Тест установки некорректных значений поля 'Единица измерения' на форме редактирования
   * 
   * @param wrongUnitFieldValue - значение поля 'Единица измерения', идентифицирующее тестовую запись
   */
//TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/setWrongTypeOnEdit.method.data")
//  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = WrongOptionException.class)
//  public void setWrongTypeOnEdit(String wrongTypeFieldValue) {
//    
//    testSetAndGetComboBoxFieldValueOnEdit(
//        GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID,
//        wrongTypeFieldValue);
//  }
  
  /**
   * Тест установки/получения значения поля 'Единица измерения' на форме редактирования
   * 
   * @param unitFieldValue - значение поля 'Единица измерения'
   */
//TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/setAndGetUnitOnEdit.method.data")
//  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
//  public void setAndGetUnitOnEdit(String unitFieldValue) {
//    
//    testSetAndGetComboBoxFieldValueOnEdit(
//        GOODS_UNIT_COMBOBOX_FIELD_ID,
//        unitFieldValue);
//    
//  }

  /**
   * Тест установки некорректных значений поля 'Единица измерения' на форме редактирования
   * 
   * @param wrongUnitFieldValue - значение поля 'Единица измерения', идентифицирующее тестовую запись
   */
//TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.unit.incorrect.edit.data")
//  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = WrongOptionException.class)
//  public void setWrongUnitOnEdit(String wrongUnitFieldValue) {
//    
//    testSetAndGetComboBoxFieldValueOnEdit(
//        GOODS_UNIT_COMBOBOX_FIELD_ID,
//        wrongUnitFieldValue);
//  }

  /**
   * Тест установки значения поля 'Мотивация' на форме редактирования
   * 
   * @param motivationFieldValue - значение поля 'Мотивация'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/setAndGetMotivationOnEdit.method.data")
  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetMotivationOnEdit(String motivationFieldValue) {
    try {
      createTestRecord(KEY_FIELD_VALUE);
      
      getCut().edit(new HashMap<String, String>() {{
        put(KEY_FIELD_WEB_ELEMENT_ID, KEY_FIELD_VALUE);
      }});
      
      GoodsAuto goodsCut = this.<GoodsAuto>getCut();
      
      goodsCut.setMotivation(motivationFieldValue);
      assertEquals(motivationFieldValue, goodsCut.getMotivation());
    } finally {
      deleteTestRecord(KEY_FIELD_VALUE);
    }
  }

  /**
   * Тест установки некорректных значений поля 'Мотивация' на форме редактирования
   * 
   * @param wrongMotivationFieldValue - устанавливаемое значение поля 'Мотивация'
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/setWrongMotivationOnEdit.method.data")
  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = WrongOptionException.class)
  public void setWrongMotivationOnEdit(String wrongMotivationFieldValue) {
    try {
      createTestRecord(KEY_FIELD_VALUE);
      
      getCut().edit(new HashMap<String, String>() {{
        put(KEY_FIELD_WEB_ELEMENT_ID, KEY_FIELD_VALUE);
      }});
      
      GoodsAuto goodsCut = this.<GoodsAuto>getCut();
      
      goodsCut.setMotivation(wrongMotivationFieldValue);
    } finally {
      deleteTestRecord(KEY_FIELD_VALUE);
    }
  }
  
  /**
   * Тест установки/чтения значения поля 'Закупочная цена' на форме редактирования
   * 
   * @param purchasingPriceNewValue - устанавливаемое значение поля 'Закупочная цена'
   */
//TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.purchasingPrice.data")
//  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
//  public void setAndGetPurchasingPriceOnEdit(String purchasingPriceNewValue) {
//    
//    testSetAndGetTextFieldValueOnEdit(
//        GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID,
//        purchasingPriceNewValue);
//  }
  
  /**
   * Тест установки/чтения значения поля 'Закупочная цена' на форме редактирования
   * 
   * @param purchasingPriceNewValue - устанавливаемое значение поля 'Закупочная цена'
   */
//  TODO: replace testSetAndGetTextFieldValueOnEdit and restore test
//  do not need general test, tests business process more important
//  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.purchasingPrice.incorrect.data ")
//  @Test(groups = "edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
//  public void setWrongPurchasingPriceOnEdit(String purchasingPriceNewValue) {
//    
//    testSetAndGetTextFieldValueOnEdit(
//        cut,
//        KEY_FIELD_WEB_ELEMENT_ID,
//        KEY_FIELD_VALUE,
//        GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID,
//        purchasingPriceNewValue,
//        false);
//  }
  

  /**
   * Тест заполнения формы редактирования
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/form.edit.data")
  @Test(groups="edit", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void fillEditForm(final String goodsName, String goodsType, String unit, String motivation, String purchasingPrice) {

    try {
      createTestRecord(goodsName);
      
      getCut().edit(new HashMap<String, String>() {{
        put(GOODS_GOODS_NAME_TEXT_FIELD_ID, goodsName);
      }});
      
      final String GOODS_PHOTO = "...";      // TODO Поддержать выбор файла
      final String GOODS_SPECIFICATION = "...";  // TODO Поддержать выбор файла
      
      GoodsAuto goodsCut = this.<GoodsAuto>getCut();
      
      goodsCut.fillEditForm(
          goodsName,
          goodsType,
          unit,
          motivation,
          purchasingPrice,
          GOODS_PHOTO,
          GOODS_SPECIFICATION);
      
      assertEquals(goodsName, goodsCut.getGoodsName());
      assertEquals(goodsType, goodsCut.getGoodsType());
      assertEquals(unit, goodsCut.getUnit());
      assertEquals(motivation, goodsCut.getMotivation());
      assertEquals(purchasingPrice, goodsCut.getPurchasingPrice());
    } finally {
      deleteTestRecord(goodsName);
    }
  }
  
  /**
   * Тест сохранения записи 
   */
  @Test(groups = "edit, create")
  public void save_AlertMessageBoxShouldAppear() {
    getCut().setWorkstate(CREATE);
    
    assertEquals(SaveResultEnum.ALERT_MESSAGE_BOX, getCut().save());
  }
  
  /**
   * Тест проверки появления popup-окна после нажатия на save при незаполненных полях 
   */
  @Test(groups = "edit, create")
  public void save() {
    getCut().setWorkstate(CREATE);
    
    final String GOODS_NAME = "Вино";
    final String GOODS_TYPE = "Продукты питания";
    final String UNIT = "Литры";
    final String MOTIVATION = "Процент с дохода";
    final String PURCHASING_PRICE = "12345.12";
    final String GOODS_PHOTO = "...";      // TODO Поддержать выбор файла
    final String GOODS_SPECIFICATION = "...";  // TODO Поддержать выбор файла
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.fillCreateForm(
        GOODS_NAME,
        GOODS_TYPE,
        UNIT,
        MOTIVATION,
        PURCHASING_PRICE,
        GOODS_PHOTO,
        GOODS_SPECIFICATION);

    assertEquals(SaveResultEnum.SUCCESS, getCut().save());
  }
  
  @Test
  @Parameters({"KEY_FIELD_VALUE"})
  public void selectItemByIndex(String goodsName) {
    fail("Не реализовано");
  }
  
  @Test
  @Parameters({"KEY_FIELD_VALUE"})
  public void selectItemByKey(String goodsName) {
    fail("Не реализовано");
  }

  /**
   * Тест удаления записи
   * 
   * @param KEY_FIELD_VALUE - значение поля 'Наименование товара', идентифицирующее тестовую запись
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/delete.data")
  @Test(groups = "delete", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", expectedExceptions = IndexOutOfBoundsException.class)
  public void delete(final String goodsNameFieldValue) {
    try {
      createTestRecord(goodsNameFieldValue);
      
      Map<String, String> key = new HashMap<String, String>() {{put(GOODS_GOODS_NAME_TEXT_FIELD_ID, goodsNameFieldValue);}};
      getCut().doSearch(key);
      
      try {
        getCut().selectItem(0);
        getCut().deleteSelectedRow();
      } catch(Exception ex) {
            fail("Элемент с ключом " + key + " отсутствует в списке");
      }
      
    } finally {
          deleteTestRecord(goodsNameFieldValue); // На случай, если getCut().delete не сработал
    }
  }
  
  /**
   * Создание тестовой записи
   * 
   * @param KEY_FIELD_VALUE - значение поля 'Наименование товара', идентифицирующее тестовую запись
   */
  protected void createTestRecord(String goodsNameFieldValue) {
    logger.trace("createRecordForTest()");
    getCut().setWorkstate(CREATE);
    
    final String GOODS_TYPE = "Продукты питания";
    final String UNIT = "Литры";
    final String MOTIVATION = "Процент с дохода";
    final String PURCHASING_PRICE = "12345.12";
    final String GOODS_PHOTO = "...";      // TODO Поддержать выбор файла
    final String GOODS_SPECIFICATION = "...";  // TODO Поддержать выбор файла
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.fillCreateForm(
        goodsNameFieldValue,
        GOODS_TYPE,
        UNIT,
        MOTIVATION,
        PURCHASING_PRICE,
        GOODS_PHOTO,
        GOODS_SPECIFICATION);

    SaveResultEnum saveResult = getCut().save();
    if(saveResult != SaveResultEnum.SUCCESS) {
        if(saveResult == SaveResultEnum.ERROR_MESSAGE_BOX) { // TODO Предполагаем, что запись уже существует, но нужно всё-таки убедиться
        logger.warn("createRecordForTest(): Запись '" + goodsNameFieldValue +"' уже существует");
        getCut().clickButton(ERROR_MESSAGE_BOX_OK_BUTTON_ID);
        } else {
            fail("Ошибка создания тестовой записи");
        }
    }
  }
  
  
  private void deleteTestRecord(final String testFieldValue) {
    throw new NotImplementedYetException();
//    getCut().delete(new HashMap(){{
//      put(GOODS_GOODS_NAME_TEXT_FIELD_ID, testFieldValue);
//    }});
  }

//  private void testSetAndGetTextFieldValueOnEdit(String testFieldId, String testFieldNewValue) {
//    testSetAndGetTextFieldValueOnEdit(
//        cut,
//        GOODS_GOODS_NAME_TEXT_FIELD_ID,
//        KEY_FIELD_VALUE,
//        testFieldId,
//        testFieldNewValue,
//        true);
//  }
//  
//  private void testSetAndGetComboBoxFieldValueOnEdit(String testFieldId, String testFieldNewValue) {
//    testSetAndGetComboBoxFieldValueOnEdit(
//        cut,
//        GOODS_GOODS_NAME_TEXT_FIELD_ID,
//        KEY_FIELD_VALUE,
//        testFieldId,
//        testFieldNewValue);
//  }
  
  /*
   * Набросок для исполнения скрипта через последовательность вызовов тестового метода
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/jepRiaCommandsTest.jrc ")
  @Test(groups = "jrc", dataProviderClass = JepFileDataProvider.class, dataProvider="JepRiaCommands")
  public void jepRiaCommandsTest(String command, List<String> parameters) {
    fail("Не реализовано");
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/photoAndPortfolio.group.data")
  @Test(groups= "setAndGetFields", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void editLargeFields(String photoPath, String portfolioPath) {
    createTestRecord(KEY_FIELD_VALUE);
    edit(KEY_FIELD_VALUE);
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.setPhoto(photoPath);
    goodsCut.setPortfolio(portfolioPath);
    
    SaveResultEnum saveResult = getCut().save();
    assertEquals(SaveResultEnum.SUCCESS, saveResult);
  }

  @Override
  protected void beforeTestLaunch() {
    
    super.beforeTestLaunch();
    enterModule(goods);
  }
}
