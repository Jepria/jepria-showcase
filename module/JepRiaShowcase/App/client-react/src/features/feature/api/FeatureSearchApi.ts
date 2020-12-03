import { Feature, FeatureSearchTemplate } from "./FeatureTypes";
import { ConnectorSearch } from "@jfront/core-rest";
import { API_PATH } from "../../../config";

class FeatureSearchApi extends ConnectorSearch<Feature, FeatureSearchTemplate> {
  constructor(baseUrl: string) {
    super(baseUrl, false);
  }
}

export const featureSearchApi = new FeatureSearchApi(API_PATH + "/feature");
