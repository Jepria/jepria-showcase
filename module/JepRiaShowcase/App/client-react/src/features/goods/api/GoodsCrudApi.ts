import { ConnectorCrud } from "@jfront/core-rest";
import { API_PATH } from "../../../config";
import { Goods, GoodsCreate, GoodsUpdate } from "./GoodsTypes";

class GoodsCrudApi extends ConnectorCrud<Goods, GoodsCreate, GoodsUpdate> {
  constructor(baseUrl: string) {
    super(baseUrl, false);
  }
}

export const goodsCrudApi = new GoodsCrudApi(API_PATH + "/goods");
