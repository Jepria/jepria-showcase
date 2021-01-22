import { Feature, FeatureSearchTemplate } from "./api/FeatureTypes";
import { createSearchSlice, SearchState } from "@jfront/core-redux-thunk";
import { featureSearchApi } from "./api/FeatureSearchApi";

export const initialSearchState: SearchState<FeatureSearchTemplate, Feature> = {
  searchRequest: { template: { maxRowCount: 250 } },
  error: null,
  isLoading: false,
  records: [],
  searchId: null,
  pageSize: 25,
  pageNumber: 1,
  // submit: false,
  resultSetSize: null,
};

export const slice = createSearchSlice({
  name: "featureSearch",
  initialState: initialSearchState,
  reducers: {},
});

const thunkCreators = slice.thunk;

export const search = thunkCreators.searchThunk(featureSearchApi);
export const postSearch = thunkCreators.postSearchThunk(featureSearchApi);
export const postSearchRequest = thunkCreators.postSearchRequestThunk(featureSearchApi);

export const { name, actions, reducer } = slice;
