package com.technology.jep.jepriashowcase.custom.auto;

import static com.technology.jep.jepria.auto.application.property.JepRiaAutoProperties.PASSWORD_KEY;
import static com.technology.jep.jepria.auto.application.property.JepRiaAutoProperties.USERNAME_KEY;
import static com.technology.jep.jepria.auto.application.property.JepRiaAutoProperties.get;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.technology.jep.jepria.auto.util.WebDriverFactory;
import com.technology.jep.jepriashowcase.auto.JepRiaShowcaseAutoImpl;
import com.technology.jep.jepriashowcase.goods.auto.GoodsAuto;
import com.technology.jep.test.util.DataProviderArguments;
import com.technology.jep.test.util.JepFileDataProvider;

public class CustomAutoTest {
  
  private JepRiaShowcaseAutoImpl automationManager;
  private CustomAuto cut;

  /**
   * Конфигурирование теста
   * 
   * @param baseUrl - URL запуска приложения
   * @param browserName - используемый браузер
   * @param browserVersion - версия браузера
   * @param browserPlatform - платформа, для которой реализован браузер
   * @param jepriaVersion - версия JepRia
   * @param forceNewBrowser - условие запуска нового браузера: если true - запускать 
   * @param forceLogin - условие перелогинивания: если true - перелогиниваться
   * @param username - имя пользователя
   * @param password - пароль пользователя
   * @param dbUrl - URL, по которому подключаемся к DB.
   * @param dbUser - Пользователей, под которым подключаемся к DB.
   * @param dbPassword - Пароль, под которым подключаемся к DB.
   */
  @Parameters({
    "baseUrl",
    "browserName",
    "browserVersion",
    "browserPlatform",
    "browserPath",
    "driverPath",
    "jepriaVersion",
    "forceNewBrowser",
    "forceLogin",
    "username",
    "password",
    "dbUrl", 
    "dbUser", 
    "dbPassword"})
  @BeforeMethod(groups = {"standard", "businessProcess"})
  public void setUp(
      String baseUrl,
      String browserName,
      @Optional("fake") String browserVersion,
      @Optional("fake") String browserPlatform,
      String browserPath,
      @Optional String driverPath,
      String jepriaVersion,
      @Optional("No") String forceNewBrowser,
      @Optional("No") String forceLogin,
      String username,
      String password,
      @Optional String dbUrl, 
      @Optional String dbUser, 
      @Optional String dbPassword) {
    
    automationManager = new JepRiaShowcaseAutoImpl(baseUrl, browserName, browserVersion, browserPlatform, browserPath,
        driverPath, jepriaVersion, username, password, dbUrl, dbUser, dbPassword);
    
    cut = automationManager.getCustomAuto(); // TODO Вернуть оптимизацию
  }
  
  @AfterMethod(groups = {"standard", "businessProcess"})
  public void tearDown() {
    WebDriverFactory.destroyInstance();
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/custom/auto/loginSuccess.data")
  @Test(dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void loginSuccessfulTest(String login, String password) {
    AssertJUnit.assertFalse(cut.isLoggedIn());
    cut.login(login, password);
    AssertJUnit.assertTrue(cut.isLoggedIn());
  }
  
  @DataProviderArguments("filePath=test/resources/com/technology/jep/jepriashowcase/custom/auto/loginFail.data")
  @Test(dataProviderClass = JepFileDataProvider.class, dataProvider="dataFromFile")
  public void loginFailedTest(String login, String password) {
    AssertJUnit.assertFalse(cut.isLoggedIn());
    cut.login(login, password);
    AssertJUnit.assertTrue(cut.isAuthorizedFailed());
  }
  
//  @Test(dependsOnMethods = {"login"})
  @Test
  public void isLoggedIn() {
    cut.login(get(USERNAME_KEY), get(PASSWORD_KEY)); // TODO Оптимизировать
    AssertJUnit.assertTrue(cut.isLoggedIn());
    cut.logout();
    AssertJUnit.assertFalse(cut.isLoggedIn());
  }

  @Test
  public void logout() {
    cut.login(get(USERNAME_KEY), get(PASSWORD_KEY)); // TODO Оптимизировать
    AssertJUnit.assertTrue(cut.isLoggedIn());
    cut.logout();
    AssertJUnit.assertFalse(cut.isLoggedIn());
  }
  
//  @Test(dependsOnMethods = {"login"})
  @Test
  public void openFullScreenModules() {
    cut.login(get(USERNAME_KEY), get(PASSWORD_KEY)); // TODO Оптимизировать
    GoodsAuto fullScreenModules = cut.openFullScreenModules();
    AssertJUnit.assertNotNull(fullScreenModules);
  }
}
