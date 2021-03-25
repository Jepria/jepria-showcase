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
  searchId: null,
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
  reducers: {
    querySearch(state, action: PayloadAction<QuerySearch>) {
      state.records = [];
      state.isLoading = true;
      state.searchRequest = action.payload.template;
      state.pageNumber = action.payload.pageNumber;
      state.pageSize = action.payload.pageSize;
    },
  },
});

const thunkCreators = slice.thunk;

export const querySearch = function (
  template: FeatureSearchTemplate,
  listSortConfiguration: ColumnSortConfiguration[],
  pageSize: number,
  pageNumber: number
): ThunkAction<
  Promise<Array<Feature>>,
  SearchState<FeatureSearchTemplate, Feature>,
  unknown,
  Action<string>
> {
  return async (dispatch) => {
    try {
      dispatch(
        actions.querySearch({
          template: {
            template,
            listSortConfiguration,
          },
          pageSize,
          pageNumber,
        })
      );
      const query = {
        ...template,
        sort: listSortConfiguration?.map(
          (sortConfig) => `${sortConfig.columnName},${sortConfig.sortOrder}`
        ),
        page: pageNumber,
        pageSize,
      };
      const result = await featureSearchApi.querySearch(
        queryString.stringify(query)
      );
      dispatch(
        actions.searchSuccess({
          records: result.data,
          resultSetSize: result.resultsetSize,
        })
      );
      return result.data;
    } catch (error) {
      dispatch(actions.failure({ error }));
      return Promise.reject(error);
    }
  };
};
export const search = thunkCreators.searchThunk(featureSearchApi);
export const postSearch = thunkCreators.postSearchThunk(featureSearchApi);
export const postSearchRequest = thunkCreators.postSearchRequestThunk(
  featureSearchApi
);

export const { name, actions, reducer } = slice;
