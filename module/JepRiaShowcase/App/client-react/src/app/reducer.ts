import { Feature, FeatureSearchTemplate } from "./../features/feature/api/FeatureTypes";
import { EntityState, OptionState, SearchState } from "@jfront/core-redux-thunk";
import { combineReducers, Reducer } from "@reduxjs/toolkit";
import { initialEntityState, reducer as crudReducer } from "../features/feature/state/featureSlice";
import {
  initialSearchState,
  reducer as searchReducer,
} from "../features/feature/featureSearchSlice";
// import { FeatureProcess } from "../features/feature-process/api/FeatureProcessTypes";
// import { initialOptionsState, reducer as optionsReducer } from "../../list/state/listOptionsSlice";

export interface FeatureState {
  feature: {
    featureSearchSlice: SearchState<FeatureSearchTemplate, Feature>;
    featureCrudSlice: EntityState<Feature>;
    // featureOptionsSlice: OptionState<string>;
  };
  // featureProcess: {
  //   featureProcessCrud: EntityState<FeatureProcess>;
  // }
}

export const initialState: FeatureState = {
  feature: {
    featureSearchSlice: initialSearchState,
    featureCrudSlice: initialEntityState,
    // featureOptionsSlice: initialOptionsState,
  },
  // featureProcess: {
  //   featureProcessCrud: initialFeatureProcessState,
  // }
};

export const featureReducer = combineReducers({
  featureSearchSlice: searchReducer,
  featureCrudSlice: crudReducer,
  // featureOptionsSlice: optionsReducer,
});

// export const reducer: Reducer<FeatureState> = combineReducers<FeatureState>({
//   feature: featureReducer,
// });
