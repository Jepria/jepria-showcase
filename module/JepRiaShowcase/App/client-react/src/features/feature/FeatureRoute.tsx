import React from "react";
import { useSelector } from "react-redux";
import { Route, useRouteMatch, useHistory, useParams } from "react-router-dom";
import FeatureDetailPage from "./pages/FeatureDetailPage";
import FeatureCreatePage from "./pages/FeatureCreatePage";
import FeatureSearchPage from "./pages/FeatureSearchPage";
import FeatureListPage from "./pages/FeatureListPage";
import FeatureEditPage from "./pages/FeatureEditPage";
import FeatureToolbar from "./components/FeatureToolbar";
import { Panel, Tab, TabPanel } from "@jfront/ui-core";
import { useTranslation } from "react-i18next";
import { selectFeature } from "./featureSlice";
import { Feature } from "./api/FeatureTypes";

function FeatureRoute() {
  const { path } = useRouteMatch();
  const history = useHistory();
  const { t } = useTranslation();
  const currentFeature: Feature = useSelector(selectFeature);

  return (
    <Panel>
      <Panel.Header>
        <TabPanel>
          <Tab
            selected={true}
            onClick={() => {
              history.push(`/feature/${currentFeature?.featureId}/detail`);
            }}
          >
            {t("feature.header")}
          </Tab>
          {currentFeature?.featureId ? (
            <Tab
              selected={false}
              onClick={() => {
                history.push(`/feature/${currentFeature?.featureId}/feature-process/list`);
              }}
            >
              {t("feature-process.header")}
            </Tab>
          ) : null}
        </TabPanel>
        <FeatureToolbar />
      </Panel.Header>
      <Panel.Content>
        <Route path={`${path}`} exact>
          <FeatureSearchPage />
        </Route>
        <Route path={`${path}/create`} exact>
          <FeatureCreatePage />
        </Route>
        <Route path={`${path}/:featureId/edit`} exact>
          <FeatureEditPage />
        </Route>
        <Route path={`${path}/:featureId/detail`}>
          <FeatureDetailPage />
        </Route>
        <Route path={`${path}/list`}>
          <FeatureListPage />
        </Route>
      </Panel.Content>
    </Panel>
  );
}

export default FeatureRoute;
