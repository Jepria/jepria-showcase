import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@jfront/ui-core";
import { Feature } from "../api/FeatureTypes";
import { actions as searchActions, search } from "../state/FeatureSearchSlice";
import { actions as crudActions } from "../state/FeatureSlice";
import { RootState } from "../../../app/store";
import queryString from "query-string";

const useQuery = () => {
  return queryString.parse(window.location.search, { arrayFormat: "bracket" });
};

const FeatureListPage = () => {
  //----------------
  const history = useHistory();
  let query = useQuery();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------
  const [page, setPage] = useState({
    pageSize: query?.pageSize ? parseInt(query.pageSize as string) : 25,
    pageNumber: query?.page ? parseInt(query.page as string) : 1,
  });

  const { currentRecord } = useSelector((state: RootState) => state.feature.featureCrudSlice);

  const { records, searchRequest, isLoading, resultSetSize } = useSelector(
    (state: RootState) => state.feature.featureSearchSlice
  );

  useEffect(() => {
    if (searchRequest) {
      dispatch(search(searchRequest, page.pageSize, page.pageNumber));
    }
  }, [page, dispatch]);

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
            accessor: "featureStatus.name" as any,
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
        manualPaging
        manualSort
        fetchData={(pageNumber, pageSize, sortConfig) => {
          const newSearchRequest = {
            template: {
              maxRowCount: 25,
              ...query,
              ...searchRequest?.template,
            },
            listSortConfiguration: sortConfig,
          };
          dispatch(search(newSearchRequest, page.pageSize, page.pageNumber));
        }}
      />
    </>
  );
};

export default FeatureListPage;
