import React, { useEffect, useRef } from "react";
import { useHistory } from "react-router-dom";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { Feature, FeatureCreate } from "../api/FeatureTypes";
import { selectSaveOnCreateFeature, submitSavedOnCreate } from "../featureSlice";
import { setState, Workstates } from "../../../app/WorkstateSlice";
import { featureCrudApi } from "../api/FeatureCrudApi";

const FeatureCreatePage = () => {
  //----------------
  let formRef = useRef(null) as any;
  const history = useHistory();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  const onCreateFeature = useSelector(selectSaveOnCreateFeature);
  useEffect(() => {
    if (onCreateFeature) {
      dispatch(submitSavedOnCreate());
      formRef.current?.dispatchEvent(new Event("submit"));
    }
  }, [onCreateFeature]);

  const formik = useFormik<FeatureCreate>({
    initialValues: {
      description: "",
      featureName: "",
      featureNameEn: "",
    },
    onSubmit: (values: FeatureCreate) => {
      featureCrudApi.create(values).then((feature: Feature) => {
        history.push(`/feature/${feature.featureId}/detail`);
      });
    },
    validate: (values) => {
      const errors: {
        featureName?: string;
        featureNameEn?: string;
        description?: string;
      } = {};
      if (!values.featureName) {
        errors.featureName = t("validation.notEmpty");
      }
      return errors;
    },
  });

  useEffect(() => {
    dispatch(setState(Workstates.FeatureCreate));
  }, []);

  return (
    <>
      <Form id="create-form" onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label required>{t("feature.fields.featureName")}</Form.Label>
          <Form.Control error={formik.errors.featureName} style={{ maxWidth: "150px" }}>
            <TextInput
              name="featureName"
              value={formik.values.featureName}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEn")}</Form.Label>
          <Form.Control error={formik.errors.featureNameEn} style={{ maxWidth: "150px" }}>
            <TextInput
              name="featureNameEn"
              value={formik.values.featureNameEn}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.description")}:</Form.Label>
          <Form.Control error={formik.errors.description} style={{ maxWidth: "150px" }}>
            <textarea
              name="description"
              value={formik.values.description}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureCreatePage;
