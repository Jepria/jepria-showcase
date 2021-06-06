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
import { Feature } from "../api/FeatureTypes";
import { Workstates, useWorkstate } from "../../../app/common/useWorkstate";
import { deleteRecord } from "../state/FeatureSlice";
import { RootState, useAppDispatch } from "../../../app/store";
import { search } from "../state/FeatureSearchSlice";
import { createEvent } from "@jfront/core-common";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const FeatureToolbar = ({ formRef }) => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const { pathname } = useLocation();
  const dispatch = useAppDispatch();
  //----------------

  const { records, pageSize, pageNumber, searchRequest } = useSelector(
    (state: RootState) => state.feature.featureSearchSlice
  );
  const { currentRecord, selectedRecords } = useSelector(
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
          formRef.current?.dispatchEvent(createEvent("submit"));
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
            deleteRecord(selectedRecords.map((selectRecord: Feature) => selectRecord.featureId))
          ).then(() => {
            if (pathname.endsWith("/list") && searchRequest) {
              dispatch(search(searchRequest, pageSize, pageNumber));
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
          () => history.push(`/feature/list/?pageSize=${pageSize}&page=${pageNumber}&maxRowCount=25`) // TODO full template
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
          formRef.current?.dispatchEvent(createEvent("submit"));
        }}
      >
        {t("toolbar.find")}
      </ToolbarButtonBase>
    </Toolbar>
  );
};

export default FeatureToolbar;
