package com.technology.jep.jepriashowcase.featureoperator.shared.service;
 
import com.technology.jep.jepria.shared.service.data.JepDataServiceAsync;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.technology.jep.jepria.shared.field.option.JepOption;
 
public interface FeatureOperatorServiceAsync extends JepDataServiceAsync {
  void getFeatureOperator(String operatorName, AsyncCallback<List<JepOption>> callback);
}
