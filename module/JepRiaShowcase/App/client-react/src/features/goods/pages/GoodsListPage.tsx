import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Grid } from "@jfront/ui-core";
import { Goods } from "../api/GoodsTypes";
import { setCurrentRecord } from "../GoodsSlice";
import { selectSearchResult, fetchGoodsSearchResultset, selectIsLoading, selectResultSetSize, selectSearchId, fetchSearchGoods } from "../GoodsSearchSlice";

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

  const pageSize: number = parseInt(query.get("pageSize") as string);
  const page: number = parseInt(query.get("page") as string);
  const records: Array<Goods> = useSelector(selectSearchResult);
  const isLoading = useSelector(selectIsLoading);
  const resultSetSize = useSelector(selectResultSetSize);
  const searchId = useSelector(selectSearchId);

  useEffect(() => {
    dispatch(fetchGoodsSearchResultset(location.search, pageSize, page));
  }, [location]);

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
          onSelection={(selectedRecords) => {
            if (selectedRecords.length === 1) {
              dispatch(setCurrentRecord(selectedRecords[0]));
            } else {
              dispatch(setCurrentRecord(undefined));
            }
          }}
          onDoubleClick={(record) => {
            history.push(`/goods/${record.goodsId}/detail`);
          }}
          onPaging={(pageNumber, pageSize) => {
            if (searchId) {
              console.log(`pageNumber = ${pageNumber}, pageSize = ${pageSize}`)
              dispatch(fetchSearchGoods(searchId, pageSize, pageNumber));
            }
          }}
          onSort={(sortConfig) => {

            dispatch(fetchGoodsSearchResultset(location.search, pageSize, page, sortConfig));
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

export default GoodsListPage;
