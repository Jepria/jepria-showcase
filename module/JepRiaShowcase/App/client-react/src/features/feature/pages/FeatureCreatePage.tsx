import React from "react";
import { useHistory } from "react-router-dom";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { Feature, FeatureCreate } from "../api/FeatureTypes";
import { featureCrudApi } from "../api/FeatureCrudApi";

const FeatureCreatePage = ({ formRef }) => {
  //----------------
  const history = useHistory();
  const { t } = useTranslation();
  //----------------

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

  return (
    <>
      <Form id="create-form" onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label required>{t("feature.fields.featureName")}</Form.Label>
          <Form.Control error={formik.errors.featureName}>
            <TextInput
              name="featureName"
              value={formik.values.featureName}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEn")}</Form.Label>
          <Form.Control error={formik.errors.featureNameEn}>
            <TextInput
              name="featureNameEn"
              value={formik.values.featureNameEn}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.description")}:</Form.Label>
          <Form.Control error={formik.errors.description}>
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
