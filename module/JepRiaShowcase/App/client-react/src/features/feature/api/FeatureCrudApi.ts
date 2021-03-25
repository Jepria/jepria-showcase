import axios, { AxiosInstance } from "axios";
import { ConnectorCrud } from "@jfront/core-rest";
import { API_PATH } from "../../../config";
import { Feature, FeatureCreate, FeatureUpdate } from "./FeatureTypes";

class FeatureCrudApi extends ConnectorCrud<Feature, number, FeatureCreate, FeatureUpdate> {
  constructor(
    baseUrl: string,
    withCrudentials = false,
    axiosInstance?: AxiosInstance
  ) {
    super(baseUrl, withCrudentials, axiosInstance);
  }
}

export const featureCrudApi = new FeatureCrudApi(API_PATH + "/feature", false, axios);
