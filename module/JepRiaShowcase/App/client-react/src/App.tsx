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
        <Switch>
          <Route path="/feature" exact>
            <AppTabPanel />
            <FeatureToolbar />
            <FeatureSearchPage />
          </Route>
          <Route path="/feature/create" exact>
            <AppTabPanel />
            <FeatureToolbar />
            <FeatureCreatePage />
          </Route>
          <Route path="/feature/:featureId/edit" exact>
            <AppTabPanel />
            <FeatureToolbar />
            <FeatureEditPage />
          </Route>
          <Route path="/feature/:featureId/detail">
            <AppTabPanel />
            <FeatureToolbar />
            <FeatureDetailPage />
          </Route>
          <Route path="/feature/list">
            <AppTabPanel />
            <FeatureToolbar />
            <FeatureListPage />
          </Route>
          <Route path="/feature/:featureId/feature-process" exact>
            <AppTabPanel />
            <FeatureProcessToolbar />
            <FeatureProcessListPage />
          </Route>
          <Route path="/feature/:featureId/feature-process/:featureProcessId/detail">
            <AppTabPanel />
            <FeatureProcessToolbar />
            <FeatureProcessDetailPage />
          </Route>
          <Route path="/feature/:featureId/feature-process/create" exact>
            <AppTabPanel />
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
