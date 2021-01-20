import { ConnectorCrud } from "@jfront/core-rest";
import { API_PATH } from "../../../config";
import { Feature, FeatureCreate, FeatureUpdate } from "./FeatureTypes";

class FeatureCrudApi extends ConnectorCrud<Feature, string, FeatureCreate, FeatureUpdate> {
  constructor(baseUrl: string) {
    super(baseUrl, false);
  }
}

export const featureCrudApi = new FeatureCrudApi(API_PATH + "/feature");
