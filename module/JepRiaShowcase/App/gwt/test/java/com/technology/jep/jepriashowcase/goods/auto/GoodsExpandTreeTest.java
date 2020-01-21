package com.technology.jep.jepriashowcase.goods.auto;

import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;

import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.module.JepRiaModuleAuto.SaveResultEnum;
import com.technology.jep.jepria.client.ui.WorkstateEnum;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;
import com.technology.jep.test.util.DataProviderArguments;
import com.technology.jep.test.util.JepFileDataProvider;

public class GoodsExpandTreeTest extends JepRiaShowcaseAutoTest {
  
  @Override
  protected void beforeTestLaunch() {
    super.beforeTestLaunch();
    //Вход в модуль. Фактически переход по ссылке, указанной в ModuleDescription.
    enterModule(goods);
    //Логин по умолчанию (указаны в test.propertiest).
    if(!isLoggedIn()) loginDefault();
  }
  
  /**
   * Создание тестовой записи
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.treetestcreate.data")
  @Test(priority = 1, dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", 
  description = "Создание тестовой записи")
  public void createTestData(
      String goodsName,
      String goodsType,
      String unit,
      String motivation,
      String purchasingPrice,
      String goodsCatalogList) {
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();

    goodsCut.setWorkstate(CREATE);
    goodsCut.fillCreateForm(goodsName, goodsType, unit, motivation, purchasingPrice, null, null);
    goodsCut.setCatalogSections(goodsCatalogList.split(", "));
    
    // Осуществляем сохранение записи
    SaveResultEnum saveResult = goodsCut.save();
    assertEquals(SaveResultEnum.SUCCESS, saveResult);
  }
  
  /**
   * Тест разворачивания узлов в дереве и выделения в нем элементов.
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.treetestexpand.data")
  @Test(priority = 2, dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile",
      description = "Тест разворачивания узлов в дереве и выделения в нем элементов.")
  public void treeFieldNodesExpandTest(String goodsName, String goodsCatalogList) {
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.find();
    
    goodsCut.setGoodsName(goodsName);
      
    // Осуществляем поиск
    goodsCut.doSearch();
    
    goodsCut.selectItem(0);
    
    goodsCut.setWorkstate(WorkstateEnum.EDIT);
    
    assertArrayEquals(goodsCut.getCatalogSections(), goodsCatalogList.split(", "));
  }
  
  /**
   * Удаление тестовой записи
   * 
   * @param featureName - значение ключевого поля featureName, идентифицирующего тестовую запись
   */
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/goods/auto/field.treetestdeleterow.data")
  @Test(priority = 3, dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile", 
    description = "Удаление тестовой записи")
  public void delete(String goodsName) {
    
    GoodsAuto goodsCut = this.<GoodsAuto>getCut();
    
    goodsCut.find();
    
    goodsCut.setGoodsName(goodsName);
      
    // Осуществляем поиск
    goodsCut.doSearch();
    
    goodsCut.selectItem(0);
    
    // Нажимаем на кнопку удаление, подтверждаем удаление
    goodsCut.deleteSelectedRow();
  }
}

