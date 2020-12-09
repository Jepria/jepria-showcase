import queryString from "query-string";
import { SearchRequest } from "@jfront/core-rest";
import {
  SearchState,
  createGenericSearchSlice,
} from "./../../app/common/recordSearchSlice";
import { AppThunk, RootState } from "./../../app/store";
import { Feature, FeatureSearchTemplate } from "./api/FeatureTypes";
import { featureSearchApi } from "./api/FeatureSearchApi";

const initialState: SearchState<Feature, FeatureSearchTemplate> = {
  searchTemplate: { maxRowCount: 250 },
  error: null,
  isLoading: false,
  searchResult: [],
  searchId: null,
  pageSize: 25,
  page: 1,
  submit: false,
  resultSetSize: null,
};

export const featureSearchSlice = createGenericSearchSlice({
  name: "featureSearch",
  initialState: initialState,
  reducers: {},
});

export const { setSearchTemplate } = featureSearchSlice.actions;
export const { searchError } = featureSearchSlice.actions;
export const { isLoading } = featureSearchSlice.actions;
export const { searchSuccess } = featureSearchSlice.actions;
export const { submitSearch } = featureSearchSlice.actions;
export const { setResultSetSize } = featureSearchSlice.actions;
export const { setSearchId } = featureSearchSlice.actions;

export const selectSearchResult = (state: RootState) =>
  state.featureSearch.searchResult;
export const selectError = (state: RootState) => state.featureSearch.error;
export const selectIsLoading = (state: RootState) =>
  state.featureSearch.isLoading;
export const selectSearchTemplate = (state: RootState) =>
  state.featureSearch.searchTemplate;
export const selectSearchPageSize = (state: RootState) =>
  state.featureSearch.pageSize;
export const selectSearchPage = (state: RootState) => state.featureSearch.page;
export const selectSearchSubmit = (state: RootState) =>
  state.featureSearch.submit;
export const selectResultSetSize = (state: RootState) =>
  state.featureSearch.resultSetSize;
export const selectSearchId = (state: RootState) => state.featureSearch.searchId;  

/**
 * Fetch records and update search result
 * @param {string} searchRequestString template string from query
 * @param {number} pageSize page size
 * @param {number} page page index
 */
export const fetchFeatureSearchResultset = (
  searchRequestString: string,
  pageSize,
  page
): AppThunk => async (dispatch) => {
  try {
    let searchTemplate = queryString.parse(searchRequestString);
    if (searchTemplate.page) {
      delete searchTemplate.page;
    }
    if (searchTemplate.pageSize) {
      delete searchTemplate.pageSize;
    }
    let searchRequest: SearchRequest<FeatureSearchTemplate> = {
      template: searchTemplate,
    };

    dispatch(isLoading(true));
    dispatch(setSearchTemplate(JSON.parse(JSON.stringify(searchTemplate))));
    featureSearchApi.postSearchRequest(searchRequest).then((searchId) => {
      featureSearchApi.getResultSetSize(searchId).then((resultSize) => {
        dispatch(setResultSetSize(resultSize));
        if (resultSize > 0) {
          if (searchId) {
            featureSearchApi
              .search(searchId, pageSize, page)
              .then((records) => {
                dispatch(searchSuccess(records));
                dispatch(setSearchId(searchId));
                dispatch(isLoading(false));
              });
          }
        } else {
          alert("Search empty!");
          dispatch(isLoading(false));
        }
      });
    });
  } catch (err) {
    dispatch(isLoading(false));
    dispatch(setResultSetSize(0));
    dispatch(searchError(err));
  }
};

export const fetchSearchFeatures = (searchId: string, pageSize: number, page: number) : AppThunk => async (dispatch) => {
  try {
    dispatch(isLoading(true));
    featureSearchApi.search(searchId, pageSize, page).then((records) => {
      dispatch(searchSuccess(records));
      dispatch(isLoading(false));
    });
  } catch (err) {
    dispatch(isLoading(false));
    dispatch(searchError(err));
  }
};

export const postSearchFeatures  = (searchRequestString: string): AppThunk => async (dispatch) => {
  try {
    let searchTemplate = queryString.parse(searchRequestString);
    if (searchTemplate.page) {
      delete searchTemplate.page;
    }
    if (searchTemplate.pageSize) {
      delete searchTemplate.pageSize;
    }
    let searchRequest: SearchRequest<FeatureSearchTemplate> = {
      template: searchTemplate,
    };

    dispatch(isLoading(true));
    dispatch(setSearchTemplate(JSON.parse(JSON.stringify(searchTemplate))));
    featureSearchApi.postSearchRequest(searchRequest).then((searchId) => {
      featureSearchApi.getResultSetSize(searchId).then((resultSize) => {
        dispatch(setResultSetSize(resultSize));
        if (resultSize > 0) {
          if (searchId) {
            dispatch(isLoading(false));
            dispatch(setSearchId(searchId));
          }
        } else {
          alert("Search empty!");
          dispatch(isLoading(false));
        }
      });
    });
  } catch (err) {
    dispatch(isLoading(false));
    dispatch(setResultSetSize(0));
    dispatch(searchError(err));
  }
}


export default featureSearchSlice.reducer;
