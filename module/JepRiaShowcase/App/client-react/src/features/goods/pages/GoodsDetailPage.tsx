import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { Goods } from "../api/GoodsTypes";
import { RootState } from "../../../app/store";
import { getRecordById } from "../state/GoodsCrudSlice";

const GoodsDetailPage = () => {
  //----------------
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { goodsId } = useParams();
  const { currentRecord, error } = useSelector(
    (state: RootState) => state.goods.goodsCrudSlice
  );

  useEffect(() => {
    dispatch(getRecordById({ primaryKey: goodsId }));
  }, []);

  return (
    <>
      {error ? <div>{error.message}</div> : null}
      <Form>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureId")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.goodsId}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureStatus")}:</Form.Label>
          <Form.Label
            style={{
              width: "350px",
              justifyContent: "flex-start",
            }}
          >
            {currentRecord?.goodsTypeCode}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureName")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.goodsName}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEn")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.supplierId}
          </Form.Label>
        </Form.Field>
      </Form>
    </>
  );
};

export default GoodsDetailPage;
