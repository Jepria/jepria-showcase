import { RecordState } from "./../../app/common/recordSlice";
import { Goods, GoodsCreate } from "./api/GoodsTypes";
import { AppThunk, RootState } from "./../../app/store";
import { createGenericSlice } from "../../app/common/recordSlice";
import { goodsCrudApi } from "./api/GoodsCrudApi";

const initialState: RecordState<Goods> = {
  currentRecord: null,
  error: null,
  saveOnCreate: false,
  saveOnEdit: false,
};

export const GoodsSlice = createGenericSlice({
  name: "goodsSlice",
  initialState: initialState,
  reducers: {},
});

export const { setCurrentRecord } = GoodsSlice.actions;
export const { getError } = GoodsSlice.actions;
export const { setCreateRecord } = GoodsSlice.actions;
export const { setSaveOnEditRecord } = GoodsSlice.actions;

export const selectCurrentRecord = (state: RootState) => state.feature.currentRecord;
export const selectError = (state: RootState) => state.feature.error;
export const selectSaveOnCreateFeature = (state: RootState) => state.feature.saveOnCreate;
export const selectSaveOnEditFeature = (state: RootState) => state.feature.saveOnEdit;

export const fetchFeature = (featureId: string): AppThunk => async (dispatch) => {
  try {
    goodsCrudApi.getRecordById(featureId).then((feature) => {
      dispatch(setCurrentRecord(feature));
    });
  } catch (error) {
    dispatch(getError(error));
  }
};

export const createFeature = (feature: GoodsCreate): AppThunk => async (dispatch) => {
  try {
    goodsCrudApi.create(feature).then((feature) => {
      dispatch(setCurrentRecord(feature));
    });
  } catch (error) {
    dispatch(getError(error));
  }
};

export const submitSaveOnCreate = (): AppThunk => async (dispatch) => {
  dispatch(setCreateRecord(true));
};

export const submitSavedOnCreate = (): AppThunk => async (dispatch) => {
  dispatch(setCreateRecord(false));
};

export const submitSaveOnEditFeature = (): AppThunk => async (dispatch) => {
  dispatch(setSaveOnEditRecord(true));
};

export const submitSavedOnEditFeature = (): AppThunk => async (dispatch) => {
  dispatch(setSaveOnEditRecord(false));
};

export default GoodsSlice.reducer;
