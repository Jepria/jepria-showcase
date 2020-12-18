import React, { useEffect, useRef } from "react";
import { useHistory } from "react-router-dom";
import { useFormik } from "formik";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { Form } from "@jfront/ui-core";
import { TextInput } from "@jfront/ui-core";
import { Goods, GoodsCreate } from "../api/GoodsTypes";
import { selectSaveOnCreateFeature, submitSavedOnCreate } from "../GoodsSlice";
import { goodsCrudApi } from "../api/GoodsCrudApi";

const GoodsCreatePage = () => {
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

  const formik = useFormik<GoodsCreate>({
    initialValues: {
      
    },
    onSubmit: (values: GoodsCreate) => {
      goodsCrudApi.create(values).then((good: Goods) => {
        history.push(`/goods/${good.goodsId}/detail`);
      });
    },    
  });

  return (
    <>
      <Form id="create-form" onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label required>{t("goods.fields.goodsName")}</Form.Label>
          <Form.Control error={formik.errors.goodsName}>
            <TextInput
              name="goodsName"
              value={formik.values.goodsName}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("goods.fields.goodsTypeCode")}</Form.Label>
          <Form.Control error={formik.errors.goodsTypeCode}>
            <TextInput
              name="goodsTypeCode"
              value={formik.values.goodsTypeCode}
              onChange={formik.handleChange}
            />
          </Form.Control>
        </Form.Field>
      </Form>
    </>
  );
};

export default GoodsCreatePage;
