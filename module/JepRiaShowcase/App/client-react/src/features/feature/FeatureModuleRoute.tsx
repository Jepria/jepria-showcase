import React from "react";
import { Route, useRouteMatch } from "react-router-dom";
import FeatureRoute from "./FeatureRoutes";
import FeatureProcessRoute from "../feature-process/FeatureProcessRoute";

function FeatureModuleRoute() {
  const { path } = useRouteMatch();

  return (
    <>
      <Route path={`${path}/:featureId/feature-process`}>
        <FeatureProcessRoute />
      </Route>
      <Route path={`${path}`}>
        <FeatureRoute />
      </Route>
    </>
  );
}

export default FeatureModuleRoute;
