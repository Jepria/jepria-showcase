import { createSearchSlice, SearchState } from "@jfront/core-redux-thunk";
import { Goods, GoodsSearchTemplate } from "../api/GoodsTypes";
import { goodsSearchApi } from "../api/GoodsSearchApi";

export const initialSearchState: SearchState<GoodsSearchTemplate, Goods> = {
  searchRequest: { template: { maxRowCount: 250 } },
  error: null,
  isLoading: false,
  records: [],
  searchId: null,
  pageSize: 25,
  pageNumber: 1,
  resultSetSize: null,
};

export const slice = createSearchSlice({
  name: "goodsSearch",
  initialState: initialSearchState,
  reducers: {},
});

const thunkCreators = slice.thunk;

export const search = thunkCreators.searchThunk(goodsSearchApi);
export const postSearch = thunkCreators.postSearchThunk(goodsSearchApi);
export const postSearchRequest = thunkCreators.postSearchRequestThunk(goodsSearchApi);

export const { name, actions, reducer } = slice;
