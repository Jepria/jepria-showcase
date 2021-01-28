import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@jfront/ui-core";
import { Feature } from "../api/FeatureTypes";
import { postSearch, postSearchRequest, search } from "../state/FeatureSearchSlice";
import { actions as crudActions } from "../state/FeatureSlice";
import { RootState } from "../../../app/store";

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
  const [page, setPage] = useState({
    pageSize: parseInt(query.get("pageSize") as string),
    pageNumber: parseInt(query.get("page") as string),
  });
  console.log(`page: ${page.pageNumber}`)
  const { currentRecord } = useSelector((state: RootState) => state.feature.featureCrudSlice);

  const { records, searchId, searchRequest, isLoading, resultSetSize } = useSelector(
    (state: RootState) => state.feature.featureSearchSlice
  );

  useEffect(() => {
    if (searchId) {
      dispatch(
        search({ searchId: searchId, pageSize: page.pageSize, pageNumber: page.pageNumber })
      );
    }
  }, [searchId, page, dispatch]);

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
        defaultPageSize={page.pageSize}
        defaultPageNumber={page.pageNumber}
        onSelection={(records) => {
          if (records) {
            if (records.length === 1) {
              if (records[0] !== currentRecord) {
                dispatch(crudActions.setCurrentRecord({ currentRecord: records[0] }));
                dispatch(crudActions.selectRecords({ selectedRecords: records }));
              }
            } else if (currentRecord) {
              dispatch(crudActions.setCurrentRecord({} as any));
              dispatch(crudActions.selectRecords({ selectedRecords: records }));
            }
          }
        }}
        onDoubleClick={(record: Feature) => {
          history.push(`/feature/${record.featureId}/detail`);
        }}
        onPaging={(pageNumber, pageSize) => {
          setPage({
            pageNumber: pageNumber,
            pageSize: pageSize,
          });
        }}
        onSort={(sortConfig) => {
          console.log("onSort!!!!!!!!!!")
          const newSearchRequest = {
            template: {
              maxRowCount: 25,
              ...query,
              ...searchRequest?.template,
            },
            listSortConfiguration: sortConfig,
          };
          dispatch(
            postSearchRequest({
              searchTemplate: newSearchRequest,
            })
          );
        }}
      />

    </>
  );
};

export default FeatureListPage;
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

  useEffect(() => {
    dispatch(setState(Workstates.FeatureList));
    dispatch(fetchSearchFeatures(location.search, pageSize, page));
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
            if (selectedFeatures.length === 1) {
              dispatch(setCurrentRecord(selectedFeatures[0]));
            } else {
              dispatch(setCurrentRecord(undefined));
            }
          }}
          onDoubleClick={(feature) => {
            history.push(`/feature/${feature.featureId}/detail`);
          }}
          isLoading={isLoading}
        />
    </>
  );
};

export default FeatureListPage;
