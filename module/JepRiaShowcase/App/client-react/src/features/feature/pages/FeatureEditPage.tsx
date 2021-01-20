import React, { useEffect, useRef } from "react";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
// import {
//   selectFeature,
//   selectSaveOnEditFeature,
//   setCurrentRecord,
//   submitSavedOnEditFeature,
// } from "../state/featureSlice";
import { Feature, FeatureUpdate } from "../api/FeatureTypes";
import { featureCrudApi } from "../api/FeatureCrudApi";
import { RootState } from "../../../app/store";

const FeatureEditPage = ({ formRef }) => {
  //----------------
  // let formRef = useRef(null) as any;
  const history = useHistory();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { featureId } = useParams();
  const { currentRecord } = useSelector((state: RootState) => state.feature.featureCrudSlice);

  // const onSaveFeature = useSelector(selectSaveOnEditFeature);
  // useEffect(() => {
  //   if (onSaveFeature) {
  //     dispatch(submitSavedOnEditFeature());
  //     formRef.current?.dispatchEvent(new Event("submit"));
  //   }
  // }, [onSaveFeature]);

  // useEffect(() => {
  //   featureCrudApi.getRecordById(featureId).then((feature) => {
  //     dispatch(setCurrentRecord(feature));
  //   });
  // }, []);

  const formik = useFormik<FeatureUpdate>({
    initialValues: {
      featureName: currentRecord?.featureName ? currentRecord?.featureName : "",
      featureNameEn: currentRecord?.featureNameEn ? currentRecord?.featureNameEn : "",
      description: currentRecord?.description ? currentRecord?.description : "",
    },
    onSubmit: (values: FeatureUpdate) => {
      if (featureId) {
        featureCrudApi.update(featureId.toString(), values).then(() => {
          history.push(`/feature/${featureId}/detail`);
        });
      }
    },
    enableReinitialize: true,
  });

  return (
    <>
      <Form id="edit-form" onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureId")}:</Form.Label>
          <Form.Label style={{ width: "350px", justifyContent: "start" }}>
            {currentRecord?.featureId}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureStatus")}:</Form.Label>
          <Form.Label
            style={{
              width: "350px",
              justifyContent: "start",
            }}
          >
            {currentRecord?.featureStatus?.name}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureName")}:</Form.Label>
          <TextInput
            style={{ textAlign: "left" }}
            defaultValue={currentRecord?.featureName}
            name="featureName"
            value={formik.values.featureName}
            onChange={formik.handleChange}
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEn")}:</Form.Label>
          <TextInput
            style={{ textAlign: "left" }}
            defaultValue={currentRecord?.featureNameEn}
            name="featureNameEn"
            value={formik.values.featureNameEn}
            onChange={formik.handleChange}
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.dateIns")}:</Form.Label>
          <Form.Label
            style={{
              width: "350px",
              justifyContent: "start",
            }}
          >
            {currentRecord?.dateIns.toString()
              ? new Date(currentRecord?.dateIns.toString()).toLocaleDateString()
              : ""}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.description")}:</Form.Label>
          <TextInput
            style={{ textAlign: "left" }}
            defaultValue={currentRecord?.description}
            name="description"
            value={formik.values.description}
            onChange={formik.handleChange}
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.author")}:</Form.Label>
          <Form.Label style={{ width: "350px", textAlign: "left" }}>
            {currentRecord?.author?.name}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.responsible")}:</Form.Label>
          <Form.Label
            style={{
              width: "350px",
              textAlign: "left",
            }}
          >
            {currentRecord?.responsible?.name}
          </Form.Label>
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureEditPage;
