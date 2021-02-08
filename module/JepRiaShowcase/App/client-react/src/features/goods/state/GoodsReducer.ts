import { Goods, GoodsSearchTemplate } from "../api/GoodsTypes";
import { EntityState, OptionState, SearchState } from "@jfront/core-redux-thunk";
import { combineReducers } from "@reduxjs/toolkit";
import { initialEntityState, reducer as crudReducer } from "./GoodsCrudSlice";
import { initialSearchState, reducer as searchReducer } from "./GoodsSearchSlice";
import goodsOptionsReducer from "./GoodsOptionsSlice";

export interface GoodsState {
  goods: {
    goodsSearchSlice: SearchState<GoodsSearchTemplate, Goods>;
    goodsCrudSlice: EntityState<Goods>;
    // goodsOptionsSlice: OptionState
  };
}

export const initialState: GoodsState = {
  goods: {
    goodsSearchSlice: initialSearchState,
    goodsCrudSlice: initialEntityState,
  },
};

export const goodsReducer = combineReducers({
  goodsSearchSlice: searchReducer,
  goodsCrudSlice: crudReducer,
  goodsOptionsSlice: goodsOptionsReducer,
});
