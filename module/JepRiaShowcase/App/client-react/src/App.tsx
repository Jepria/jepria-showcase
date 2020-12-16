import React, { Suspense, useEffect } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { useTranslation } from "react-i18next";
import FeatureModuleRoute from "./features/feature/FeatureModuleRoute";

const Loader = () => (
  <div>
    <div>Loading...</div>
  </div>
);

function Main() {
  const { i18n } = useTranslation();
  const language = new URLSearchParams(window.location.search).get("locale");

  useEffect(() => {
    if (language) {
      i18n.changeLanguage(language);
    }
  }, [language]);

  return (
    <BrowserRouter basename={`${process.env.PUBLIC_URL}`}>
      <Switch>
        <Route path="/feature">
          <FeatureModuleRoute />
        </Route>
      </Switch>
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
