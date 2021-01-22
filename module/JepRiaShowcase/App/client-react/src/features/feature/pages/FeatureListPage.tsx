import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import queryString from "query-string";
import { Grid } from "@jfront/ui-core";
import { Feature } from "../api/FeatureTypes";
import { postSearch, postSearchRequest, search } from "../featureSearchSlice";
import { actions as crudActions } from "../state/featureSlice";
import { actions as searchActions } from "../featureSearchSlice";
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

  const { currentRecord } = useSelector((state: RootState) => state.feature.featureCrudSlice);

  const { records, searchId, searchRequest, isLoading, resultSetSize } = useSelector(
    (state: RootState) => state.feature.featureSearchSlice
  );

  useEffect(() => {
    if (searchId) {
      dispatch(search({ searchId: searchId, pageSize: page.pageSize, pageNumber: page.pageNumber }));
    } else {
      let searchTemplate = queryString.parse(location.search);
      console.log("searchTemplate ", searchTemplate);
      dispatch(
        postSearch({
          searchTemplate: { template: searchTemplate },
          pageNumber: page.pageNumber,
          pageSize: page.pageSize,
        })
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
          if (pageNumber !== page.pageNumber || pageSize !== page.pageSize) {
            console.log("onPaging ", pageNumber, pageSize);
            setPage({
              pageNumber: pageNumber,
              pageSize: pageSize,
            });
          }
        }}
        onSort={(sortConfig) => {
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
