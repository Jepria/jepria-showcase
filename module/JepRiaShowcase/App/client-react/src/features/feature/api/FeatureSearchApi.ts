import axios, { AxiosInstance } from "axios";
import { ConnectorSearch } from "@jfront/core-rest";
import { API_PATH } from "../../../config";
import { Feature, FeatureSearchTemplate } from "./FeatureTypes";

class FeatureSearchApi extends ConnectorSearch<Feature, FeatureSearchTemplate> {
  constructor(
    baseUrl: string,
    withCrudentials = false,
    axiosInstance?: AxiosInstance
  ) {
    super(baseUrl, withCrudentials, axiosInstance);
  }
}

export const featureSearchApi = new FeatureSearchApi(API_PATH + "/feature", false, axios);
