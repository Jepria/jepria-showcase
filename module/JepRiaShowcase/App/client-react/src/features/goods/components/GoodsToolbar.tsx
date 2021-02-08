import React from "react";
import { useSelector } from "react-redux";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import {
  Toolbar,
  ToolbarButtonBase,
  ToolbarButtonCreate,
  ToolbarButtonDelete,
  ToolbarButtonEdit,
  ToolbarButtonFind,
  ToolbarButtonSave,
  ToolbarButtonView,
  ToolbarSplitter,
} from "@jfront/ui-core";
import { Goods } from "../api/GoodsTypes";
import { Workstates, useWorkstate } from "../../../app/common/useWorkstate";
import { deleteRecord } from "../state/GoodsCrudSlice";
import { RootState, useAppDispatch } from "../../../app/store";
import { search } from "../state/GoodsSearchSlice";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const GoodsToolbar = ({ formRef }) => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const { pathname } = useLocation();
  const dispatch = useAppDispatch();
  //----------------

  const { records, searchId, pageSize, pageNumber, } = useSelector((state: RootState) => state.goods.goodsSearchSlice);
  const { currentRecord, selectedRecords } = useSelector(
    (state: RootState) => state.goods.goodsCrudSlice
  );
  const state = useWorkstate(history.location.pathname);

  return (
    <Toolbar>
      <ToolbarButtonCreate
        disabled={state === Workstates.Detail}
        onClick={() => history.push(`/goods/create`)}
      />
      <ToolbarButtonSave
        disabled={Workstates.Create !== state && Workstates.Edit !== state}
        onClick={() => {
          formRef.current?.dispatchEvent(new Event("submit"));
        }}
      />
      <ToolbarButtonEdit
        disabled={!currentRecord}
        onClick={() => {
          history.push(`/goods/${currentRecord?.goodsId}/edit`);
        }}
      />
      <ToolbarButtonDelete
        disabled={!currentRecord}
        onClick={() => {
          dispatch(
            deleteRecord({
              primaryKeys: selectedRecords.map((selectRecord: Goods) => selectRecord.goodsId),
            })
          ).then(() => {
            if (pathname.endsWith("/list") && searchId) {
              dispatch(search({ searchId, pageSize: pageSize, pageNumber: pageNumber }));
            } else {
              history.push(`/goods/list?pageSize=${pageSize}&page=${pageNumber}`);
            }
          });
        }}
      />
      <ToolbarButtonView
        disabled={!currentRecord || Workstates.Detail === state}
        onClick={() => history.push(`/goods/${currentRecord?.goodsId}/detail`)}
      />
      <ToolbarSplitter />
      <ToolbarButtonBase
        disabled={Workstates.List !== state && records ? records.length === 0 : true}
        onClick={
          () => history.push(`/goods/list/?pageSize=${pageSize}&page=${pageNumber}`) // TODO full template
        }
      >
        {t("toolbar.list")}
      </ToolbarButtonBase>
      <ToolbarButtonFind
        disabled={state === Workstates.Search}
        onClick={() => history.push(`/goods`)}
      />
      <ToolbarButtonBase
        disabled={state !== Workstates.Search}
        type="submit"
        onClick={() => {
          formRef.current?.dispatchEvent(new Event("submit"));
        }}
      >
        {t("toolbar.find")}
      </ToolbarButtonBase>
    </Toolbar>
  );
};

export default GoodsToolbar;
