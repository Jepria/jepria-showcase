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

  const { records, searchId, searchTemplate, isLoading, resultSetSize } = useSelector(
    (state: RootState) => state.feature.featureSearchSlice
  );

  useEffect(() => {
    if (searchId) {
      dispatch(search({ searchId: searchId, pageSize: page.pageSize, page: page.pageNumber }));
    } else {
      let searchTemplate = queryString.parse(location.search);
      console.log("searchTemplate ", searchTemplate);
      dispatch(
        postSearch({
          searchTemplate: { template: searchTemplate },
          page: page.pageNumber,
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
        onSelection={(selectedRecords: Feature[]) => {
          if (selectedRecords.length === 1) {
            dispatch(crudActions.setCurrentRecord({ currentRecord: selectedRecords[0] }));
          } else {
            dispatch(crudActions.setCurrentRecord({}));
          }
        }}
        onDoubleClick={(record) => {
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
              ...searchTemplate?.template,
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
