import { useDispatch } from "react-redux";
import { Action, configureStore, getDefaultMiddleware, ThunkAction } from "@reduxjs/toolkit";
import logger from "redux-logger";
import { initialState as FeatureInitialState, featureReducer } from "./reducer";
import featureProcessReducer from "../features/feature-process/featureProcessSlice";

export const store = configureStore({
  reducer: {
    feature: featureReducer,
    featureProcess: featureProcessReducer,
  },
  middleware: [...getDefaultMiddleware().concat(logger)],
  preloadedState: FeatureInitialState,
  devTools: process.env.NODE_ENV === "development",
});

export type AppDispatch = typeof store.dispatch;
export const useAppDispatch = () => useDispatch<AppDispatch>();

export type RootState = ReturnType<typeof store.getState>;
export type AppThunk = ThunkAction<void, RootState, unknown, Action<string>>;
