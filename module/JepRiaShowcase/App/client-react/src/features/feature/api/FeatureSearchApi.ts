import { Feature, FeatureSearchTemplate, ResultSet } from "./FeatureTypes";
import {
  buildError,
  ConnectorSearch,
  handleAxiosError,
} from "@jfront/core-rest";
import { API_PATH } from "../../../config";
import axios, { AxiosInstance } from "axios";
class FeatureSearchApi extends ConnectorSearch<Feature, FeatureSearchTemplate> {
  constructor(
    baseUrl: string,
    withCrudentials = false,
    axiosInstance?: AxiosInstance
  ) {
    super(baseUrl, true, axiosInstance);
  }

  querySearch = (query: string) => {
    return new Promise<ResultSet>((resolve, reject) => {
      this.getAxios()
        .get(this.baseUrl + `/search?${query}`, {
          headers: {
            Accept: "application/json;charset=utf-8",
            "Content-Type": "application/json;charset=utf-8",
            "Cache-Control": "no-cache",
          },
        })
        .then((response) => {
          if (response.status === 200) {
            resolve(response.data);
          } else {
            reject(buildError(response));
          }
        })
        .catch((error) => reject(handleAxiosError(error)));
    });
  };
}

export const featureSearchApi = new FeatureSearchApi(
  API_PATH + "/feature",
  false,
  axios
);
