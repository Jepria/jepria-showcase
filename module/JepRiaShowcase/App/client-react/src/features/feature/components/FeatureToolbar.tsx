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

const FeatureToolbar = ({ formRef }) => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  //----------------
  // const currentRecord: Feature = useSelector(selectFeature);
  // const records: Array<Feature> = useSelector(selectSearchResult);
  // const searchPage: number = useSelector(selectSearchPage);
  // const searchPageSize: number = useSelector(selectSearchPageSize);
  const { records } = useSelector((state: FeatureState) => state.feature.featureSearchSlice);
  const { currentRecord, error } = useSelector(
    (state: FeatureState) => state.feature.featureCrudSlice
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
        // onClick={() => {
        //   if (Workstates.Create === state) {
        //     dispatch(submitSaveOnCreate());
        //   } else if (Workstates.Edit == state) {
        //     dispatch(submitSaveOnEditFeature());
        //   }
        // }}
      />
      <ToolbarButtonEdit
        disabled={!currentRecord}
        onClick={() => {
          history.push(`/feature/${currentRecord?.featureId}/edit`);
        }}
      />
      <ToolbarButtonDelete disabled={!currentRecord} />
      <ToolbarButtonView
        disabled={!currentRecord || Workstates.Detail === state}
        onClick={() => history.push(`/feature/${currentRecord?.featureId}/detail`)}
      />
      <ToolbarSplitter />
      <ToolbarButtonBase
        disabled={Workstates.List !== state && records ? records.length === 0 : true}
        // onClick={() => history.push(`/feature/list/?pageSize=${searchPageSize}&page=${searchPage}`)}
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
          console.log(formRef);
        }}
        // onClick={() => {
        //   dispatch(submitSearch(true));
        // }}
      >
        {t("toolbar.find")}
      </ToolbarButtonBase>
    </Toolbar>
  );
};

export default FeatureToolbar;
