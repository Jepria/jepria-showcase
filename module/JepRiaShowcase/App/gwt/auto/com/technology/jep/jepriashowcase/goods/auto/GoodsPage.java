package com.technology.jep.jepriashowcase.goods.auto;

import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_GOODS_NAME_TEXT_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_MOTIVATION_RADIO_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID;
import static com.technology.jep.jepriashowcase.goods.client.GoodsAutomationConstant.GOODS_UNIT_COMBOBOX_FIELD_ID;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.technology.jep.jepria.auto.module.page.JepRiaModulePage;

public class GoodsPage extends JepRiaModulePage {
  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(GoodsPage.class.getName());
  
    @FindBy(id = GOODS_GOODS_NAME_TEXT_FIELD_ID)
    public WebElement goodsNameField;
    
    @FindBy(id = GOODS_PURCHASING_PRICE_NUMBER_FIELD_ID)
    public WebElement purchasingPriceField;

    @FindBy(id = GOODS_GOODS_TYPE_COMBOBOX_FIELD_ID)
    public WebElement goodsTypeField;

    @FindBy(id = GOODS_MOTIVATION_RADIO_FIELD_ID)
    public WebElement motivationField;

    @FindBy(id = GOODS_UNIT_COMBOBOX_FIELD_ID)
    public WebElement unitField;
}
