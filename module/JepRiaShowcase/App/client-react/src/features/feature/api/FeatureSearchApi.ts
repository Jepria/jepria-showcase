import { Feature, FeatureSearchTemplate } from "./FeatureTypes";
import { ConnectorSearch } from "@jfront/core-rest";

class FeatureSearchApi extends ConnectorSearch<Feature, FeatureSearchTemplate> {
  constructor(baseUrl: string) {
    super(baseUrl, false);
  }
}

export const featureSearchApi = new FeatureSearchApi(process.env.REACT_APP_API_URL + "/feature");
