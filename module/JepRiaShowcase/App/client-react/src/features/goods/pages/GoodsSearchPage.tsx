import React, { useEffect, useRef, useState } from "react";
import { useFormik } from "formik";
import queryString from "query-string";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { CheckBox, CheckBoxGroup, ComboBox, ComboBoxItem, Form, NumberInput } from "@jfront/ui-core";
import { GoodsSearchTemplate } from "../api/GoodsTypes";
import { selectSearchSubmit, selectSearchTemplate, submitSearch } from "../GoodsSearchSlice";
import { getGoodsSegmentOptions, getGoodsTypeOptions } from "../GoodsOptionsSlice";
import { RootState } from "../../../app/store";

const FeatureSearchPage = () => {
  //----------------
  let formRef = useRef(null) as any;
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  //----------------

  const selectSearch = useSelector(selectSearchSubmit);
  const searchTemplate: GoodsSearchTemplate = useSelector(selectSearchTemplate);

  const goodsType = useSelector((state: RootState) => state.goodsOptions.goodsType.options);
  const goodsTypeIsLoading = useSelector((state: RootState) => state.goodsOptions.goodsType.isLoading);
  const goodsSegment = useSelector((state: RootState) => state.goodsOptions.goodsSegment.options);
  const goodsSegmentIsLoading = useSelector((state: RootState) => state.goodsOptions.goodsSegment.isLoading);

  useEffect(() => {
    if (selectSearch) {
      formRef.current?.dispatchEvent(new Event("submit"));
      dispatch(submitSearch(false));
    }
  }, [selectSearch]);

  useEffect(() => {
    dispatch(getGoodsSegmentOptions());
    dispatch(getGoodsTypeOptions());
  }, []);

  const formik = useFormik<GoodsSearchTemplate>({
    initialValues: searchTemplate,
    enableReinitialize: true,
    onSubmit: (values) => {
      let query = queryString.stringify(values);
      if (query) {
        query = "&" + query;
      }
      history.push(`/goods/list/?pageSize=25&page=1${query}`);
    },
    validate: (values) => {

    },
  });

  return (
    <>
      <Form onSubmit={formik.handleSubmit} ref={formRef}>
        <Form.Field>
          <Form.Label>{t("goods.fields.goodsId")}:</Form.Label>
          <Form.Control error={formik.errors.goodsId}>
            <NumberInput
              name="goodsId"
              value={formik.values?.goodsId}
              onChange={formik.handleChange}
              autoComplete="off"
            />
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("goods.fields.goodsTypeCode")}</Form.Label>
          <Form.Control error={formik.errors.goodsTypeCode}>
            <ComboBox
              name="goodsTypeCode"
              value={formik.values?.goodsTypeCode? formik.values.goodsTypeCode: []}
              onSelectionChange={(name, value) => {
                formik.setFieldValue("goodsTypeCode", value);
              }}
              isLoading={goodsTypeIsLoading}
            >
              <ComboBoxItem value={undefined} label="" />
              {goodsType
                ? goodsType.map((option) => {
                  return (
                    <ComboBoxItem key={option.value} value={option.value} label={option.name} />
                  );
                })
                : null}
            </ComboBox>
          </Form.Control>
        </Form.Field>
        <Form.Field>
          <Form.Label>{t("goods.fields.goodsSegmentType")}</Form.Label>
          <Form.Control>
            <CheckBoxGroup
              name="name"
              isLoading={goodsSegmentIsLoading}
              disabled={false}
              values={formik.values.goodsSegmentCode ? formik.values.goodsSegmentCode : []}
              onChange={(name, newValue) => {
                formik.setFieldValue("goodsSegmentCode", newValue);
              }}
            >
              {goodsSegment
                ? goodsSegment.map((option) => {
                  return (
                    <CheckBox key={option.value} value={option.value} label={option.name} />
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
