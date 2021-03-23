import React, {useContext, useEffect, useState} from "react";
import { Route, useRouteMatch } from "react-router-dom";
import {useTranslation} from "react-i18next";
import {Forbidden, Loader} from '@jfront/oauth-ui'
import { UserContext } from '@jfront/oauth-user'
import FeatureRoute from "./FeatureRoute";
import FeatureProcessRoute from "../feature-process/FeatureProcessRoute";



function FeatureModuleRoute() {
  const { t } = useTranslation();
  const { path } = useRouteMatch();
  const { isRoleLoading, isUserInRoles } = useContext(UserContext);
  const [hasAccess, setHasAccess] = useState(false);

  useEffect(() => {
    isUserInRoles(["JrsEditFeature", "JrsEditAllFeature", "JrsAssignResponsibleFeature"])
      .then(setHasAccess)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <>
      {isRoleLoading && <Loader title="JepRiaShowcase" text={t("loadingRoles")} />}
      {!isRoleLoading && !hasAccess && <Forbidden />}
      {!isRoleLoading && hasAccess && (
        <>
          <Route path={`${path}/:featureId/feature-process`}>
            <FeatureProcessRoute/>
          </Route>
          <Route path={`${path}`}>
            <FeatureRoute />
          </Route>
        </>)}
    </>
  );
}

export default FeatureModuleRoute;
