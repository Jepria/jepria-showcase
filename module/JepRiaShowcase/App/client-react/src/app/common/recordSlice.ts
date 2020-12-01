import {
  createSlice,
  PayloadAction,
  SliceCaseReducers,
  ValidateSliceCaseReducers,
} from "@reduxjs/toolkit";
import { AppThunk, RootState } from "./../../app/store";

export interface RecordState<T = any> {
  currentRecord: T;
  error: string;
  saveOnCreate: boolean;
  saveOnEdit: boolean;
}

// const initialState: RecordState = {
//   currentRecord: null,
//   error: null,
//   saveOnCreate: false,
//   saveOnEdit: false,
// };

// export const recordSlice = createSlice({
//   name: "record",
//   initialState,
//   reducers: {
//     setCreateRecord(state, action) {
//       state.saveOnCreate = action.payload;
//     },
//     setSaveOnEditRecord(state, action) {
//       state.saveOnEdit = action.payload;
//     },
//     setCurrentRecord(state, action) {
//       state.currentRecord = action.payload;
//     },
//     getError(state, action) {
//       state.error = action.payload;
//       state.currentRecord = null;
//     },
//   },
// });

export const createGenericSlice = <T, Reducers extends SliceCaseReducers<RecordState<T>>>({
  name = "",
  initialState,
  reducers,
}: {
  name: string;
  initialState: RecordState<T>;
  reducers: ValidateSliceCaseReducers<RecordState<T>, Reducers>;
}) => {
  return createSlice({
    name,
    initialState,
    reducers: {
      setCreateRecord(state, action) {
        state.saveOnCreate = action.payload;
      },
      setSaveOnEditRecord(state, action) {
        state.saveOnEdit = action.payload;
      },
      setCurrentRecord(state, action) {
        state.currentRecord = action.payload;
      },
      getError(state, action) {
        state.error = action.payload;
        state.currentRecord = null;
      },
      ...reducers,
    },
  });
};

// export const { setCurrentRecord } = recordSlice.actions;
// export const { getError } = recordSlice.actions;
// export const { setCreateRecord } = recordSlice.actions;
// export const { setSaveOnEditRecord } = recordSlice.actions;

// export const selectFeature = (state: RootState) => state.feature.currentFeature;
// export const selectError = (state: RootState) => state.feature.error;
// export const selectSaveOnCreateFeature = (state: RootState) => state.feature.saveOnCreateFeature;
// export const selectSaveOnEditFeature = (state: RootState) => state.feature.saveOnEditFeature;

// export const fetchRecord = (featureId: string): AppThunk => async (dispatch) => {
//   try {
//     getFeature(featureId).then((feature) => {
//       dispatch(setCurrentRecord(feature));
//     });
//   } catch (error) {
//     dispatch(getError(error));
//   }
// };

// export const createRecord = (record: FeatureCreate): AppThunk => async (dispatch) => {
//   try {
//     createFeatureApi(record).then((feature) => {
//       dispatch(setCurrentRecord(feature));
//       // dispatch(setCreateFeature(true));
//     });
//   } catch (error) {
//     dispatch(getError(error));
//   }
// };

// export const submitSaveOnCreate = (): AppThunk => async (dispatch) => {
//   dispatch(setCreateRecord(true));
// };

// export const submitSavedOnCreate = (): AppThunk => async (dispatch) => {
//   dispatch(setCreateRecord(false));
// };

// export const submitSaveOnEditRecord = (): AppThunk => async (dispatch) => {
//   dispatch(setSaveOnEditRecord(true));
// };

// export const submitSavedOnEditRecord = (): AppThunk => async (dispatch) => {
//   dispatch(setSaveOnEditRecord(false));
// };

// export default recordSlice.reducer;
