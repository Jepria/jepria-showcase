import { createSearchSlice, SearchState } from "@jfront/core-redux-thunk";
import { Feature, FeatureSearchTemplate } from "../api/FeatureTypes";
import { featureSearchApi } from "../api/FeatureSearchApi";
import { ThunkAction, Action, PayloadAction } from "@reduxjs/toolkit";
import { SearchRequest } from "@jfront/core-rest";
import { ColumnSortConfiguration } from "@jfront/ui-core";
import queryString from "query-string";

export const initialSearchState: SearchState<FeatureSearchTemplate, Feature> = {
  searchRequest: { template: { maxRowCount: 250 } },
  error: null,
  isLoading: false,
  records: [],
  pageSize: 25,
  pageNumber: 1,
  resultSetSize: null,
};

type QuerySearch = {
  template: SearchRequest<FeatureSearchTemplate>;
  pageNumber: number;
  pageSize: number;
};

export const slice = createSearchSlice({
  name: "featureSearch",
  initialState: initialSearchState,
  reducers: {},
});

const thunkCreators = slice.thunk;

export const search = thunkCreators.searchThunk(featureSearchApi);

export const { name, actions, reducer } = slice;
