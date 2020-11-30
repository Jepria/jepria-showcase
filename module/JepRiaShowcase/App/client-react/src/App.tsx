import React, { Suspense, useEffect } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { Panel } from "@jfront/ui-core";
import FeatureDetailPage from "./features/feature/pages/Detail";
import FeatureCreatePage from "./features/feature/pages/Create";
import FeatureSearchPage from "./features/feature/pages/Search";
import FeatureListPage from "./features/feature/pages/List";
import FeatureEditPage from "./features/feature/pages/Edit";
import FeatureProcessListPage from "./features/feature-process/pages/List";
import FeatureProcessDetailPage from "./features/feature-process/pages/Detail";
import FeatureProcessCreatePage from "./features/feature-process/pages/Create";
import AppTabPanel from "./features/tabpanel/AppTabPanel";
import FeatureToolbar from "./features/feature/components/FeatureToolbar";
import FeatureProcessToolbar from "./features/feature-process/components/FeatureProcessToolbar";

const Loader = () => (
  <div>
    <div>Loading...</div>
  </div>
);

function Main() {
  const { t, i18n } = useTranslation();
  const language = new URLSearchParams(window.location.search).get("locale");

  useEffect(() => {
    if (language) {
      i18n.changeLanguage(language);
    }
  }, [language]);

  return (
    <BrowserRouter basename={`${process.env.PUBLIC_URL}`}>
      <Panel>
        <Panel.Header>
          <AppTabPanel />
        </Panel.Header>
        <Switch>
          <Route path="/" exact>
            <FeatureToolbar />
            <FeatureSearchPage />
          </Route>
          <Route path="/create" exact>
            <FeatureToolbar />
            <FeatureCreatePage />
          </Route>
          <Route path="/:featureId/edit" exact>
            <FeatureToolbar />
            <FeatureEditPage />
          </Route>
          <Route path="/:featureId/detail">
            <FeatureToolbar />
            <FeatureDetailPage />
          </Route>
          <Route path="/list">
            <FeatureToolbar />
            <FeatureListPage />
          </Route>
          <Route path="/:featureId/feature-process" exact>
            <FeatureProcessToolbar />
            <FeatureProcessListPage />
          </Route>
          <Route path="/:featureId/feature-process/:featureProcessId/detail">
            <FeatureProcessToolbar />
            <FeatureProcessDetailPage />
          </Route>
          <Route path="/:featureId/feature-process/create" exact>
            <FeatureProcessToolbar />
            <FeatureProcessCreatePage />
          </Route>
        </Switch>
      </Panel>
    </BrowserRouter>
  );
}

const App = () => {
  return (
    <Suspense fallback={<Loader />}>
      <Main />
    </Suspense>
  );
};

export default App;
