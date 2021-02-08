import { createCrudSlice, EntityState } from "@jfront/core-redux-thunk";
import { Goods } from "../api/GoodsTypes";
import { goodsCrudApi } from "../api/GoodsCrudApi";

export const initialEntityState: EntityState<Goods> = {
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

export const getRecordById = thunkCreators.getRecordByIdThunk(goodsCrudApi);
export const createRecord = thunkCreators.createThunk(goodsCrudApi);
export const updateRecord = thunkCreators.updateThunk(goodsCrudApi);
export const deleteRecord = thunkCreators.deleteThunk(goodsCrudApi);

export const { name, actions, reducer } = entitySlice;
