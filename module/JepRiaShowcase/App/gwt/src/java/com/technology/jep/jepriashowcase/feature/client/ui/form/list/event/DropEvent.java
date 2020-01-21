package com.technology.jep.jepriashowcase.feature.client.ui.form.list.event;

import com.technology.jep.jepria.client.widget.event.JepEvent;
import com.technology.jep.jepria.shared.record.JepRecord;

/**
 * Событие для передачи перемещенных при помощи Drag&Drop записей из GridManager в ListFormPresenter.
 * 
 * @author SavenkovAV
 *
 */
public class DropEvent extends JepEvent {

  private final JepRecord record;
  private final JepRecord newPositionRecord;
  private final boolean insertAfter;

  public DropEvent(JepRecord record, JepRecord newPositionRecord, boolean insertAfter) {
    this.record = record;
    this.newPositionRecord = newPositionRecord;
    this.insertAfter = insertAfter;
  }

  public JepRecord getRecord() {
    return this.record;
  }

  public JepRecord getNewPositionRecord() {
    return this.newPositionRecord;
  }

  public boolean isInsertAfter() {
    return insertAfter;
  }
}