package com.technology.jep.jepriashowcase.goods.auto;

import static com.technology.jep.jepria.auto.util.WebDriverFactory.getWait;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.JEP_FIELD_INPUT_POSTFIX;
import static com.technology.jep.jepriashowcase.arsenic.client.ArsenicAutomationConstant.ARSENIC_JEP_TREE_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_CATALOG_ID_LIST;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_GOODS_NAME_TEXT_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_MOTIVATION_RADIO_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_PHOTO_IMAGE_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_PORTFOLIO_FILE_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_UNIT_COMBOBOX_FIELD_ID;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.technology.jep.jepria.auto.exception.WrongOptionException;
import com.technology.jep.jepria.auto.module.JepRiaModuleAutoImpl;
import com.technology.jep.jepria.auto.util.WebDriverFactory;
import com.technology.jep.jepria.auto.widget.tree.TreeItemFilter;
import com.technology.jep.jepria.shared.exceptions.NotImplementedYetException;

public class GoodsAutoImpl extends JepRiaModuleAutoImpl<GoodsPage> implements GoodsAuto {
  
  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(GoodsAutoImpl.class.getName());

  public GoodsAutoImpl() {
    super(new GoodsPage());
  }

  @Override
  public void setGoodsName(String goodsNameValue) {
    setFieldValue(GOODS_GOODS_NAME_TEXT_FIELD_ID, goodsNameValue);
  }

  @Override
  public void setPurchasingPrice(String purchasingPrice) {
    setFieldValue(GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID, purchasingPrice);
  }

  @Override
  public String getGoodsName() {
    return getFieldValue(GOODS_GOODS_NAME_TEXT_FIELD_ID);
  }
  
/*  @Override
  public void clickSpecialRadio() {
    WebDriverFactory.getDriver().findElement(By.xpath("//label[contains(text(), 'Процент с дохода')]")).click();
  }*/

  @Override
  public String getPurchasingPrice() {
    return getFieldValue(GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID);
  }

  @Override
  public void setGoodsType(String goodsType) {
    selectComboBoxMenuItem(GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID, goodsType);
  }

  @Override
  public String getGoodsType() {
    return getFieldValue(GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID);
  }

  @Override
  public void setUnit(String unit) {
    selectComboBoxMenuItem(GOODS_UNIT_COMBOBOX_FIELD_ID, unit);
  }

  @Override
  public String getUnit() {
    return getFieldValue(GOODS_UNIT_COMBOBOX_FIELD_ID);
  }


  @Override
  public void setSegment(String goodsSegment) {
    throw new NotImplementedYetException();
  }
  
  @Override
  public void setCatalogSections(String[] goodsCatalogSections) {
    selectTreeItems(GOODS_CATALOG_ID_LIST, goodsCatalogSections);
  }
  
  @Override
  public void setMotivation(String motivation) {
    // TODO: поддержка Selenium-тестирования элемента RadioButton пока не поддерживается,
    // поэтому весь код, который должен располагаться в JepRiaModuleAutoImpl, располагается здесь.
    
    try {
      
      // TODO Обработку radioButton перенести в JepRia
      WebElement radioButtonSpan = WebDriverFactory.getDriver().findElement(By.xpath(//TODO replace WebDriverFactory.getDriver() from everywhere  
          String.format("//*[@id='%s']//span[descendant::label[contains(text(),'%s')]]",
              GOODS_MOTIVATION_RADIO_FIELD_ID + JEP_FIELD_INPUT_POSTFIX,
              motivation))); 
      
      WebElement radioButtonInput = radioButtonSpan.findElement(By.xpath("./input"));
      getWait().until(elementToBeClickable(radioButtonInput));

      radioButtonInput.click();
    } catch(NoSuchElementException ex) {
      throw new WrongOptionException("Wrong radio option", ex);      
    }
  }

  @Override
  public String getMotivation() {
    // TODO: поддержка Selenium-тестирования элемента RadioButton пока не поддерживается,
    // поэтому весь код, который должен располагаться в JepRiaModuleAutoImpl, располагается здесь.
    
    String result;
    
    String checkedRadioButtonXPath = "//table[@id='"
        + GOODS_MOTIVATION_RADIO_FIELD_ID
        + "']//span[@class='gwt-RadioButton']//input[@checked]/following-sibling::label";
    
    try {
      WebElement fieldInput = ((GoodsPage)page).motivationField.findElement(By.xpath(checkedRadioButtonXPath));//TODO infer generic type
      result = fieldInput.getText();
    } catch(NoSuchElementException ex) {
      result = "";
    }
    
    return result;
  }
  
  @Override
  public void setPhoto(String pathToFile) {
    setLargeFieldValue(GOODS_PHOTO_IMAGE_FIELD_ID, pathToFile);
  }
  
  @Override
  public void setPortfolio(String pathToFile) {
    setLargeFieldValue(GOODS_PORTFOLIO_FILE_FIELD_ID, pathToFile);
  }
  
  @Override
  public String getSegment() {
    throw new NotImplementedYetException();
  }

  @Override
  public String[] getCatalogSections() {
    return getTreeFieldNodesByFilter(GOODS_CATALOG_ID_LIST, TreeItemFilter.FILTER_CHECKED_LEAVES);
  }

  @Override
  public void fillSearchForm(
      String goodsName, 
      String goodsType, 
      String unit, 
      String goodsSegment,
      String[] goodsCatalogSections) {
    
    setGoodsName(goodsName);
    setGoodsType(goodsType);
    setUnit(unit);
    setSegment(goodsSegment);
    setCatalogSections(goodsCatalogSections);
  }

  @Override
  public void fillCreateForm(
      String goodsName, 
      String goodsType, 
      String unit, 
      String motivation, 
      String purchasingPrice, 
      String goodsPhoto, 
      String goodsSpecification) {
    
    setGoodsName(goodsName);
    setGoodsType(goodsType);
    setUnit(unit);
    setMotivation(motivation);
    setPurchasingPrice(purchasingPrice);
  }


  @Override
  public void fillEditForm(
      String goodsName, 
      String goodsType, 
      String unit, 
      String motivation, 
      String purchasingPrice, 
      String goodsPhoto, 
      String goodsSpecification) {
    
    setGoodsName(goodsName);
    setGoodsType(goodsType);
    setUnit(unit);
    setMotivation(motivation);
    setPurchasingPrice(purchasingPrice);
  }
}
