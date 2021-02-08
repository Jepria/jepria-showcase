import React, { useEffect } from "react";
import { useFormik } from "formik";
import queryString from "query-string";
import { useTranslation } from "react-i18next";
import { useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import {
  CheckBox,
  CheckBoxGroup,
  ComboBox,
  ComboBoxItem,
  Form,
  NumberInput,
} from "@jfront/ui-core";
import { GoodsSearchTemplate } from "../api/GoodsTypes";
import { RootState } from "../../../app/store";
import { getGoodsSegmentOptions, getGoodsTypeOptions } from "../state/GoodsOptionsSlice";

const FeatureSearchPage = ({ formRef }) => {
  //----------------
  const { t } = useTranslation();
  const history = useHistory();
  const dispatch = useDispatch();
  //----------------

  const { searchRequest, isLoading } = useSelector(
    (state: RootState) => state.goods.goodsSearchSlice
  );

  const goodsType = useSelector((state: RootState) => state.goods.goodsOptionsSlice.goodsType.options);
  const goodsTypeIsLoading = useSelector(
    (state: RootState) => state.goods.goodsOptionsSlice.goodsType.isLoading
  );
  const goodsSegment = useSelector((state: RootState) => state.goods.goodsOptionsSlice.goodsSegment.options);
  const goodsSegmentIsLoading = useSelector(
    (state: RootState) => state.goods.goodsOptionsSlice.goodsSegment.isLoading
  );

  useEffect(() => {
    dispatch(getGoodsSegmentOptions());
    dispatch(getGoodsTypeOptions());
    
  }, []);

  const formik = useFormik<GoodsSearchTemplate>({
    initialValues: searchRequest.template,
    enableReinitialize: true,
    onSubmit: (values) => {
      let query = queryString.stringify(values);
      if (query) {
        query = "&" + query;
      }
      history.push(`/goods/list/?pageSize=25&page=1${query}`);
    },
    validate: (values) => {
      let query = queryString.stringify(values);
      history.replace(`?${query}`);
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
              value={formik.values?.goodsTypeCode ? formik.values.goodsTypeCode : []}
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
                    return <CheckBox key={option.value} value={option.value} label={option.name} />;
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
