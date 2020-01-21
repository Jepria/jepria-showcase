package com.technology.jep.jepriashowcase.feature.auto;

import static com.technology.jep.jepria.auto.util.WebDriverFactory.getWait;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.TOOLBAR_DELETE_BUTTON_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.ERROR_MESSAGEBOX_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.ERROR_MESSAGE_BOX_OK_BUTTON_ID;
import static com.technology.jep.jepria.client.JepRiaAutomationConstant.GRID_HEADER_POSTFIX;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.SELECTED;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.VIEW_LIST;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_AUTHOR_ID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_DESCRIPTION_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATUREID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURENAMEEN_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURENAME_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURE_STATUS_CODE_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURE_STATUS_CODE_LIST_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FEATURE_STATUS_NAME_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_FROMDATEINS_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_MAXROWCOUNT_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_TODATEINS_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCEFROM_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCETO_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureClientConstant.FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.technology.jep.jepria.auto.condition.ExpectedConditions;
import com.technology.jep.jepria.auto.module.JepRiaModuleAutoImpl;
import com.technology.jep.jepria.auto.module.page.JepRiaModulePage;
import com.technology.jep.jepria.auto.util.DragAndDropTestUtil;
import com.technology.jep.jepria.auto.util.WebDriverFactory;
import com.technology.jep.jepria.client.ui.WorkstateEnum;

