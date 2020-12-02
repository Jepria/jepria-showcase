import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
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
import { selectFeature, submitSaveOnCreate, submitSaveOnEditFeature } from "../featureSlice";
import {
  selectSearchPage,
  selectSearchPageSize,
  selectSearchResult,
  submitSearch,
} from "../featureSearchSlice";
import { Feature } from "../api/FeatureTypes";
import { selectState, Workstates } from "../../../app/WorkstateSlice";

const FeatureToolbar = () => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  //----------------
  const state: Workstates = useSelector(selectState);
  const currentRecord: Feature = useSelector(selectFeature);
  const records: Array<Feature> = useSelector(selectSearchResult);
  const searchPage: number = useSelector(selectSearchPage);
  const searchPageSize: number = useSelector(selectSearchPageSize);

  return (
    <Toolbar>
      <ToolbarButtonCreate
        disabled={state === Workstates.FeatureCreate}
        onClick={() => history.push(`/feature/create`)}
      />
      <ToolbarButtonSave
        disabled={Workstates.FeatureCreate !== state && Workstates.FeatureEdit !== state}
        onClick={() => {
          if (Workstates.FeatureCreate === state) {
            dispatch(submitSaveOnCreate());
          } else if (Workstates.FeatureEdit == state) {
            dispatch(submitSaveOnEditFeature());
          }
        }}
      />
      <ToolbarButtonEdit
        disabled={!currentRecord}
        onClick={() => {
          history.push(`/feature/${currentRecord?.featureId}/edit`);
        }}
      />
      <ToolbarButtonDelete disabled={!currentRecord} />
      <ToolbarButtonView
        disabled={!currentRecord || Workstates.FeatureDetail === state}
        onClick={() => history.push(`/feature/${currentRecord?.featureId}/detail`)}
      />
      <ToolbarSplitter />
      <ToolbarButtonBase
        disabled={Workstates.FeatureList !== state && records ? records.length === 0 : true}
        onClick={() => history.push(`/feature/list/?pageSize=${searchPageSize}&page=${searchPage}`)}
      >
        {t("toolbar.list")}
      </ToolbarButtonBase>
      <ToolbarButtonFind
        disabled={state === Workstates.FeatureSearch}
        onClick={() => history.push(`/feature`)}
      />
      <ToolbarButtonBase
        disabled={state !== Workstates.FeatureSearch}
        type="submit"
        onClick={() => {
          dispatch(submitSearch(true));
        }}
      >
        {t("toolbar.find")}
      </ToolbarButtonBase>
    </Toolbar>
  );
};

export default FeatureToolbar;
