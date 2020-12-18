import queryString from "query-string";
import { ColumnSortConfiguration, SearchRequest } from "@jfront/core-rest";
import {
  SearchState,
  createGenericSearchSlice,
} from "./../../app/common/recordSearchSlice";
import { AppThunk, RootState } from "./../../app/store";
import { Goods, GoodsSearchTemplate } from "./api/GoodsTypes";
import { goodsSearchApi } from "./api/GoodsSearchApi";

const initialState: SearchState<Goods, GoodsSearchTemplate> = {
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

export const GoodsSearchSlice = createGenericSearchSlice({
  name: "goodsSearch",
  initialState: initialState,
  reducers: {},
});

export const { setSearchTemplate } = GoodsSearchSlice.actions;
export const { searchError } = GoodsSearchSlice.actions;
export const { isLoading } = GoodsSearchSlice.actions;
export const { searchSuccess } = GoodsSearchSlice.actions;
export const { submitSearch } = GoodsSearchSlice.actions;
export const { setResultSetSize } = GoodsSearchSlice.actions;
export const { setSearchId } = GoodsSearchSlice.actions;

export const selectSearchResult = (state: RootState) =>
  state.goodsSearch.searchResult;
export const selectError = (state: RootState) => state.goodsSearch.error;
export const selectIsLoading = (state: RootState) =>
  state.goodsSearch.isLoading;
export const selectSearchTemplate = (state: RootState) =>
  state.goodsSearch.searchTemplate;
export const selectSearchPageSize = (state: RootState) =>
  state.goodsSearch.pageSize;
export const selectSearchPage = (state: RootState) => state.goodsSearch.page;
export const selectSearchSubmit = (state: RootState) =>
  state.goodsSearch.submit;
export const selectResultSetSize = (state: RootState) =>
  state.goodsSearch.resultSetSize;
export const selectSearchId = (state: RootState) => state.goodsSearch.searchId;  

/**
 * Fetch records and update search result
 * @param {string} searchRequestString template string from query
 * @param {number} pageSize page size
 * @param {number} page page index
 */
export const fetchGoodsSearchResultset = (
  searchRequestString: string,
  pageSize,
  page,
  listSortConfiguration?: Array<ColumnSortConfiguration>
): AppThunk => async (dispatch) => {
  try {
    let searchTemplate = queryString.parse(searchRequestString);
    if (searchTemplate.page) {
      delete searchTemplate.page;
    }
    if (searchTemplate.pageSize) {
      delete searchTemplate.pageSize;
    }
    let searchRequest: SearchRequest<GoodsSearchTemplate> = {
      template: searchTemplate,
      listSortConfiguration
    };

    dispatch(isLoading(true));
    dispatch(setSearchTemplate(JSON.parse(JSON.stringify(searchTemplate))));
    goodsSearchApi.postSearchRequest(searchRequest).then((searchId) => {
      goodsSearchApi.getResultSetSize(searchId).then((resultSize) => {
        dispatch(setResultSetSize(resultSize));
        if (resultSize > 0) {
          if (searchId) {
            goodsSearchApi
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

export const fetchSearchGoods = (searchId: string, pageSize: number, page: number) : AppThunk => async (dispatch) => {
  try {
    dispatch(isLoading(true));
    goodsSearchApi.search(searchId, pageSize, page).then((records) => {
      dispatch(searchSuccess(records));
      dispatch(isLoading(false));
    });
  } catch (err) {
    dispatch(isLoading(false));
    dispatch(searchError(err));
  }
};

export const postSearchGoods  = (searchRequestString: string): AppThunk => async (dispatch) => {
  try {
    let searchTemplate = queryString.parse(searchRequestString);
    if (searchTemplate.page) {
      delete searchTemplate.page;
    }
    if (searchTemplate.pageSize) {
      delete searchTemplate.pageSize;
    }
    let searchRequest: SearchRequest<GoodsSearchTemplate> = {
      template: searchTemplate,
    };

    dispatch(isLoading(true));
    dispatch(setSearchTemplate(JSON.parse(JSON.stringify(searchTemplate))));
    goodsSearchApi.postSearchRequest(searchRequest).then((searchId) => {
      goodsSearchApi.getResultSetSize(searchId).then((resultSize) => {
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


export default GoodsSearchSlice.reducer;