public class FeatureAutoImpl extends JepRiaModuleAutoImpl<JepRiaModulePage>
    implements FeatureAuto {

  public FeatureAutoImpl() {
    super(new JepRiaModulePage());
  }

  @Override
  public void setFeatureId(String featureId) {
    setFieldValue(FEATURE_FEATUREID_DETAILFORM_FIELD_ID, featureId);
  }

  @Override
  public void setFeatureName(String featureName) {
    setFieldValue(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID, featureName);
  }

  @Override
  public void setFeatureNameEn(String featureNameEn) {
    setFieldValue(FEATURE_FEATURENAMEEN_DETAILFORM_FIELD_ID, featureNameEn);
  }

  @Override
  public void setWorkSequenceFrom(String workSequenceFrom) {
    setFieldValue(FEATURE_WORKSEQUENCEFROM_DETAILFORM_FIELD_ID, workSequenceFrom);
  }

  @Override
  public void setWorkSequenceTo(String workSequenceTo) {
    setFieldValue(FEATURE_WORKSEQUENCETO_DETAILFORM_FIELD_ID, workSequenceTo);
  }

  @Override
  public void setWorkSequence(String workSequence) {
    setFieldValue(FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID, workSequence);
  }
  
  @Override
  public void setFromDateIns(String fromDateIns) {
    setFieldValue(FEATURE_FROMDATEINS_DETAILFORM_FIELD_ID, fromDateIns);
  }
  
  @Override
  public void setToDateIns(String toDateIns) {
    setFieldValue(FEATURE_TODATEINS_DETAILFORM_FIELD_ID, toDateIns);
  }
  
  @Override
  public void setDescription(String description) {
    setFieldValue(FEATURE_DESCRIPTION_DETAILFORM_FIELD_ID, description);
  }

  @Override
  public void setMaxRowCount(String maxRowCount) {
    setFieldValue(FEATURE_MAXROWCOUNT_DETAILFORM_FIELD_ID, maxRowCount);
  }

  @Override
  public void setAuthorId(String authorId) {
    selectComboBoxMenuItemWithCharByCharReloadingOptions(
        FEATURE_AUTHOR_ID_DETAILFORM_FIELD_ID, 
        authorId,
        FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR);
  }
  
  @Override
  public void setResponsibleId(String responsibleName) {
    selectComboBoxMenuItemWithCharByCharReloadingOptions(
        FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID, 
        responsibleName,
        FEATURE_RESPONSIBLE_ID_COMBOBOX_MIN_CHAR);
  }
  
  @Override
  public void setFeatureStatusCode(String statusCode) {
    this.selectComboBoxMenuItem(FEATURE_FEATURE_STATUS_CODE_DETAILFORM_FIELD_ID, statusCode);
  }
  
  @Override
  public void setFeatureStatusCodeList(String[] menuItems) {
    this.selectListMenuItems(FEATURE_FEATURE_STATUS_CODE_LIST_DETAILFORM_FIELD_ID, menuItems);
  }

  @Override
  public String getFeatureId() {
    return getFieldValue(FEATURE_FEATUREID_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getFeatureName() {
    return getFieldValue(FEATURE_FEATURENAME_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getFeatureNameEn() {
    return getFieldValue(FEATURE_FEATURENAMEEN_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getWorkSequenceFrom() {
    return getFieldValue(FEATURE_WORKSEQUENCEFROM_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getWorkSequenceTo() {
    return getFieldValue(FEATURE_WORKSEQUENCETO_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getWorkSequence() {
    return getFieldValue(FEATURE_WORKSEQUENCE_DETAILFORM_FIELD_ID);
  }
  
  @Override
  public String getFromDateIns() {
    return getFieldValue(FEATURE_FROMDATEINS_DETAILFORM_FIELD_ID);
  }
  
  @Override
  public String getToDateIns() {
    return getFieldValue(FEATURE_TODATEINS_DETAILFORM_FIELD_ID);
  }
  
  @Override
  public String getDescription() {
    return getFieldValue(FEATURE_DESCRIPTION_DETAILFORM_FIELD_ID);
  }
  
  @Override
  public String getMaxRowCount() {
    return getFieldValue(FEATURE_MAXROWCOUNT_DETAILFORM_FIELD_ID);
  }
  
  @Override
  public String getAuthorId() {
    return getFieldValue(FEATURE_AUTHOR_ID_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getResponsibleName() {
    return getFieldValue(FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID);
  }

  @Override
  public String getFeatureStatusName() {
    return getFieldValue(FEATURE_FEATURE_STATUS_NAME_FIELD_ID);
  }

  @Override
  public WorkstateEnum confirmErrorMessageBox() {
    assert checkMessageBox(ERROR_MESSAGEBOX_ID);
    clickButton(ERROR_MESSAGE_BOX_OK_BUTTON_ID);
    return waitForStatusWorkstate(VIEW_LIST);
  }
  
  @Override
  public void fillSearchForm(
      String featureId,
      String featureName,
      String featureNameEn,
      String workSequenceFrom,
      String workSequenceTo,
      String fromDateIns,
      String toDateIns,
      String maxRowCount) {
    setFeatureId(featureId);
    setFeatureName(featureName);
    setFeatureNameEn(featureNameEn);
    setWorkSequenceFrom(workSequenceFrom);
    setWorkSequenceTo(workSequenceTo);
    setFromDateIns(fromDateIns);
    setToDateIns(toDateIns);
    setMaxRowCount(maxRowCount);
  }

  @Override
  public void fillCreateForm(
      String featureName,
      String featureNameEn,
      String description) {
    setFeatureName(featureName);
    setFeatureNameEn(featureNameEn);
    setDescription(description);
  }

  @Override
  public void fillEditForm(
      String featureName,
      String featureNameEn,
      String workSequence) {
    setFeatureName(featureName);
    setFeatureNameEn(featureNameEn);
    setWorkSequence(workSequence);
  }
  
  @Override
  public void sortByColumn(String gridId, Integer columnId){
    
    assert getWorkstateFromStatusBar() == VIEW_LIST || getWorkstateFromStatusBar() == SELECTED;
    
    //WebElement grid = getGridBody(gridId);
    
    List<WebElement> columnList = findElementsAndWait(By.xpath(
        String.format("//thead[@id='%s']/tr/th",
            gridId + GRID_HEADER_POSTFIX)));
    new Actions(WebDriverFactory.getDriver()).click(columnList.get(columnId)).build().perform();
    new Actions(WebDriverFactory.getDriver()).moveByOffset(0, 0).build().perform();
    waitForListMask();
  }
  
  @Override
  public void dragAndDropGridRowBeforeTarget(int draggableRowIndex, int targetRowIndex, String gridId){
    assert getWorkstateFromStatusBar() == VIEW_LIST || getWorkstateFromStatusBar() == SELECTED;
    
    WebDriver driver = WebDriverFactory.getDriver();
    selectItem(draggableRowIndex, gridId);
    WebElement draggableRow = getGridRowElement(draggableRowIndex, gridId);
    WebElement targetRow = getGridRowElement(targetRowIndex, gridId);
    Dimension sourceElementSize = draggableRow.getSize();
    Point sourceLocation = draggableRow.getLocation();
    int sourceX = sourceLocation.getX() + sourceElementSize.getWidth() / 2;
    int sourceY = sourceLocation.getY() + sourceElementSize.getHeight() / 2;
    Dimension targetElementSize = targetRow.getSize();
    Point targetLocation = targetRow.getLocation();
    int targetX = targetLocation.getX() + targetElementSize.getWidth() / 2;
    int targetY = targetLocation.getY() + targetElementSize.getHeight() * 15 / 100;
    DragAndDropTestUtil.html5_DragAndDrop(driver, draggableRow, targetRow, sourceX, sourceY, targetX, targetY);
    waitForStatusWorkstate(VIEW_LIST, SELECTED);
  }
  
  @Override
  public void dragAndDropGridRowAfterTarget(int draggableRowIndex, int targetRowIndex, String gridId){
    assert getWorkstateFromStatusBar() == VIEW_LIST || getWorkstateFromStatusBar() == SELECTED;
    
    WebDriver driver = WebDriverFactory.getDriver();
    selectItem(draggableRowIndex, gridId);
    WebElement draggableRow = getGridRowElement(draggableRowIndex, gridId);
    WebElement targetRow = getGridRowElement(targetRowIndex, gridId);
    Dimension sourceElementSize = draggableRow.getSize();
    Point sourceLocation = draggableRow.getLocation();
    int sourceX = sourceLocation.getX() + sourceElementSize.getWidth() / 2;
    int sourceY = sourceLocation.getY() + sourceElementSize.getHeight() / 2;
    Dimension targetElementSize = targetRow.getSize();
    Point targetLocation = targetRow.getLocation();
    int targetX = targetLocation.getX() + targetElementSize.getWidth() / 2;
    int targetY = targetLocation.getY() + targetElementSize.getHeight() * 85 / 100;
    DragAndDropTestUtil.html5_DragAndDrop(driver, draggableRow, targetRow, sourceX, sourceY, targetX, targetY);
    waitForStatusWorkstate(VIEW_LIST, SELECTED);
  }
  
  @Override
  public boolean isDeleteButtonClickable() {
    try {
      WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), 1);
      wait.until(elementToBeClickable(By.id(TOOLBAR_DELETE_BUTTON_ID)));
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }
}
