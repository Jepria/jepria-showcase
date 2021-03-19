import { createCrudSlice, EntityState } from "@jfront/core-redux-thunk";
import { FeatureProcess } from "../api/FeatureProcessTypes";
import { featureCrudApi } from "../api/FeatureProcessApi";

export const initialEntityState: EntityState<FeatureProcess> = {
  currentRecord: null,
  error: null,
  isLoading: false,
  selectedRecords: [],
};

const entitySlice = createCrudSlice({
  name: "featureProcessSlice",
  initialState: initialEntityState,
  reducers: {},
});

const thunkCreators = entitySlice.thunk;

export const getRecordById = thunkCreators.getRecordByIdThunk(featureCrudApi);
export const createRecord = thunkCreators.createThunk(featureCrudApi);
export const updateRecord = thunkCreators.updateThunk(featureCrudApi);
export const deleteRecord = thunkCreators.deleteThunk(featureCrudApi);

export const { name, actions, reducer } = entitySlice;
