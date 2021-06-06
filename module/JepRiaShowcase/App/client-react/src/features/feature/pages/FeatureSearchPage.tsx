import React, { useEffect, useState } from "react";
import { useFormik } from "formik";
import queryString from "query-string";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Form, NumberInput } from "@jfront/ui-core";
import { DatePicker } from "@jfront/ui-core";
import { CheckBoxGroup } from "@jfront/ui-core";
import { CheckBox } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { FeatureSearchTemplate } from "../api/FeatureTypes";
import { FeatureStatusOptions } from "../../feature-process/api/FeatureProcessTypes";
import { actions } from "../state/FeatureSearchSlice";
import { getFeatureStatusOptions } from "../../feature-process/api/FeatureProcessApi";
import { FeatureState } from "../state/FeatureReducer";

const useQuery = () => {
  return queryString.parse(window.location.search, { arrayFormat: 'bracket' })
};

const FeatureSearchPage = ({ formRef }) => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  const template = useQuery();
  //----------------

  const [statusOptions, setStatusOptions] = useState<FeatureStatusOptions[]>();

  const { searchRequest, isLoading } = useSelector(
    (state: FeatureState) => state.feature.featureSearchSlice
  );

  useEffect(() => {
    getFeatureStatusOptions().then((options) => {
      setStatusOptions(options);
    });
  }, []);

  const formik = useFormik<FeatureSearchTemplate>({
    initialValues: {
      maxRowCount: 25,
      // ...template, ...searchRequest.template,
      statusCodeList: []
    },
    enableReinitialize: true,
    onSubmit: (values) => {
      let query = queryString.stringify(values, { arrayFormat: 'bracket' });
      if (query) {
        query = "&" + query;
      }
      history.push(`/feature/list/?pageSize=25&page=1${encodeURI(query)}`);
    },
    validate: (values) => {
      const errors: {
        featureId?: string;
        featureNameTemplate?: string;
        featureNameEnTemplate?: string;
        dateInsFrom?: string;
        dateInsTo?: string;
        statusCodeList?: string;
      } = {};

      let query = queryString.stringify(values);
      history.replace(`?${query}`);
      return errors;
    },
  });
  console.log(formik.values.statusCodeList)
  return (
    <>
      <Form onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureId")}:</Form.Label>
          <Form.Control error={formik.errors.featureId}>
            <NumberInput
              name="featureId"
              value={formik.values?.featureId}
              onChange={formik.handleChange}
              autoComplete="off"
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameTemplate")}:</Form.Label>
          <Form.Control error={formik.errors.featureNameTemplate}>
            <TextInput
              name="featureNameTemplate"
              value={formik.values?.featureNameTemplate}
              onChange={formik.handleChange}
              autoComplete="off"
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEnTemplate")}:</Form.Label>
          <Form.Control error={formik.errors.featureNameEnTemplate}>
            <TextInput
              name="featureNameEnTemplate"
              value={formik.values?.featureNameEnTemplate}
              onChange={formik.handleChange}
              autoComplete="off"
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.dateInsFrom")}:</Form.Label>
          <Form.Control error={formik.errors.dateInsFrom as any}>
            <DatePicker
              name="dateInsFrom"
              selected={formik.values?.dateInsFrom}
              dateFormat="dd.MM.yyyy"
              onChange={(date) => {
                formik.setFieldValue("dateInsFrom", date);
              }}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.dateInsTo")}:</Form.Label>
          <Form.Control error={formik.errors.dateInsTo as any}>
            <DatePicker
              name="dateInsTo"
              selected={formik.values?.dateInsTo}
              onChange={(date) => {
                formik.setFieldValue("dateInsTo", date);
              }}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.statusCodeList")}:</Form.Label>
          <Form.Control error={formik.errors.statusCodeList as any}>
            <CheckBoxGroup
              name="statusCodeList"
              values={
                formik.values.statusCodeList ? formik.values.statusCodeList : []
              }
              style={{ width: "142px" }}
              onChange={(name, newValue) => {
                console.log(newValue)
                formik.setFieldValue("statusCodeList", newValue);
              }}
              isLoading={isLoading}
            >
              {statusOptions
                ? statusOptions.map((option) => {
                  return (
                    <CheckBox
                      key={option.value}
                      value={option.value}
                      label={option.name}
                    />
                  );
                })
                : null}
            </CheckBoxGroup>
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.maxRowCount")}:</Form.Label>
          <Form.Control>
            <NumberInput
              name="maxRowCount"
              value={formik.values.maxRowCount}
              onChange={formik.handleChange}
              autoComplete="off"
            />
          </Form.Control>
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureSearchPage;
