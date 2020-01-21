package com.technology.jep.jepriashowcase.custom.auto;

/*
public class CustomAutoImpl<A extends JepRiaShowcaseAuto> extends ApplicationEntranceAuto<A, JepRiaShowcasePageManager> implements CustomAuto {
  
  public CustomAutoImpl(A jepRiaShowcaseAuto, JepRiaShowcasePageManager pageManager) {
    super(jepRiaShowcaseAuto, pageManager);
  }

  @Override
  public GoodsAuto openFullScreenModules() {
    pages.customPage.ensurePageLoaded();
    assert(isLoggedIn());
    pages.customPage.fullScreenModulesButton.click();
    return this.applicationManager.getGoodsAuto(true);
  }

  @Override
  public boolean isReady() {
    pages.customPage.ensurePageLoaded();

    return true;
  }

  @Override
  public boolean isLoggedIn() {
    return pages.customPage.ensurePageLoaded()
          .isElementPresent(By.id(CustomAutomationConstant.LOGGED_IN_USER_ID)) &&
              pages.customPage.getWebDriver().findElement(By.id(CustomAutomationConstant.LOGGED_IN_USER_ID)).isDisplayed();
  }
  
  @Override
  public void login(String login, String password) {
    pages.customPage
        .ensurePageLoaded()
        .setUsername(login)
        .setPassword(password)
        .doLogin();
    pages.customPage.ensureAuthorized();
  }
  
  @Override
  public void logout() {
    pages.customPage.doLogout();
    pages.customPage.ensureLogout();
  }
  
  @Override
  public boolean isAuthorizedFailed(){
    return pages.customPage.isElementPresent(By.id(CustomAutomationConstant.ERROR_LABEL_ID))
        && pages.customPage.isElementPresent(By.id(CustomAutomationConstant.LOGIN_BUTTON_ID))
          && !pages.customPage.loginButton.isEnabled();
  }
}
*/
