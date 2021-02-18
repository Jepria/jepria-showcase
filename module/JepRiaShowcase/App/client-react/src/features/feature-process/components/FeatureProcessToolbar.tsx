import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { useTranslation } from "react-i18next";
import { useHistory, useParams } from "react-router-dom";
import {
  Toolbar,
  ToolbarButtonBase,
  ToolbarButtonCreate,
  ToolbarButtonDelete,
  ToolbarButtonSave,
  ToolbarButtonView,
  ToolbarSplitter,
} from "@jfront/ui-core";
import { selectFeatureProcess, submitSaveOnCreateFeatureProcess } from "../featureProcessSlice";
import { deleteFeatureProcess } from "../api/FeatureProcessApi";
import { useWorkstate, Workstates } from "../../../app/common/useWorkstate";

const FeatureProcessToolbar = () => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  const { featureId } = useParams<any>();
  //----------------
  const state: Workstates = useWorkstate(history.location.pathname);
  const currentFeatureProcess = useSelector(selectFeatureProcess);

  return (
    <Toolbar>
      <ToolbarButtonCreate
        disabled={Workstates.Create === state}
        onClick={() => history.push(`/feature/${featureId}/feature-process/create`)}
      />
      <ToolbarButtonSave
        disabled={Workstates.Create !== state}
        onClick={() => {
          if (Workstates.Create === state) {
            dispatch(submitSaveOnCreateFeatureProcess());
          }
        }}
      />
      <ToolbarButtonDelete
        disabled={!currentFeatureProcess}
        onClick={() => {
          if (currentFeatureProcess.featureId && currentFeatureProcess.featureProcessId) {
            deleteFeatureProcess(
              parseInt(currentFeatureProcess.featureId.toString()),
              parseInt(currentFeatureProcess.featureProcessId as any)
            ).then(() => {
              history.push(`/feature/${featureId}/feature-process/list`);
            });
          }
        }}
      />
      <ToolbarButtonView
        disabled={!currentFeatureProcess || Workstates.Detail === state}
        onClick={() =>
          history.push(
            `/feature/${currentFeatureProcess.featureId}/feature-process/${currentFeatureProcess?.featureProcessId}/detail`
          )
        }
      />
      <ToolbarSplitter />
      <ToolbarButtonBase
        disabled={Workstates.List === state}
        onClick={() => {
          history.push(`/feature/${currentFeatureProcess.featureId}/feature-process/list`);
        }}
      >
        {t("toolbar.list")}
      </ToolbarButtonBase>
    </Toolbar>
  );
};

export default FeatureProcessToolbar;
