import { Feature, FeatureSearchTemplate } from "./../features/feature/api/FeatureTypes";
import { EntityState, OptionState, SearchState } from "@jfront/core-redux-thunk";
import { combineReducers, Reducer } from "@reduxjs/toolkit";
import { initialEntityState, reducer as crudReducer } from "../features/feature/state/featureSlice";
import {
  initialSearchState,
  reducer as searchReducer,
} from "../features/feature/featureSearchSlice";

export interface FeatureState {
  feature: {
    featureSearchSlice: SearchState<FeatureSearchTemplate, Feature>;
    featureCrudSlice: EntityState<Feature>;
    // featureOptionsSlice: OptionState<string>;
  };
}

export const initialState: FeatureState = {
  feature: {
    featureSearchSlice: initialSearchState,
    featureCrudSlice: initialEntityState,
    // featureOptionsSlice: initialOptionsState,
  },
};

export const featureReducer = combineReducers({
  featureSearchSlice: searchReducer,
  featureCrudSlice: crudReducer,
  // featureOptionsSlice: optionsReducer,
});
