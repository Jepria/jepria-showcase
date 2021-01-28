package com.technology.jep.jepriashowcase.main.rest.api;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class FeatureApiImpl implements FeatureApi {

  private final WebTarget client;

  public FeatureApiImpl(WebTarget client) {
    this.client = client;
  }

  @Override
  public Response getFeatureOperator() {
    return client.path("/feature/option/feature-operator").request(MediaType.APPLICATION_JSON_TYPE).get();
  }

  @Override
  public Response getFeatureStatus() {
    return client.path("/feature/option/feature-status").request(MediaType.APPLICATION_JSON_TYPE).get();
  }

  @Override
  public Response getRecordById(Integer featureId) {
    return client.path("/feature/" + featureId).request(MediaType.APPLICATION_JSON_TYPE).get();
  }
}
