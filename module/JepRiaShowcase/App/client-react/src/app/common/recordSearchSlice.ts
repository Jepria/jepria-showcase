import { createSlice, SliceCaseReducers, ValidateSliceCaseReducers } from "@reduxjs/toolkit";

export interface RecordState<RECORD = any, TEMPLATE = any> {
  searchTemplate?: TEMPLATE;
  error?: string;
  isLoading?: boolean;
  searchResult?: Array<RECORD>;
  pageSize?: number;
  page?: number;
  submit?: boolean;
}

export const createGenericSlice = <
  RECORD,
  TEMPLATE,
  Reducers extends SliceCaseReducers<RecordState<RECORD, TEMPLATE>>
>({
  name = "",
  initialState,
  reducers,
}: {
  name: string;
  initialState: RecordState<RECORD, TEMPLATE>;
  reducers: ValidateSliceCaseReducers<RecordState<RECORD, TEMPLATE>, Reducers>;
}) => {
  return createSlice({
    name,
    initialState,
    reducers: {
      setSearchTemplate(state, action) {
        state.searchTemplate = action.payload;
      },
      searchError(state, action) {
        state.error = action.payload;
        state.searchTemplate = null;
        state.searchResult = [];
      },
      isLoading(state, action) {
        state.isLoading = action.payload;
      },
      searchSuccess(state, action) {
        state.searchResult = action.payload;
      },
      submitSearch(state, action) {
        state.submit = action.payload;
      },
      ...reducers,
    },
  });
};
