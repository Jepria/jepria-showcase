import React, { useEffect, useRef, useState } from "react";
import { useFormik } from "formik";
import queryString from "query-string";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { DatePicker } from "@jfront/ui-core";
import { CheckBoxGroup } from "@jfront/ui-core";
import { CheckBox } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { FeatureSearchTemplate } from "../api/FeatureTypes";
import { FeatureStatusOptions } from "../../feature-process/api/FeatureProcessTypes";
import { getFeatureStatusOptions } from "../../feature-process/api/FeatureProcessApi";
import { setState, Workstates } from "../../../app/WorkstateSlice";
import { selectSearchSubmit, selectSearchTemplate, submitSearch } from "../featureSearchSlice";

const SearchPage = () => {
  let formRef = useRef(null) as any;
  const { t } = useTranslation();
  const history = useHistory();
  let [statusOptions, setStatusOptions] = useState<FeatureStatusOptions[]>();
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const dispatch = useDispatch();
  const selectSearch = useSelector(selectSearchSubmit);
  const searchTemplate: FeatureSearchTemplate = useSelector(selectSearchTemplate);

  useEffect(() => {
    if (selectSearch) {
      formRef.current?.dispatchEvent(new Event("submit"));
      dispatch(submitSearch(false));
    }
  }, [selectSearch]);

  const onSubmit = (data?: FeatureSearchTemplate) => {
    let query = queryString.stringify(data);
    if (query) {
      query = "&" + query;
    }

    history.push(`/feature/list/?pageSize=25&page=1${query}`);
  };

  useEffect(() => {
    dispatch(setState(Workstates.FeatureSearch));
    getFeatureStatusOptions().then((options) => {
      setStatusOptions(options);
      setIsLoading(false);
    });
  }, []);

  const formik = useFormik<FeatureSearchTemplate>({
    initialValues: searchTemplate,
    onSubmit: (values) => {
      onSubmit(values);
    },
    enableReinitialize: true,
  });

  return (
    <>
      <Form onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field style={{ display: "inline-block" }}>
          <Form.Label>{t("feature.fields.featureId")}:</Form.Label>
          <TextInput
            name="featureId"
            value={formik.values?.featureId}
            onChange={formik.handleChange}
            type="number"
            autoComplete="off"
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameTemplate")}:</Form.Label>
          <TextInput
            name="featureNameTemplate"
            value={formik.values?.featureNameTemplate}
            onChange={formik.handleChange}
            autoComplete="off"
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureNameEnTemplate")}:</Form.Label>
          <TextInput
            name="featureNameEnTemplate"
            value={formik.values?.featureNameEnTemplate}
            onChange={formik.handleChange}
            autoComplete="off"
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.dateInsFrom")}</Form.Label>
          <DatePicker
            name="dateInsFrom"
            selected={formik.values?.dateInsFrom}
            onChange={(date) => {
              formik.setFieldValue("dateInsFrom", date);
            }}
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.dateInsTo")}</Form.Label>
          <DatePicker
            name="dateInsTo"
            selected={formik.values?.dateInsTo}
            onChange={(date) => {
              formik.setFieldValue("dateInsTo", date);
            }}
          />
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.statusCodeList")}</Form.Label>
          <CheckBoxGroup
            name="statusCodeList"
            values={formik.values?.statusCodeList ? formik.values.statusCodeList : []}
            style={{ width: "142px" }}
            onChange={(name, newValue) => {
              formik.setFieldValue("statusCodeList", newValue);
            }}
            isLoading={isLoading}
          >
            {statusOptions
              ? statusOptions.map((option) => {
                  return <CheckBox key={option.value} value={option.value} label={option.name} />;
                })
              : null}
          </CheckBoxGroup>
        </Form.Field>
        <Form.Field>
          <input id="search-submit" type="submit" hidden={true} />
        </Form.Field>
      </Form>
    </>
  );
};

export default SearchPage;
