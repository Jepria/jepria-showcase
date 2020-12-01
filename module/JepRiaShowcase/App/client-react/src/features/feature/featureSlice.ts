import { RecordState } from "./../../app/common/recordSlice";
import { Feature, FeatureCreate } from "./api/FeatureTypes";
import { AppThunk, RootState } from "./../../app/store";
import { createGenericSlice } from "../../app/common/recordSlice";
import { featureCrudApi } from "./api/FeatureCrudApi";

const initialState: RecordState<Feature> = {
  currentRecord: null,
  error: null,
  saveOnCreate: false,
  saveOnEdit: false,
};

export const featureSlice = createGenericSlice({
  name: "featureSlice",
  initialState: initialState,
  reducers: {},
});

export const { setCurrentRecord } = featureSlice.actions;
export const { getError } = featureSlice.actions;
export const { setCreateRecord } = featureSlice.actions;
export const { setSaveOnEditRecord } = featureSlice.actions;

export const selectFeature = (state: RootState) => state.feature.currentRecord;
export const selectError = (state: RootState) => state.feature.error;
export const selectSaveOnCreateFeature = (state: RootState) => state.feature.saveOnCreate;
export const selectSaveOnEditFeature = (state: RootState) => state.feature.saveOnEdit;

export const fetchFeature = (featureId: string): AppThunk => async (dispatch) => {
  try {
    featureCrudApi.getRecordById(featureId).then((feature) => {
      dispatch(setCurrentRecord(feature));
    });
  } catch (error) {
    dispatch(getError(error));
  }
};

export const createFeature = (feature: FeatureCreate): AppThunk => async (dispatch) => {
  try {
    featureCrudApi.create(feature).then((feature) => {
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

export default featureSlice.reducer;
