import React from "react";
import { Route, useRouteMatch } from "react-router-dom";
import GoodsRoute from "./GoodsRoute";

function GoodsModuleRoute() {
  const { path } = useRouteMatch();

  return (
    <>
      <Route path={`${path}`}>
        <GoodsRoute />
      </Route>
    </>
  );
}

export default GoodsModuleRoute;
