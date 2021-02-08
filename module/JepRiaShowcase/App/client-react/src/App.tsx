import React, { Suspense, useContext, useEffect } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { UserPanel } from "@jfront/oauth-ui";
import { UserContext } from "@jfront/oauth-user";
import { Loader } from "@jfront/oauth-ui";
import FeatureModuleRoute from "./features/feature/FeatureModuleRoute";
import GoodsModuleRoute from "./features/goods/GoodsModuleRoute";

function Main() {
  const { i18n, t } = useTranslation();
  const language = new URLSearchParams(window.location.search).get("locale");
  const { currentUser, isUserLoading } = useContext(UserContext);

  useEffect(() => {
    if (language) {
      i18n.changeLanguage(language);
    }
  }, [language]);

  return (
    <>
      {currentUser.username !== "Guest" && !isUserLoading && (
        <BrowserRouter basename={`${process.env.PUBLIC_URL}`}>
          <Switch>
            <Route path="/feature">
              <UserPanel />
              <FeatureModuleRoute />
            </Route>
            <Route path="/goods">
              <UserPanel />
              <GoodsModuleRoute />
            </Route>
          </Switch>
        </BrowserRouter>
      )}
      {isUserLoading && <Loader title="JepRiaShowcase" text={t("loadingUser")} />}
    </>
  );
}

const App = () => {
  return (
    <Suspense fallback={<Loader title="JepRiaShowcase" text="Загрузка приложения..." />}>
      <Main />
    </Suspense>
  );
};

export default App;
