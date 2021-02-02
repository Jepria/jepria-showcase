import { createCrudSlice, EntityState } from "@jfront/core-redux-thunk";
import { Feature } from "../api/FeatureTypes";
import { featureCrudApi } from "../api/FeatureCrudApi";

export const initialEntityState: EntityState<Feature> = {
  currentRecord: null,
  error: null,
  isLoading: false,
  selectedRecords: [],
};

const entitySlice = createCrudSlice({
  name: "featureSlice",
  initialState: initialEntityState,
  reducers: {},
});

const thunkCreators = entitySlice.thunk;

export const getRecordById = thunkCreators.getRecordByIdThunk(featureCrudApi);
export const createRecord = thunkCreators.createThunk(featureCrudApi);
export const updateRecord = thunkCreators.updateThunk(featureCrudApi);
export const deleteRecord = thunkCreators.deleteThunk(featureCrudApi);

export const { name, actions, reducer } = entitySlice;
