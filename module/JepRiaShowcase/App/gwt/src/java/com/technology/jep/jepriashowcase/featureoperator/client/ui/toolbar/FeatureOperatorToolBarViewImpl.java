package com.technology.jep.jepriashowcase.featureoperator.client.ui.toolbar;
 
import com.technology.jep.jepria.client.ui.toolbar.ToolBarViewImpl;
import static com.technology.jep.jepria.client.ui.toolbar.ToolBarConstant.*;

public class FeatureOperatorToolBarViewImpl extends ToolBarViewImpl implements FeatureOperatorToolBarView {
   
   
  public FeatureOperatorToolBarViewImpl() {
    super();
    
    removeItem(EDIT_BUTTON_ID);
  }
}
