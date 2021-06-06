import React, { useEffect } from "react";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useSelector, useDispatch } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { FeatureUpdate } from "../api/FeatureTypes";
import { RootState, useAppDispatch } from "../../../app/store";
import { getRecordById, updateRecord } from "../state/FeatureSlice";

const FeatureEditPage = ({ formRef }) => {
  //----------------
  const history = useHistory();
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  //----------------

  let { featureId } = useParams<any>();
  const { currentRecord } = useSelector((state: RootState) => state.feature.featureCrudSlice);

  useEffect(() => {
    dispatch(getRecordById(featureId));
  }, []);

  const formik = useFormik<FeatureUpdate>({
    initialValues: {
      featureName: currentRecord?.featureName ? currentRecord?.featureName : "",
      featureNameEn: currentRecord?.featureNameEn ? currentRecord?.featureNameEn : "",
      description: currentRecord?.description ? currentRecord?.description : "",
    },
    onSubmit: (values: FeatureUpdate) => {
      if (featureId) {
        dispatch(updateRecord(featureId, values)).then(() => {
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
