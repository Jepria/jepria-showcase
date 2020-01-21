package com.technology.jep.jepriashowcase.featureoperator.client.ui.form.list;
 
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_DATE_FORMAT;
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_TIME_FORMAT;
import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorClientConstant.featureOperatorText;
import static com.technology.jep.jepriashowcase.featureoperator.client.FeatureOperatorAutomationConstant.FEATURE_OPERATOR_GRID_ID;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.FEATURE_OPERATOR_NAME;
import static com.technology.jep.jepriashowcase.featureoperator.shared.field.FeatureOperatorFieldNames.OPERATOR_NAME;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.technology.jep.jepria.client.ui.form.list.StandardListFormViewImpl;
import com.technology.jep.jepria.client.widget.list.JepColumn;

public class FeatureOperatorListFormViewImpl extends StandardListFormViewImpl { 
  
   public FeatureOperatorListFormViewImpl() {
    super(FeatureOperatorListFormViewImpl.class.getCanonicalName(), FEATURE_OPERATOR_GRID_ID);
  }
  
  private static DateTimeFormat defaultDateFormatter = DateTimeFormat.getFormat(DEFAULT_DATE_FORMAT);
  private static DateTimeFormat defaultDateTimeFormatter = DateTimeFormat.getFormat(DEFAULT_DATE_FORMAT + " " + DEFAULT_TIME_FORMAT);

  @SuppressWarnings({ "rawtypes", "unchecked", "serial" })
  @Override
    protected List<JepColumn> getColumnConfigurations() {
      return new ArrayList<JepColumn>() {{
      add(new JepColumn(FEATURE_OPERATOR_NAME, featureOperatorText.featureOperator_list_feature_operator_name(), 150));
      add(new JepColumn(DATE_INS, featureOperatorText.featureOperator_list_date_ins(), 150, new DateCell(defaultDateTimeFormatter)));
      add(new JepColumn(OPERATOR_NAME, featureOperatorText.featureOperator_list_operator_name(), 150));
    }};    
  }
}
