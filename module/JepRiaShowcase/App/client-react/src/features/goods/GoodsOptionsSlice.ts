import { combineReducers } from "@reduxjs/toolkit";
import { goodsOptionsApi } from "./api/GoodsOptionsApi";
import { createOptionsSlice, OptionState } from "./../../app/common/optionsSlice";
import { Options } from "./api/GoodsTypes";

const initialState: OptionState<Options<string>> = {
  error: null,
  isLoading: false,
  options: [],
};

const GoodsSegmentOptionsSlice = createOptionsSlice({
  name: "GoodsSegmentOptionsSlice",
  initialState: initialState,
  reducers: {},
});

export const getGoodsSegmentOptions = GoodsSegmentOptionsSlice.thunk.getOptions(() => {
  return goodsOptionsApi.getGoodsSegment();
});

const UnitOptionsSlice = createOptionsSlice({
  name: "UnitOptionsSlice",
  initialState: initialState,
  reducers: {},
});

export const getUnitOptions = UnitOptionsSlice.thunk.getOptions(() => {
  return goodsOptionsApi.getUnit();
});

const GoodsTypeOptionsSlice = createOptionsSlice({
  name: "GoodsTypeOptionsSlice",
  initialState: initialState,
  reducers: {},
});

export const getGoodsTypeOptions = GoodsTypeOptionsSlice.thunk.getOptions(() => {
  return goodsOptionsApi.getGoodsType();
});

const MotivationTypeOptionsSlice = createOptionsSlice({
  name: "MotivationTypeOptionsSlice",
  initialState: initialState,
  reducers: {},
});

export const getMotivationTypeOptions = MotivationTypeOptionsSlice.thunk.getOptions(() => {
  return goodsOptionsApi.getMotivationType();
});

const goodsOptionsReducer = combineReducers({
  goodsSegment: GoodsSegmentOptionsSlice.reducer,
  unitOptions: UnitOptionsSlice.reducer,
  goodsType: GoodsTypeOptionsSlice.reducer,
  motivationType: MotivationTypeOptionsSlice.reducer,
});

export default goodsOptionsReducer;
