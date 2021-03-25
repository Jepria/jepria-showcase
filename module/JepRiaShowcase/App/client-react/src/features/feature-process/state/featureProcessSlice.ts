import { createSlice } from "@reduxjs/toolkit";
import { FeatureProcess } from "../api/FeatureProcessTypes";
import { AppThunk, RootState } from "../../../app/store";

interface FeatureProcessState {
  currentRecord: FeatureProcess;
  error: string;
  saveOnCreateFeatureProcess: boolean;
}

const initialState: FeatureProcessState = {
  currentRecord: null,
  error: null,
  saveOnCreateFeatureProcess: false,
};

export const featureProcessSlice = createSlice({
  name: "featureProcess",
  initialState,
  reducers: {
    setSaveOnCreateFeatureProcess(state, action) {
      state.saveOnCreateFeatureProcess = action.payload;
    },
    setCurrentFeatureProcess(state, action) {
      state.currentRecord = action.payload;
    },
    getFeatureError(state, action) {
      state.error = action.payload;
      state.currentRecord = null;
    },
  },
});

export const { setCurrentFeatureProcess } = featureProcessSlice.actions;
export const { getFeatureError } = featureProcessSlice.actions;
export const { setSaveOnCreateFeatureProcess } = featureProcessSlice.actions;

export const selectFeatureProcess = (state: RootState) =>
  state.featureProcess.currentRecord; 
export const selectError = (state: RootState) => state.featureProcess.error;
export const selectSaveOnCreateFeatureProcess = (state: RootState) =>
  state.featureProcess.saveOnCreateFeatureProcess;

export const submitSaveOnCreateFeatureProcess = (): AppThunk => async (dispatch) => {
  dispatch(setSaveOnCreateFeatureProcess(true));
};

export const submitSavedOnCreateFeatureProcess = (): AppThunk => async (dispatch) => {
  dispatch(setSaveOnCreateFeatureProcess(false));
};

export default featureProcessSlice.reducer;
