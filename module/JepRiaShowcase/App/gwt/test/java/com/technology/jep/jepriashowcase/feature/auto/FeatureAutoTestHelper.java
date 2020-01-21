package com.technology.jep.jepriashowcase.feature.auto;

import static com.technology.jep.jepria.client.JepRiaAutomationConstant.ERROR_MESSAGE_BOX_OK_BUTTON_ID;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.CREATE;
import static com.technology.jep.jepria.client.ui.WorkstateEnum.EDIT;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID;

import org.apache.log4j.Logger;
import org.testng.AssertJUnit;

import com.technology.jep.jepria.auto.module.JepRiaModuleAuto;
import com.technology.jep.jepria.auto.module.JepRiaModuleAuto.SaveResultEnum;

public class FeatureAutoTestHelper {
  
  private static Logger logger = Logger.getLogger(FeatureAutoTestHelper.class.getName());

  public final static String FEATURE_STATUS_NAME_REMARK_WORKS = "Работа над ремарками";
  
  /**
   * Проверяет доступность поля "Ответственный" на редактирование. <br/>
   * Переходит в режим редактирования, делает проверку.
   * @return true, если можно редактировать, иначе false
   */
  protected static boolean isResponsibleEditable(JepRiaModuleAuto cut) {
    
    // переходим в состояние редактирования
    cut.setWorkstate(EDIT);
    
    // Проверяем, что осуществился переход на форму редактирования
    AssertJUnit.assertEquals(
        EDIT,
        cut.getWorkstateFromStatusBar());
    
    return cut.isFieldEditable(FEATURE_RESPONSIBLE_ID_DETAILFORM_FIELD_ID);
  }
  
  /**
   * Создание тестовой записи
   * 
   * @param featureName - значение ключевого поля featureName, идентифицирующего тестовую запись
   * @param cut - Экземпляр интерфейса автотеста 
   */
  protected static void createTestRecord(String featureName, FeatureAuto cut) {
    logger.trace("createRecordForTest()");
    cut.setWorkstate(CREATE);
    
    // arbitrary values. //TODO get rid of hardcode, fill by dataProvider
    int random = (int)Math.random();
    cut.fillCreateForm(featureName, "featureNameEn_"+random, null);
    
    SaveResultEnum saveResult = cut.save();
    if(saveResult != SaveResultEnum.SUCCESS) {
      if(saveResult == SaveResultEnum.ERROR_MESSAGE_BOX) { // TODO Предполагаем, что запись уже существует, но нужно всё-таки убедиться
        logger.warn("createRecordForTest(): аналогичная (по какому признаку?) запись уже существует");
        cut.clickButton(ERROR_MESSAGE_BOX_OK_BUTTON_ID);
      } else {
        AssertJUnit.fail("Ошибка создания тестовой записи");
      }
    }
  }
}
