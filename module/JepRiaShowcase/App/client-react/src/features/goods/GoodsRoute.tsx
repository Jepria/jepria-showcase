import React from "react";
import { useSelector } from "react-redux";
import { Route, useRouteMatch, useHistory, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { Panel, Tab, TabPanel } from "@jfront/ui-core";
import GoodsDetailPage from "./pages/GoodsDetailPage";
import GoodsCreatePage from "./pages/GoodsCreatePage";
import GoodsSearchPage from "./pages/GoodsSearchPage";
import GoodsListPage from "./pages/GoodsListPage";
import GoodsEditPage from "./pages/GoodsEditPage";
import GoodsToolbar from "./components/GoodsToolbar";
import { selectCurrentRecord } from "./GoodsSlice";
import { Goods } from "./api/GoodsTypes";

function GoodsRoute() {
  const { path } = useRouteMatch();
  const history = useHistory();
  const { t } = useTranslation();
  const currenRrecord: Goods = useSelector(selectCurrentRecord);

  return (
    <Panel>
      <Panel.Header>
        <TabPanel>
          <Tab
            selected={true}
            onClick={() => {
              history.push(`/${path}/${currenRrecord?.goodsId}/detail`);
            }}
          >
            {t("feature.header")}
          </Tab>
          {currenRrecord?.goodsId ? (
            <Tab
              selected={false}
              onClick={() => {
                history.push(`/${path}/${currenRrecord?.goodsId}/feature-process/list`);
              }}
            >
              {t("feature-process.header")}
            </Tab>
          ) : null}
        </TabPanel>
        <GoodsToolbar />
      </Panel.Header>
      <Panel.Content>
        <Route path={`${path}`} exact>
          <GoodsSearchPage />
        </Route>
        <Route path={`${path}/create`} exact>
          <GoodsCreatePage />
        </Route>
        <Route path={`${path}/:featureId/edit`} exact>
          <GoodsEditPage />
        </Route>
        <Route path={`${path}/:featureId/detail`}>
          <GoodsDetailPage />
        </Route>
        <Route path={`${path}/list`}>
          <GoodsListPage />
        </Route>
      </Panel.Content>
    </Panel>
  );
}

export default GoodsRoute;
