package com.technology.jep.jepriashowcase.feature.client.ui.form.list;

import static com.technology.jep.jepria.client.JepRiaClientConstant.JepTexts;
import static com.technology.jep.jepriashowcase.feature.shared.field.FeatureFieldNames.WORK_SEQUENCE;
import static com.technology.jep.jepriashowcase.feature.client.FeatureClientConstant.featureText;

import java.util.List;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.view.client.SetSelectionModel;
import com.technology.jep.jepria.client.message.ErrorDialog;
import com.technology.jep.jepria.client.widget.event.JepListener;
import com.technology.jep.jepria.client.widget.list.GridManager;
import com.technology.jep.jepria.client.widget.toolbar.PagingToolBar;
import com.technology.jep.jepria.shared.record.JepRecord;
import com.technology.jep.jepria.shared.util.JepRiaUtil;
import com.technology.jep.jepriashowcase.feature.client.ui.form.list.event.DropEvent;

/**
 * GridManager с добавленным Listener'ом dropEventListener для дальнейшей связи с DB.
 * (handler инициализируется в FeatureListFormPresenter)
 * 
 * @author SavenkovAV
 *
 * @param <W>
 * @param <P>
 * @param <S>
 */
public class FeatureGridManager<W extends AbstractCellTable<JepRecord>, 
  P extends PagingToolBar, 
  S extends SetSelectionModel<JepRecord>> extends GridManager<W,P,S>{

  private JepListener dropEventListener;

  public void addDropListener(JepListener listener) {
    dropEventListener = listener;
  }
  
  @Override
  public void changeRowPosition(List<Object> oldRowList, int newIndex, boolean isOver, boolean insertBefore, boolean insertAfter){
    JepRecord newPositionRecord = dataProvider.getList().get(newIndex);
    if (oldRowList.size() == 1) {
      if (!JepRiaUtil.isEmpty(newPositionRecord) && !JepRiaUtil.isEmpty(newPositionRecord.get(WORK_SEQUENCE))) {
          dropEventListener.handleEvent(new DropEvent((JepRecord) oldRowList.get(0), 
              newPositionRecord, insertAfter));
          super.changeRowPosition(oldRowList, newIndex, isOver, insertBefore, insertAfter);
      } else {
        new ErrorDialog(JepTexts.errors_dialog_title(), 
            new Throwable(featureText.feature_list_drop_empty_work_sequence_error_text()), 
            featureText.feature_list_drop_empty_work_sequence_error_text()).show();
      }
    } else {
      new ErrorDialog(JepTexts.errors_dialog_title(), 
          new Throwable(featureText.feature_list_drop_only_one_record_error_text()), 
          featureText.feature_list_drop_only_one_record_error_text()).show();
    }
  }
}
