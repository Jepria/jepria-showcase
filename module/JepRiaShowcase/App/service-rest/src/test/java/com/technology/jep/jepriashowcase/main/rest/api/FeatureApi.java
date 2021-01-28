package com.technology.jep.jepriashowcase.main.rest.api;


import javax.ws.rs.core.Response;

public interface FeatureApi {
  Response getFeatureOperator();
  Response getFeatureStatus();
  Response getRecordById(Integer featureId);
}
