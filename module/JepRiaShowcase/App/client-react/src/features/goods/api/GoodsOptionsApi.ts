import {
  GoodsSegmentOptions,
  UnitOptions,
  GoodsTypeOptions,
  MotivationTypeOptions,
} from "./GoodsTypes";
import { ConnectorBase } from "@jfront/core-rest";
import { API_PATH } from "../../../config";

class GoodsOptionsApi extends ConnectorBase {
  constructor(baseUrl: string) {
    super(baseUrl, false);
  }

  getGoodsSegment(): Promise<GoodsSegmentOptions[]> {
    const url = `${this.baseUrl}/option/goods-segment`;

    return new Promise<GoodsSegmentOptions[]>((resolve, reject) => {
      this.getAxios()
        .get(url, {
          headers: {
            Accept: "application/json;charset=utf-8",
            "Content-Type": "application/json;charset=utf-8",
          },
        })
        .then((response) => {
          console.log("response");
          console.log(response);
          resolve(response.data);
        })
        .catch((reason) => {
          reject(reason);
        });
    });
  }

  getUnit(): Promise<UnitOptions[]> {
    const url = `${this.baseUrl}/option/unit`;

    return this.getAxios()
      .get(url, {
        headers: {
          Accept: "application/json;charset=utf-8",
          "Content-Type": "application/json;charset=utf-8",
        },
      })
      .then((response) => response.data)
      .catch((reason) => {
        return Promise.reject(reason);
      });
  }

  getGoodsType(): Promise<GoodsTypeOptions[]> {
    const url = `${this.baseUrl}/option/goods-type`;

    return this.getAxios()
      .get(url, {
        headers: {
          Accept: "application/json;charset=utf-8",
          "Content-Type": "application/json;charset=utf-8",
        },
      })
      .then((response) => response.data)
      .catch((reason) => {
        return Promise.reject(reason);
      });
  }

  getMotivationType(): Promise<MotivationTypeOptions[]> {
    const url = `${this.baseUrl}/option/motivation-type`;

    return this.getAxios()
      .get(url, {
        headers: {
          Accept: "application/json;charset=utf-8",
          "Content-Type": "application/json;charset=utf-8",
        },
      })
      .then((response) => response.data)
      .catch((reason) => {
        return Promise.reject(reason);
      });
  }
}

export const goodsOptionsApi = new GoodsOptionsApi(API_PATH + "/goods");
