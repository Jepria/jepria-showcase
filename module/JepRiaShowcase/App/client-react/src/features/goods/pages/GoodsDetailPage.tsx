import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { Goods } from "../api/GoodsTypes";
import { fetchFeature, selectError, selectCurrentRecord } from "../GoodsSlice";

const GoodsDetailPage = () => {
  //----------------
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { featureId } = useParams();
  const currentRecord: Goods = useSelector(selectCurrentRecord);
  const error = useSelector(selectError);

  useEffect(() => {
    dispatch(fetchFeature(featureId));
  }, []);

  return (
    <>
      {error ? <div>{error}</div> : null}
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
