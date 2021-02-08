import React, { useRef } from "react";
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
import { Goods } from "./api/GoodsTypes";
import { RootState } from "../../app/store";

function GoodsRoute() {
  const { path } = useRouteMatch();
  const history = useHistory();
  const { t } = useTranslation();
  let formRef = useRef<HTMLFormElement>(null);
  // const currenRrecord: Goods = useSelector(selectCurrentRecord);
  const { currentRecord } = useSelector((state: RootState) => state.goods.goodsCrudSlice);

  return (
    <Panel>
      <Panel.Header>
        <TabPanel>
          <Tab
            selected={true}
            onClick={() => {
              history.push(`/${path}/${currentRecord?.goodsId}/detail`);
            }}
          >
            {t("feature.header")}
          </Tab>
          {currentRecord?.goodsId ? (
            <Tab
              selected={false}
              onClick={() => {
                history.push(`/${path}/${currentRecord?.goodsId}/feature-process/list`);
              }}
            >
              {t("feature-process.header")}
            </Tab>
          ) : null}
        </TabPanel>
        <GoodsToolbar formRef={formRef} />
      </Panel.Header>
      <Panel.Content>
        <Route path={`${path}`} exact>
          <GoodsSearchPage formRef={formRef} />
        </Route>
        <Route path={`${path}/create`} exact>
          <GoodsCreatePage formRef={formRef} />
        </Route>
        <Route path={`${path}/:featureId/edit`} exact>
          <GoodsEditPage formRef={formRef} />
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
