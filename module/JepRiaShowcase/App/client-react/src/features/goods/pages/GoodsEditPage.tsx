import React, { useEffect, useRef } from "react";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import {
  selectCurrentRecord,
  selectSaveOnEditFeature,
  setCurrentRecord,
  submitSavedOnEditFeature,
} from "../GoodsSlice";
import { Goods, GoodsUpdate } from "../api/GoodsTypes";
import { goodsCrudApi } from "../api/GoodsCrudApi";

const GoodsEditPage = () => {
  //----------------
  let formRef = useRef(null) as any;
  const history = useHistory();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { featureId } = useParams();
  const currentRecord: Goods = useSelector(selectCurrentRecord);

  const onSaveFeature = useSelector(selectSaveOnEditFeature);
  useEffect(() => {
    if (onSaveFeature) {
      dispatch(submitSavedOnEditFeature());
      formRef.current?.dispatchEvent(new Event("submit"));
    }
  }, [onSaveFeature]);

  useEffect(() => {
    goodsCrudApi.getRecordById(featureId).then((feature) => {
      dispatch(setCurrentRecord(feature));
    });
  }, []);

  const formik = useFormik<GoodsUpdate>({
    initialValues: {
    },
    onSubmit: (values: GoodsUpdate) => {
      if (featureId) {
        goodsCrudApi.update(featureId.toString(), values).then(() => {
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
            {currentRecord?.goodsId}
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
            {currentRecord?.goodsTypeCode}
          </Form.Label>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("feature.fields.featureName")}:</Form.Label>
          <TextInput
            style={{ textAlign: "left" }}
            defaultValue={currentRecord?.goodsName}
            name="featureName"
            value={formik.values.goodsName}
            onChange={formik.handleChange}
          />
        </Form.Field>
      </Form>
    </>
  );
};

export default GoodsEditPage;
