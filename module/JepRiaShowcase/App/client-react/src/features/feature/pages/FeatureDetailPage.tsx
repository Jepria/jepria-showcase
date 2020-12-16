import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { Feature } from "../api/FeatureTypes";
import { fetchFeature, selectError, selectFeature } from "../featureSlice";

const FeatureDetailPage = () => {
  //----------------
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { featureId } = useParams();
  const currentRecord: Feature = useSelector(selectFeature);
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
            {currentRecord?.featureId}
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
            {currentRecord?.featureStatus?.name}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureName")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.featureName}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEn")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.featureNameEn}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.dateIns")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord
              ? currentRecord?.dateIns.toString()
                ? new Date(currentRecord?.dateIns.toString()).toLocaleDateString()
                : ""
              : null}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.description")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.description}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.author")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "flex-start" }}>
            {currentRecord?.author?.name}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.responsible")}:</Form.Label>
          <Form.Label
            style={{
              width: "350px",
              justifyContent: "flex-start",
            }}
          >
            {currentRecord?.responsible?.name}
          </Form.Label>
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureDetailPage;
