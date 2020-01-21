package com.technology.jep.jepriashowcase.arsenic.auto;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.exception.WrongOptionException;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoTest;
import com.technology.jep.test.util.DataProviderArguments;
import com.technology.jep.test.util.JepFileDataProvider;

public class ArsenicAutoTest extends JepRiaShowcaseAutoTest {
  
  @Override
  protected void beforeTestLaunch() {
    
    super.beforeTestLaunch();
    enterModule(arsenic);
    loginDefault();
  };
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepTextField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepTextField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepTextField(value);
    assertEquals(value, arsenicCut.getJepTextField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepTextAreaField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepTextAreaField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepTextAreaField(value);
    assertEquals(value, arsenicCut.getJepTextAreaField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepIntegerField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepIntegerField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepIntegerField_maxRowCount(value);
    assertEquals(value, arsenicCut.getJepIntegerField_maxRowCount());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepLongField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepLongField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepLongField(value);
    assertEquals(value, arsenicCut.getJepLongField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepMoneyField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepMoneyField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepMoneyField(value);
    assertEquals(value, arsenicCut.getJepMoneyField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepNumberField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepNumberField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepNumberField(value);
    assertEquals(value, arsenicCut.getJepNumberField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepDateField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepDateField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepDateField(value);
    assertEquals(value, arsenicCut.getJepDateField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepComboBoxField_1.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepComboBoxFieldNotLazy(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepComboBoxFieldNotLazy(value);
    assertEquals(value, arsenicCut.getJepComboBoxFieldNotLazy());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepComboBoxField_1.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepComboBoxFieldSimple(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepComboBoxFieldSimple(value);
    assertEquals(value, arsenicCut.getJepComboBoxFieldSimple());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepComboBoxField_1.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepComboBoxFieldDurable(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepComboBoxFieldDurable(value);
    assertEquals(value, arsenicCut.getJepComboBoxFieldDurable());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepComboBoxField_2.data")
  @Test(groups="setAndGetFields", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepComboBoxFieldReloading(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepComboBoxFieldReloading(value);
    assertEquals(value, arsenicCut.getJepComboBoxFieldReloading());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepComboBoxField_2.data")
  @Test(groups="setAndGetFields", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepComboBoxField3chReloading(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepComboBoxField3chReloading(value);
    assertEquals(value, arsenicCut.getJepComboBoxField3chReloading());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepListField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepDualListField(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepDualListField(values);

    // Поскольку getJepDualListField возвращает опции в порядке, определяемом самим полем JepDualListField,
    // то следует производить сравнение без учета порядка.
    String[] actualValues = arsenicCut.getJepDualListField();
    assertEquals(values.length, actualValues.length);
    Arrays.sort(values);
    Arrays.sort(actualValues);
    assertArrayEquals(values, actualValues);
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepCheckBoxField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepCheckBoxField(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    final boolean boolValue;
    
    if ("true".equals(value) || "1".equals(value)) {
      boolValue = true;
    } else if ("false".equals(value) || "0".equals(value)) {
      boolValue = false;
    } else {
      throw new IllegalArgumentException("'" + value + "' is not a valid argument. Pass 'true'/'false' or '1'/'0' only.");
    }
    
    arsenicCut.setJepCheckBoxField(boolValue);
    assertEquals(boolValue, arsenicCut.getJepCheckBoxField());
    
    arsenicCut.changeJepCheckBoxField();
    assertEquals(!boolValue, arsenicCut.getJepCheckBoxField());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepListField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepListField(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepListField(values);
    
    // Поскольку getJepListField возвращает опции в порядке, определяемом самим полем JepListField,
    // то следует производить сравнение без учета порядка.
    String[] actualValues = arsenicCut.getJepListField();
    assertEquals(values.length, actualValues.length);
    Arrays.sort(values);
    Arrays.sort(actualValues);
    assertArrayEquals(values, actualValues);
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepListField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepListFieldCheckAll(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepListFieldCheckAll(values);
    
    // Поскольку getJepListField возвращает опции в порядке, определяемом самим полем JepListField,
    // то следует производить сравнение без учета порядка.
    String[] actualValues = arsenicCut.getJepListFieldCheckAll();
    assertEquals(values.length, actualValues.length);
    Arrays.sort(values);
    Arrays.sort(actualValues);
    assertArrayEquals(values, actualValues);
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepTreeField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepTreeField(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepTreeField(values);
    
    // Поскольку getJepTreeField_nodes возвращает опции в порядке, определяемом самим полем JepTreeField,
    // то следует производить сравнение без учета порядка.
    String[] actualValues = arsenicCut.getJepTreeField_checked();
    assertEquals(values.length, actualValues.length);
    Arrays.sort(values);
    Arrays.sort(actualValues);
    assertArrayEquals(values, actualValues);
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepTreeField_nodes.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepTreeField_nodes(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepTreeField_nodes(values);
    
    // Поскольку getJepTreeField_nodes возвращает опции в порядке, определяемом самим полем JepTreeField,
    // то следует производить сравнение без учета порядка.
    String[] actualValues = arsenicCut.getJepTreeField_nodes_checked();
    assertEquals(values.length, actualValues.length);
    Arrays.sort(values);
    Arrays.sort(actualValues);
    assertArrayEquals(values, actualValues);
    
    
    // Тестируем флажок "Выделить все"
    // Важно тестировать полное выделение именно здесь, а не в отдельном методе,
    // чтобы до этого момента иметь какую-либо развернутость дерева.
    if (arsenicCut.selectAllJepTreeField_nodes(true)) {
      actualValues = arsenicCut.getJepTreeField_nodes_checked();
      String[] visibleOptions = arsenicCut.getJepTreeField_nodes_visible();
      assertEquals(visibleOptions.length, actualValues.length);
      Arrays.sort(visibleOptions);
      Arrays.sort(actualValues);
      assertArrayEquals(visibleOptions, actualValues);
    }
    if (arsenicCut.selectAllJepTreeField_nodes(false)) {
      actualValues = arsenicCut.getJepTreeField_nodes_checked();
      assertEquals(0, actualValues.length);
    }
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepTreeField.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepTreeField_casc(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.setJepTreeField_casc(values);
    
    // Поскольку getJepTreeField_casc возвращает опции в порядке, определяемом самим полем JepTreeField,
    // то следует производить сравнение без учета порядка.
    String[] actualValues = arsenicCut.getJepTreeField_casc_checked();
    assertEquals(values.length, actualValues.length);
    Arrays.sort(values);
    Arrays.sort(actualValues);
    assertArrayEquals(values, actualValues);
  }
  
  
  
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/fields.incorrect.data")
  @Test(groups="setAndGetFields", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetFieldsIncorrect(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.setJepIntegerField_maxRowCount(value);
    Assert.assertNotEquals(value, arsenicCut.getJepIntegerField_maxRowCount());
    
    arsenicCut.setJepLongField(value);
    Assert.assertNotEquals(value, arsenicCut.getJepLongField());
    
    arsenicCut.setJepMoneyField(value);
    Assert.assertNotEquals(value, arsenicCut.getJepMoneyField());
    
    arsenicCut.setJepNumberField(value);
    Assert.assertNotEquals(value, arsenicCut.getJepNumberField());
    
    arsenicCut.setJepDateField(value);
    Assert.assertNotEquals(value, arsenicCut.getJepDateField());
    
    try {
      arsenicCut.setJepComboBoxFieldNotLazy(value);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
    
    try {
      arsenicCut.setJepComboBoxFieldSimple(value);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
    
    try {
      arsenicCut.setJepComboBoxFieldDurable(value);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
    
    try {
      arsenicCut.setJepComboBoxFieldReloading(value);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
    
    try {
      arsenicCut.setJepComboBoxField3chReloading(value);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/fields.incorrect_multiple.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetFieldsIncorrectMultiple(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    try {
      arsenicCut.setJepDualListField(values);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
    
    try {
      arsenicCut.setJepListField(values);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepComboBoxField_1char.data")
  @Test(groups="setAndGetFields", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepComboBoxField3chReloadingIncorrect(String value) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    try {
      arsenicCut.setJepComboBoxField3chReloading(value);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    }
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/field.jepTreeField.incorrect.data")
  @Test(groups="setAndGetFields!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void setAndGetJepTreeField_nodesIncorrect(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    try {
      arsenicCut.setJepTreeField_nodes(values);
      fail();
    } catch (WrongOptionException woe) {
      // OK
    } catch (IllegalArgumentException  iae) {
      // OK
    }
  }
  
  
  // С осторожностью: оставить visiblity=true в конце!
  @Test(groups="fieldStates")
  public void testSwitchVisiblity() {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.setCheckBox_switchVsbl(false);
    assertEquals(true, arsenicCut.checkAllFieldsVisibility(false));
    
    arsenicCut.setCheckBox_switchVsbl(true);
    assertEquals(true, arsenicCut.checkAllFieldsVisibility(true));
  }
  
  // С осторожностью: оставить enability=true в конце!
  @Test(groups="fieldStates")
  public void testSwitchEnability() {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.setCheckBox_switchEnbl(false);
    assertEquals(true, arsenicCut.checkAllFieldsEnability(false));
    
    arsenicCut.setCheckBox_switchEnbl(true);
    assertEquals(true, arsenicCut.checkAllFieldsEnability(true));
  }
  
  // С осторожностью: оставить editability=true в конце!
  @Test(groups="fieldStates")
  public void testSwitchEditability() {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.setCheckBox_switchEdtb(false);
    assertEquals(true, arsenicCut.checkAllFieldsEditability(false));
    
    arsenicCut.setCheckBox_switchEdtb(true);
    assertEquals(true, arsenicCut.checkAllFieldsEditability(true));
  }
  
  // С осторожностью: оставить allowBlank=true в конце!
  @Test(groups="fieldStates")
  public void testSwitchAllowBlank() {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.setCheckBox_switchAlbl(false);
    assertEquals(true, arsenicCut.checkAllFieldsAllowBlank(false));
    
    arsenicCut.setCheckBox_switchAlbl(true);
    assertEquals(true, arsenicCut.checkAllFieldsAllowBlank(true));
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/list.gridHeaders.data")
  @Test(groups="list!", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void testGridHeaders(Object... headers) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.doSearch();
    
    List<String> headersList = arsenicCut.getGridHeaders();
    
    assertArrayEquals(headers, headersList.toArray(new String[headersList.size()]));
  }
  
  @Test(groups="list!")
  public void testGridData() {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    
    arsenicCut.setJepTextField("abc");
    arsenicCut.setJepTextAreaField("DEF");
    
    arsenicCut.doSearch();
    
    List<List<Object>> data = arsenicCut.getGridData();
    List<String> headers = arsenicCut.getGridHeaders();
    
    for (List<Object> row: data) {
      Object c = row.get(headers.indexOf("Text"));
      assert c instanceof String;
      assertTrue(((String)c).startsWith("abc"));
      
      Object d = row.get(headers.indexOf("TextArea"));
      assert d instanceof String;
      assertTrue(((String)d).startsWith("DEF"));
    }
  }
  
  /**
   * Переменная используется для недопущения повторного перехода на списочную форму
   * после первого запуска тестового метода testColumnOrderWithCookies.
   * Переменная не влияет на другие тестовые методы.
   */
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/arsenic/auto/list.gridHeaders.settings.data")
  @Test(groups="list", dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void testColumnOrderWithCookies(String...values) {
    ArsenicAuto arsenicCut = this.<ArsenicAuto>getCut();
    arsenicCut.find();
    arsenicCut.doSearch();
    
    arsenicCut.doGridColumnSettings(values);
    List<String> headers = arsenicCut.getGridHeaders();
    assertArrayEquals(values, headers.toArray(new String[headers.size()]));
    
    arsenicCut.refreshPage();
    
    assertArrayEquals(values, headers.toArray(new String[headers.size()]));
  }
}
