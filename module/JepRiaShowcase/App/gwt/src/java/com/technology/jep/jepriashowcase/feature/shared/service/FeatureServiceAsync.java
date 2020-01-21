package com.technology.jep.jepriashowcase.feature.shared.service;
 
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
 
public interface FeatureServiceAsync extends JepDataServiceAsync {
  void getFeatureOperator(String featureOperatorName, Integer maxRowCount, AsyncCallback<List<JepOption>> callback);

  void setFeatureWorkSequence(Integer featureId, Integer workSequence,
      AsyncCallback<Void> callback);
  
  void getFeatureStatus(AsyncCallback<List<JepOption>> callback);
}
