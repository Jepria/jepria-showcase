import { ConnectorCrud } from "@jfront/core-rest";
import axios, { AxiosInstance } from "axios";
import { API_PATH } from "../../../config";
import { FeatureProcess, FeatureProcessCreate } from "./FeatureProcessTypes";

class FeatureProcessCrudApi extends ConnectorCrud<FeatureProcess, string, FeatureProcessCreate> {
  constructor(baseUrl: string, withCrudentials = false, axiosInstance?: AxiosInstance) {
    super(baseUrl, withCrudentials, axiosInstance);
  }

  createFeatureProcess = (
    featureId: number,
    featureProcessCreate: FeatureProcessCreate
  ): Promise<FeatureProcess> => {
    const url = `${this.baseUrl}/feature/${featureId}/feature-process`;

    return new Promise<FeatureProcess>((resolve, reject) => {
      this.getAxios()
        .post(url, featureProcessCreate, {
          headers: {
            Accept: "application/json;charset=utf-8",
            "Content-Type": "application/json;charset=utf-8",
          },
        })
        .then((response) => {
          if (response.status === 201) {
            let location: string = response.headers["location"];
            axios
              .get(location, {
                headers: {
                  Accept: "application/json;charset=utf-8",
                  "Content-Type": "application/json;charset=utf-8",
                },
              })
              .then((response) => {
                if (200 === response.status) {
                  resolve(response.data);
                } else {
                  reject(response);
                }
              })
              .catch((error) => reject(error));
          } else {
            reject(response);
          }
        })
        .catch((error) => reject(error));
    });
  };
}

export const featureCrudApi = new FeatureProcessCrudApi(API_PATH + "/feature", true, axios);
