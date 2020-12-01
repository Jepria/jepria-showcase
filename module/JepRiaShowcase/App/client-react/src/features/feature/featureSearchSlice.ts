import { SearchState, createGenericSearchSlice } from "./../../app/common/recordSearchSlice";
import queryString from "query-string";
import { SearchRequest } from "@jfront/core-rest";
import { Feature, FeatureSearchTemplate } from "./api/FeatureInterface";
import { featureCrudApi } from "./api/FeatureSearchApi";
import { AppThunk, RootState } from "./../../app/store";

const initialState: SearchState<Feature, FeatureSearchTemplate> = {
  searchTemplate: {},
  error: null,
  isLoading: false,
  searchResult: [],
  pageSize: 25,
  page: 1,
  submit: false,
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

export const selectSearchResult = (state: RootState) => state.featureSearch.searchResult;
export const selectError = (state: RootState) => state.featureSearch.error;
export const selectIsLoading = (state: RootState) => state.featureSearch.isLoading;
export const selectSearchTemplate = (state: RootState) => state.featureSearch.searchTemplate;
export const selectSearchPageSize = (state: RootState) => state.featureSearch.pageSize;
export const selectSearchPage = (state: RootState) => state.featureSearch.page;
export const selectSearchSubmit = (state: RootState) => state.featureSearch.submit;

/**
 * Fetch features and update search result
 * @param {string} searchRequestString template string from query
 * @param {number} pageSize page size
 * @param {number} page page index
 */

export const fetchSearchFeatures = (
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
    featureCrudApi.postSearchRequest(searchRequest).then((searchId) => {
      featureCrudApi.getResultSetSize(searchId).then((resultSize) => {
        if (resultSize > 0) {
          if (searchId) {
            featureCrudApi.search(searchId, pageSize, page).then((features) => {
              dispatch(searchSuccess(features));
              dispatch(setSearchTemplate(JSON.parse(JSON.stringify(searchTemplate))));
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
    dispatch(searchError(err));
  }
};

export default featureSearchSlice.reducer;
