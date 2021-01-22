import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
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
// import { selectFeature, submitSaveOnCreate, submitSaveOnEditFeature } from "../state/featureSlice";
// import {
//   selectSearchPage,
//   selectSearchPageSize,
//   selectSearchResult,
//   submitSearch,
// } from "../featureSearchSlice";
import { Feature } from "../api/FeatureTypes";
import { Workstates, useWorkstate } from "../../../app/common/useWorkstate";
import { FeatureState } from "../../../app/reducer";
import { deleteRecord } from "../state/featureSlice";
import { RootState, useAppDispatch } from "../../../app/store";
import { search } from "../featureSearchSlice";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const FeatureToolbar = ({ formRef }) => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const { pathname } = useLocation();
  const dispatch = useAppDispatch();
  let query = useQuery();
  //----------------

  const { records, searchId, pageSize, pageNumber, } = useSelector((state: RootState) => state.feature.featureSearchSlice);
  const { currentRecord, selectedRecords, error } = useSelector(
    (state: RootState) => state.feature.featureCrudSlice
  );
  const state = useWorkstate(history.location.pathname);

  return (
    <Toolbar>
      <ToolbarButtonCreate
        disabled={state === Workstates.Detail}
        onClick={() => history.push(`/feature/create`)}
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
          history.push(`/feature/${currentRecord?.featureId}/edit`);
        }}
      />
      <ToolbarButtonDelete
        disabled={!currentRecord}
        onClick={() => {
          dispatch(
            deleteRecord({
              primaryKeys: selectedRecords.map((selectRecord: Feature) => selectRecord.featureId),
            })
          ).then(() => {
            if (pathname.endsWith("/list") && searchId) {
              dispatch(search({ searchId, pageSize: pageSize, pageNumber: pageNumber }));
            } else {
              history.push(`/feature/list?pageSize=${pageSize}&page=${pageNumber}`);
            }
          });
        }}
      />
      <ToolbarButtonView
        disabled={!currentRecord || Workstates.Detail === state}
        onClick={() => history.push(`/feature/${currentRecord?.featureId}/detail`)}
      />
      <ToolbarSplitter />
      <ToolbarButtonBase
        disabled={Workstates.List !== state && records ? records.length === 0 : true}
        onClick={
          () => history.push(`/feature/list/?pageSize=${pageSize}&page=${pageNumber}`) // TODO full template
        }
      >
        {t("toolbar.list")}
      </ToolbarButtonBase>
      <ToolbarButtonFind
        disabled={state === Workstates.Search}
        onClick={() => history.push(`/feature`)}
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

export default FeatureToolbar;
