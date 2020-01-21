package com.technology.jep.jepriashowcase.feature.client.ui.form.list;
 
import static com.technology.jep.jepria.shared.JepRiaConstant.DEFAULT_DATE_FORMAT;
import static com.technology.jep.jepriashowcase.feature.client.FeatureAutomationConstant.FEATURE_GRID_ID;
import static com.technology.jep.jepriashowcase.feature.client.FeatureClientConstant.featureText;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.AUTHOR_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DATE_INS;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.DESCRIPTION;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_ID;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_NAME_EN;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.FEATURE_STATUS_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.RESPONSIBLE_NAME;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.technology.jep.jepria.client.security.ClientSecurity;
import com.technology.jep.jepria.client.ui.form.list.ListFormViewImpl;
import com.technology.jep.jepria.client.widget.list.JepColumn;
import com.technology.jep.jepria.client.widget.list.JepGrid;
import com.technology.jep.jepria.client.widget.list.JepGrid.DndMode;
import com.technology.jep.jepria.client.widget.toolbar.PagingStandardBar;
import com.technology.jep.jepria.shared.record.JepRecord;
 
@SuppressWarnings("rawtypes")
public class FeatureListFormViewImpl extends ListFormViewImpl<FeatureGridManager> {
  
  /**
   * Grid для возможности управления столбцамии и строками
   * (например, раскраска строк или добавление/удаление столбцов).
   */
  protected JepGrid<JepRecord> grid;
  
  @SuppressWarnings("unchecked")
  public FeatureListFormViewImpl() {
    super(new FeatureGridManager());
    
    HeaderPanel gridPanel = new HeaderPanel();
    setWidget(gridPanel);
    
    gridPanel.setHeight("100%");
    gridPanel.setWidth("100%");
    
    List<JepColumn> columnConfigurations = getColumnConfigurations();
    if (columnConfigurations == null) { // эта проверка на всякий случай - ничего страшного, если тут будет null
        columnConfigurations = new ArrayList<JepColumn>();
    }
    
    grid = new JepGrid<JepRecord>(FeatureListFormViewImpl.class.getCanonicalName(), FEATURE_GRID_ID, columnConfigurations);
    PagingStandardBar pagingBar = new PagingStandardBar(25);
    
    gridPanel.setContentWidget(grid);
    gridPanel.setFooterWidget(pagingBar);

    list.setWidget(grid);
    list.setPagingToolBar(pagingBar);
    
    if (ClientSecurity.instance.isUserHaveRole("JrsAssignWorkSequenceFeature")) {
      list.setDndEnabled(true);
    }
  }

  private static NumberFormat defaultNumberFormatter = NumberFormat.getFormat("#");
  private static DateTimeFormat defaultDateFormatter = DateTimeFormat.getFormat(DEFAULT_DATE_FORMAT);
 
  @SuppressWarnings({ "unchecked", "serial" })
  protected List<JepColumn> getColumnConfigurations() {
    return new ArrayList<JepColumn>() {{
      add(new JepColumn(FEATURE_ID, featureText.feature_list_feature_id(), 100, new NumberCell(defaultNumberFormatter)));
      add(new JepColumn(WORK_SEQUENCE, featureText.feature_list_work_sequence(), 150, new NumberCell()));
      add(new JepColumn(FEATURE_STATUS_NAME, featureText.feature_list_feature_status_name(), 150));
      add(new JepColumn(FEATURE_NAME, featureText.feature_list_feature_name(), 150));
      add(new JepColumn(FEATURE_NAME_EN, featureText.feature_list_feature_name_en(), 150));
      add(new JepColumn(DESCRIPTION, featureText.feature_list_description(), 150));
      add(new JepColumn(DATE_INS, featureText.feature_list_date_ins(), 150, new DateCell(defaultDateFormatter)));
      add(new JepColumn(AUTHOR_NAME, featureText.feature_list_author(), 150));
      add(new JepColumn(RESPONSIBLE_NAME, featureText.feature_list_responsible_name(), 150));
    }};
  }
  
}
