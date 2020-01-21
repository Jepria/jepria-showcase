package com.technology.jep.jepriashowcase.featureprocess.client.ui.toolbar;
 
import static com.technology.jep.jepria.client.ui.toolbar.ToolBarConstant.*;
import com.technology.jep.jepria.client.ui.toolbar.ToolBarViewImpl;
import static com.technology.jep.jepria.client.JepRiaClientConstant.JepTexts;
import static com.technology.jep.jepria.client.JepRiaClientConstant.JepImages;
 
public class FeatureProcessToolBarViewImpl extends ToolBarViewImpl implements FeatureProcessToolBarView {
   
  public FeatureProcessToolBarViewImpl() {
    super();
    
    removeItem(EDIT_BUTTON_ID);
  }
}
