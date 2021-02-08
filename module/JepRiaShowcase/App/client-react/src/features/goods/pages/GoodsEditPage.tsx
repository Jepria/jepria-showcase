import React, { useEffect, useRef } from "react";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, useParams } from "react-router-dom";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { Goods, GoodsUpdate } from "../api/GoodsTypes";
import { goodsCrudApi } from "../api/GoodsCrudApi";
import { getRecordById } from "../state/GoodsCrudSlice";
import { RootState } from "../../../app/store";

const GoodsEditPage = ({ formRef }) => {
  //----------------
  const history = useHistory();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------

  let { goodsId } = useParams();
  const { currentRecord } = useSelector((state: RootState) => state.feature.featureCrudSlice);

  useEffect(() => {
    dispatch(getRecordById({ primaryKey: goodsId }));
  }, []);

  const formik = useFormik<GoodsUpdate>({
    initialValues: {},
    onSubmit: (values: GoodsUpdate) => {
      if (goodsId) {
        goodsCrudApi.update(goodsId.toString(), values).then(() => {
          history.push(`/feature/${goodsId}/detail`);
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
