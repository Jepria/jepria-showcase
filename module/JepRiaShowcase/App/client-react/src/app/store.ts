import { configureStore, ThunkAction, Action, getDefaultMiddleware } from "@reduxjs/toolkit";
import featureReducer from "../features/feature/featureSlice";
import featureSearchReducer from "../features/feature/featureSearchSlice";
import featureProcessReducer from "../features/feature-process/featureProcessSlice";
import GoodsReducer from "../features/goods/GoodsSlice";
import GoodsSearchReducer from "../features/goods/GoodsSearchSlice";
import goodsOptionsReducer from "../features/goods/GoodsOptionsSlice";
import logger from "redux-logger";

export const store = configureStore({
  reducer: {
    feature: featureReducer,
    featureSearch: featureSearchReducer,
    featureProcess: featureProcessReducer,
    goods: GoodsReducer,
    goodsSearch: GoodsSearchReducer,
    goodsOptions: goodsOptionsReducer,
  },
  middleware: [...getDefaultMiddleware().concat(logger)],
});

export type RootState = ReturnType<typeof store.getState>;
export type AppThunk = ThunkAction<void, RootState, unknown, Action<string>>;
