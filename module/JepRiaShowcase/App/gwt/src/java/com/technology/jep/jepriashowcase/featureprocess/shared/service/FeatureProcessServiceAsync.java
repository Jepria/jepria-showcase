package com.technology.jep.jepriashowcase.featureprocess.shared.service;
 
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.technology.jep.jepria.shared.field.option.JepOption;
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;
 
public interface FeatureProcessServiceAsync extends JepDataServiceAsync {
  void getFeatureStatus(AsyncCallback<List<JepOption>> callback);
}
