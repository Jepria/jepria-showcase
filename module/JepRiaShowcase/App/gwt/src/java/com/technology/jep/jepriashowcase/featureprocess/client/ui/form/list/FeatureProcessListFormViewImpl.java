package com.technology.jep.jepriashowcase.featureprocess.client.ui.form.list;
 
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_DATE_FORMAT;
import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessAutomationConstant.*;
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_TIME_FORMAT;
import static com.technology.jep.jepriashowcase.featureprocess.client.FeatureProcessClientConstant.featureProcessText;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.FEATURE_STATUS_NAME;
import static com.technology.jep.jepriashowcase.featureprocess.shared.field.FeatureProcessFieldNames.OPERATOR_NAME;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.technology.jep.jepria.client.ui.form.list.StandardListFormViewImpl;
import com.technology.jep.jepria.client.widget.list.JepColumn;

public class FeatureProcessListFormViewImpl extends StandardListFormViewImpl { 
  
   public FeatureProcessListFormViewImpl() {
    super(FeatureProcessListFormViewImpl.class.getCanonicalName(), FEATUREPROCESS_GRID_ID);
  }

  private static DateTimeFormat defaultDateTimeFormatter = DateTimeFormat.getFormat(DEFAULT_DATE_FORMAT + " " + DEFAULT_TIME_FORMAT);

  @SuppressWarnings({ "rawtypes", "unchecked", "serial" })
  @Override
    protected List<JepColumn> getColumnConfigurations() {
      return new ArrayList<JepColumn>() {{
      add(new JepColumn(FEATURE_STATUS_NAME, featureProcessText.featureProcess_list_feature_status_name(), 150));
      add(new JepColumn(DATE_INS, featureProcessText.featureProcess_list_date_ins(), 150, new DateCell(defaultDateTimeFormatter)));
      add(new JepColumn(OPERATOR_NAME, featureProcessText.featureProcess_list_operator_name(), 150));
    }};    
  }
}
