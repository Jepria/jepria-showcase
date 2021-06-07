import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { getFeatureProcess } from "../api/FeatureProcessApi";
import { setCurrentFeatureProcess } from "../state/featureProcessSlice";
import { RootState } from "../../../app/store";
import { toLocaleDate } from "../../../app/common/dateUtils";

const FeatureProcessDetailPage = () => {
  //----------------
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------
  let { featureId, featureProcessId } = useParams<any>();

  const currentRecord = useSelector((state: RootState) => state.featureProcess.currentRecord);

  useEffect(() => {
    if (featureId && featureProcessId) {
      getFeatureProcess(parseInt(featureId), featureProcessId).then((featureProcess) => {
        dispatch(setCurrentFeatureProcess(featureProcess));
      });
    }
  }, []);

  return (
    <>
      <Form>
        <Form.Field>
          <Form.Label>{t("feature-process.fields.featureStatusCode")}</Form.Label>
          <Form.Label>{currentRecord?.featureStatusName}</Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature-process.fields.dateIns")}</Form.Label>
          <Form.Label>{toLocaleDate(currentRecord?.dateIns)}</Form.Label>
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureProcessDetailPage;
