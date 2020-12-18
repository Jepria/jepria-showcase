import { Goods, GoodsSearchTemplate } from "./GoodsTypes";
import { ConnectorSearch } from "@jfront/core-rest";
import { API_PATH } from "../../../config";

class GodsSearchApi extends ConnectorSearch<Goods, GoodsSearchTemplate> {
  constructor(baseUrl: string) {
    super(baseUrl, false);
  }
}

export const goodsSearchApi = new GodsSearchApi(API_PATH + "/goods");
