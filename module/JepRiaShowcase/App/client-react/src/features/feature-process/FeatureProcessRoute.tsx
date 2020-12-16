import React from "react";
import { Route, useRouteMatch, useHistory, useParams } from "react-router-dom";
import FeatureProcessListPage from "./pages/FeatureProcessListPage";
import FeatureProcessDetailPage from "./pages/FeatureProcessDetailPage";
import FeatureProcessCreatePage from "./pages/FeatureProcessCreatePage";
import FeatureProcessToolbar from "./components/FeatureProcessToolbar";
import { Panel, Tab, TabPanel } from "@jfront/ui-core";
import { useTranslation } from "react-i18next";

function FeatureProcessRoute() {
  const { featureId } = useParams();
  const { path } = useRouteMatch();
  const history = useHistory();
  const { t } = useTranslation();

  return (
    <Panel>
      <Panel.Header>
        <TabPanel>
          <Tab
            selected={false}
            onClick={() => {
              history.push(`/feature/${featureId}/detail`);
            }}
          >
            {t("feature.header")}
          </Tab>
          <Tab
            selected={true}
            onClick={() => {
              history.push(`/feature/${featureId}/feature-process/list`);
            }}
          >
            {t("feature-process.header")}
          </Tab>
        </TabPanel>
        <FeatureProcessToolbar />
      </Panel.Header>
      <Panel.Content>
        <Route path={`${path}/list`} exact>
          <FeatureProcessListPage />
        </Route>
        <Route path={`${path}/:featureProcessId/detail`}>
          <FeatureProcessDetailPage />
        </Route>
        <Route path={`${path}/create`} exact>
          <FeatureProcessCreatePage />
        </Route>
      </Panel.Content>
    </Panel>
  );
}

export default FeatureProcessRoute;
