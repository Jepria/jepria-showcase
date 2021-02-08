import { ConnectorCrud } from "@jfront/core-rest";
import axios, { AxiosInstance } from "axios";
import { API_PATH } from "../../../config";
import { Goods, GoodsCreate, GoodsUpdate } from "./GoodsTypes";

class GoodsCrudApi extends ConnectorCrud<Goods, string, GoodsCreate, GoodsUpdate> {
  constructor(baseUrl: string, withCrudentials = false, axiosInstance?: AxiosInstance) {
    super(baseUrl, withCrudentials, axiosInstance);
  }
}

export const goodsCrudApi = new GoodsCrudApi(API_PATH + "/goods", false, axios);
