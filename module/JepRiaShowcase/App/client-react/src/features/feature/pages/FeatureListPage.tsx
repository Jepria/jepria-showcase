import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@jfront/ui-core";
import { Feature } from "../api/FeatureTypes";
import { setCurrentRecord } from "../featureSlice";
import { selectSearchResult, fetchFeatureSearchResultset, selectIsLoading, selectResultSetSize, selectSearchId, fetchSearchFeatures } from "../featureSearchSlice";
import { setState, Workstates } from "../../../app/WorkstateSlice";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const FeatureListPage = () => {
  //----------------
  const history = useHistory();
  const location = useLocation();
  let query = useQuery();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  const pageSize: number = parseInt(query.get("pageSize") as string);
  const page: number = parseInt(query.get("page") as string);
  const records: Array<Feature> = useSelector(selectSearchResult);
  const isLoading = useSelector(selectIsLoading);
  const resultSetSize = useSelector(selectResultSetSize);
  const searchId = useSelector(selectSearchId);

  useEffect(() => {
    dispatch(setState(Workstates.FeatureList));
    dispatch(fetchFeatureSearchResultset(location.search, pageSize, page));
  }, [location]);

  return (
    <>
        <Grid
          id="table"
          columns={[
            {
              Header: t("feature.fields.featureId"),
              accessor: "featureId",
            },
            {
              Header: t("feature.fields.featureStatus"),
              id: "featureStatus",
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
          isLoading={isLoading}
          data={records}
          totalRowCount={resultSetSize}
          onSelection={(selectedRecords) => {
            if (selectedRecords.length === 1) {
              dispatch(setCurrentRecord(selectedRecords[0]));
            } else {
              dispatch(setCurrentRecord(undefined));
            }
          }}
          onDoubleClick={(record) => {
            history.push(`/feature/${record.featureId}/detail`);
          }}
          onPaging={(pageNumber, pageSize) => {
            if (searchId) {
              console.log(`pageNumber = ${pageNumber}, pageSize = ${pageSize}`)
              dispatch(fetchSearchFeatures(searchId, pageSize, pageNumber));
            }
          }}
          onSort={(sortConfig) => {

            dispatch(fetchFeatureSearchResultset(location.search, pageSize, page, sortConfig));
            // if (searchRequest) {
            //   const newSearchRequest = {
            //     ...searchRequest,
            //     listSortConfiguration: sortConfig
            //   }
            //   dispatch(postSearchClientRequest(newSearchRequest, t("dataLoadingMessage")));
            // } else {
            //   if (pageSize && page) {
            //     dispatch(postSearchClientRequest({ template: searchTemplate as unknown as ClientSearchTemplate, listSortConfiguration: sortConfig }, t("dataLoadingMessage")));
            //   } else {
            //     dispatch(postSearchClientRequest({ template: { maxRowCount: 25 }, listSortConfiguration: sortConfig }, t("dataLoadingMessage")));
            //   }
            // }

          }}
        />
    </>
  );
};

export default FeatureListPage;
