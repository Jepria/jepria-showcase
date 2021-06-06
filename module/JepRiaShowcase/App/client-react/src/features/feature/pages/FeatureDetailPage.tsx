import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { getRecordById } from "../state/FeatureSlice";
import { RootState } from "../../../app/store";

const FeatureDetailPage = () => {
  //----------------
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { featureId } = useParams<any>();
  const { currentRecord, error } = useSelector(
    (state: RootState) => state.feature.featureCrudSlice
  );

  useEffect(() => {
    dispatch(getRecordById(featureId));
  }, []);

  return (
    <>
      {error ? <div>{error.message}</div> : null}
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
