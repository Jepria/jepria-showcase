import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@jfront/ui-core";
import { Feature } from "../api/FeatureTypes";
import { setCurrentRecord } from "../featureSlice";
import { selectSearchResult, fetchSearchFeatures, selectIsLoading } from "../featureSearchSlice";
import { setState, Workstates } from "../../../app/WorkstateSlice";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const FeatureListPage = () => {
  const history = useHistory();
  const location = useLocation();
  let query = useQuery();
  const pageSize: number = parseInt(query.get("pageSize") as string);
  const page: number = parseInt(query.get("page") as string);
  const { t } = useTranslation();
  const dispatch = useDispatch();
  const records: Array<Feature> = useSelector(selectSearchResult);
  const isLoading = useSelector(selectIsLoading);

  const find = () => {
    dispatch(fetchSearchFeatures(location.search, pageSize, page));
  };

  useEffect(() => {
    dispatch(setState(Workstates.FeatureList));
    find();
  }, [location]);

  return (
    <>
      {isLoading ? (
        <div style={{ textAlign: "center" }}>Loading...</div>
      ) : (
        <Grid
          id="table"
          columns={[
            {
              Header: t("feature.fields.featureId"),
              accessor: "featureId",
            },
            {
              Header: t("feature.fields.featureStatus"),
              accessor: "featureStatus.name",
            },
            {
              Header: t("feature.fields.workSequence"),
              accessor: "workSequence",
            },
            {
              Header: t("feature.fields.featureName"),
              accessor: "featureName",
            },
            {
              Header: t("feature.fields.featureNameEn"),
              accessor: "featureNameEn",
            },
            {
              Header: t("feature.fields.description"),
              accessor: "description",
            },
            {
              Header: t("feature.fields.dateIns"),
              accessor: "dateIns",
            },
            {
              Header: t("feature.fields.author"),
              accessor: "author.name",
            },
            {
              Header: t("feature.fields.responsible"),
              accessor: "responsible.name",
            },
          ]}
          data={records}
          onSelection={(selectedFeatures) => {
            console.log(selectedFeatures);
            if (selectedFeatures.length === 1) {
              dispatch(setCurrentRecord(selectedFeatures[0]));
            } else {
              dispatch(setCurrentRecord(undefined));
            }
          }}
          onDoubleClick={(feature) => {
            history.push(`/feature/${feature.featureId}/detail`);
          }}
        />
      )}
    </>
  );
};

export default FeatureListPage;
