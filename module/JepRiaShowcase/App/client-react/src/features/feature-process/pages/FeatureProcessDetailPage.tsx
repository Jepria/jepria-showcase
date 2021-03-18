import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { getFeatureProcess } from "../api/FeatureProcessApi";
import { setCurrentFeatureProcess, selectFeatureProcess } from "../state/featureProcessSlice";
import { RootState } from "../../../app/store";

const FeatureProcessDetailPage = () => {
  //----------------
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------
  let { featureId, featureProcessId } = useParams();

  const { currentRecord, error } = useSelector(
    (state: RootState) => state.featureProcess.currentFeatureProcess
  );

  // const currentRecord = useSelector(selectFeatureProcess);

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
          <Form.Label>{currentRecord?.dateIns}</Form.Label>
        </Form.Field>
      </Form>
    </>
  );
};

export default FeatureProcessDetailPage;
