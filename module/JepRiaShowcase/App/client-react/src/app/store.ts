import { configureStore, ThunkAction, Action } from "@reduxjs/toolkit";
import featureReducer from "../features/feature/featureSlice";
import featureSearchReducer from "../features/feature/featureSearchSlice";
import featureProcessReducer from "../features/feature-process/featureProcessSlice";

export const store = configureStore({
  reducer: {
    feature: featureReducer,
    featureSearch: featureSearchReducer,
    featureProcess: featureProcessReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppThunk = ThunkAction<void, RootState, unknown, Action<string>>;
