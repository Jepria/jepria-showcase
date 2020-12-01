import React, { useEffect, useRef } from "react";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import {
  selectFeature,
  selectSaveOnEditFeature,
  setCurrentRecord,
  submitSavedOnEditFeature,
} from "../featureSlice";
import { Feature, FeatureUpdate } from "../api/FeatureTypes";
import { setState, Workstates } from "../../../app/WorkstateSlice";
import { featureCrudApi } from "../api/FeatureCrudApi";

const FeatureEditPage = () => {
  let formRef = useRef(null) as any;
  const history = useHistory();
  let { featureId } = useParams();
  const { t } = useTranslation();
  const onSaveFeature = useSelector(selectSaveOnEditFeature);

  const dispatch = useDispatch();
  const currentRecord: Feature = useSelector(selectFeature);

  useEffect(() => {
    if (onSaveFeature) {
      dispatch(submitSavedOnEditFeature);
      formRef.current?.dispatchEvent(new Event("submit"));
    }
  }, [onSaveFeature]);

  const onSubmit = (data: FeatureUpdate) => {
    if (featureId) {
      featureCrudApi.update(featureId.toString(), data).then(() => {
        history.push(`/${featureId}/detail`);
      });
    }
  };

  useEffect(() => {
    dispatch(setState(Workstates.FeatureEdit));
    featureCrudApi.getRecordById(featureId).then((feature) => {
      dispatch(setCurrentRecord(feature));
    });
  }, []);

  const formik = useFormik<FeatureUpdate>({
    initialValues: {
      featureName: currentRecord?.featureName ? currentRecord?.featureName : "",
      featureNameEn: currentRecord?.featureNameEn ? currentRecord?.featureNameEn : "",
      description: currentRecord?.description ? currentRecord?.description : "",
    },
    onSubmit: (values: FeatureUpdate) => {
      onSubmit(values);
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
            style={{ width: "350px", textAlign: "left" }}
            defaultValue={currentRecord?.featureName}
            name="featureName"
            value={formik.values.featureName}
            onChange={formik.handleChange}
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEn")}:</Form.Label>
          <TextInput
            style={{ width: "350px", textAlign: "left" }}
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
            style={{ width: "350px", textAlign: "left" }}
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
        <Form.Field>
          <input id="edit-submit" type="submit" hidden={true} />
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureEditPage;
