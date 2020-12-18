//TODO interface to type?
export interface Goods {
  goodsId: number;
  supplierId: number;
  goodsName: string;
  goodsTypeCode?: {
    name: string;
    value: string;
  };
  purchasingPrice: number;
  UnitCode?: {
    name: string;
    value: string;
  };
  MotivationTypeCode?: {
    name: string;
    value: string;
  };
  goodsPhotoMimeType: string;
  goodsPhotoExtension: string;
  goodsPortfolioMimeType: string;
  goodsPortfolioExtension: string;
}

export interface GoodsCreate {
  supplierId?: number;
  goodsName?: string;
  goodsTypeCode?: string;
  purchasingPrice?: number;
  UnitCode?: string;
  MotivationTypeCode?: string;
  goodsPhotoMimeType?: string;
  goodsPhotoExtension?: string;
  goodsPortfolioMimeType?: string;
  goodsPortfolioExtension?: string;
}

export interface GoodsUpdate {
  supplierId?: number;
  goodsName?: string;
  goodsTypeCode?: string;
  purchasingPrice?: number;
  UnitCode?: string;
  MotivationTypeCode?: string;
  goodsPhotoMimeType?: string;
  goodsPhotoExtension?: string;
  goodsPortfolioMimeType?: string;
  goodsPortfolioExtension?: string;
}

export interface GoodsSearchTemplate {
  goodsId?: number;
  supplierId?: number;
  goodsName?: string;
  goodsTypeCode?: string;
  goodsSegmentCode?: string[];
  purchasingPrice?: number;
  UnitCode?: string;
  MotivationTypeCode?: string;
  goodsPhotoMimeType?: string;
  goodsPhotoExtension?: string;
  goodsPortfolioMimeType?: string;
  goodsPortfolioExtension?: string;
  maxRowCount?: number;
}

export interface Options<T> {
  name: string;
  value: T;
}

export interface GoodsSegmentOptions extends Options<string> {}

export interface GoodsTypeOptions extends Options<string> {}

export interface UnitOptions extends Options<string> {}

export interface MotivationTypeOptions extends Options<string> {}
