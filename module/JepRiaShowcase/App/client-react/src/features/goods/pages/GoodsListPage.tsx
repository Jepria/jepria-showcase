import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@jfront/ui-core";
import { Goods } from "../api/GoodsTypes";
import { RootState } from "../../../app/store";
import { actions as crudActions } from "../state/GoodsCrudSlice";
import { search, postSearchRequest } from "../state/GoodsSearchSlice";

const useQuery = () => {
  return new URLSearchParams(useLocation().search);
};

const GoodsListPage = () => {
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
    (state: RootState) => state.goods.goodsSearchSlice
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
            Header: t("goods.fields.supplierId"),
            accessor: "supplierId",
          },
          {
            Header: t("goods.fields.goodsId"),
            accessor: "goodsId",
          },
          {
            Header: t("goods.fields.goodsName"),
            accessor: "goodsName",
          },
          {
            Header: t("goods.fields.goodsTypeCode"),
            id: "goodsTypeCode",
            accessor: "goodsTypeCode.name",
          },
          {
            Header: t("goods.fields.purchasingPrice"),
            accessor: "purchasingPrice",
          },
          {
            Header: t("goods.fields.UnitCode"),
            id: "UnitCode",
            accessor: "UnitCode.name",
          },
          {
            Header: t("goods.fields.MotivationTypeCode"),
            id: "MotivationTypeCode",
            accessor: "MotivationTypeCode.name",
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
        onDoubleClick={(record: Goods) => {
          history.push(`/feature/${record.goodsId}/detail`);
        }}
        onPaging={(pageNumber, pageSize) => {
          setPage({
            pageNumber: pageNumber,
            pageSize: pageSize,
          });
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

export default GoodsListPage;
