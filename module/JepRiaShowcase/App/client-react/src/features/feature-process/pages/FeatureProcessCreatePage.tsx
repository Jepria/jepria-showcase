import React, { useEffect, useRef, useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useFormik } from "formik";
import { useDispatch, useSelector } from "react-redux";
import { ComboBox, ComboBoxItem, Form } from "@jfront/ui-core";
import { FeatureProcessCreate, FeatureStatusOptions } from "../api/FeatureProcessTypes";
import { createFeatureProcess, getFeatureStatusOptions } from "../api/FeatureProcessApi";
import { setState, Workstates } from "../../../app/WorkstateSlice";
import {
  selectSaveOnCreateFeatureProcess,
  submitSavedOnCreateFeatureProcess,
} from "../featureProcessSlice";

const FeatureProcessCreatePage = () => {
  //----------------
  let formRef = useRef(null) as any;
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  //----------------
  let { featureId } = useParams();
  const [statusOptions, setStatusOptions] = useState<FeatureStatusOptions[]>();

  const onSave = useSelector(selectSaveOnCreateFeatureProcess);

  useEffect(() => {
    if (onSave) {
      dispatch(submitSavedOnCreateFeatureProcess());
      formRef.current?.dispatchEvent(new Event("submit"));
    }
  }, [onSave]);

  useEffect(() => {
    dispatch(setState(Workstates.FeatureProcessCreate));
    getFeatureStatusOptions().then((options) => {
      setStatusOptions(options);
    });
  }, []);

  const formik = useFormik<FeatureProcessCreate>({
    initialValues: {
      featureStatusCode: "",
    },
    onSubmit: (values: FeatureProcessCreate) => {
      if (featureId) {
        createFeatureProcess(parseInt(featureId), values).then((value) => {
          history.push(
            `/feature/${value.featureId}/feature-process/${value.featureProcessId}/detail`
          );
        });
      }
    },
  });

  return (
    <>
      <Form onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label>{t("feature-process.fields.featureStatusCode")}</Form.Label>
          <Form.Control error={formik.errors.featureStatusCode} style={{ maxWidth: "200px" }}>
            <ComboBox
              name="featureStatusCode"
              value={formik.values.featureStatusCode}
              onSelectionChange={(name, value) => {
                formik.setFieldValue("featureStatusCode", value);
              }}
            >
              <ComboBoxItem value={undefined} label="" />
              {statusOptions
                ? statusOptions.map((option) => {
                    return (
                      <ComboBoxItem key={option.value} value={option.value} label={option.name} />
                    );
                  })
                : null}
            </ComboBox>
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <input type="submit" id="feature-process-save" hidden={true} />
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureProcessCreatePage;
