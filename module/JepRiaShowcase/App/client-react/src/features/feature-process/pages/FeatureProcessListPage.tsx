import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useHistory, useLocation, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { Grid } from "@jfront/ui-core";
import { FeatureProcess } from "../api/FeatureProcessTypes";
import { findFeatureProcess } from "../api/FeatureProcessApi";
import { setCurrentFeatureProcess } from "../featureProcessSlice";

const FeatureProcessListPage = () => {
  //----------------
  const location = useLocation();
  const history = useHistory();
  const { t } = useTranslation();
  const dispatch = useDispatch();
  //----------------
  const [records, setRecords] = useState<FeatureProcess[]>();
  let { featureId } = useParams<any>();

  useEffect(() => {
    if (featureId) {
      findFeatureProcess(parseInt(featureId)).then((processes: FeatureProcess[]) => {
        setRecords(processes);
      });
    }
  }, [location]);

  return (
    <>
      <Grid
        id="table"
        columns={[
          {
            Header: t("feature-process.fields.featureStatusName"),
            accessor: "featureStatusName",
          },
          {
            Header: t("feature-process.fields.dateIns"),
            accessor: "dateIns",
          },
        ]}
        data={records ? records : []} //todo: bug in library
        onSelection={(selected) => {
          if (selected.length === 1) {
            dispatch(setCurrentFeatureProcess(selected[0]));
          } else {
            dispatch(setCurrentFeatureProcess(undefined));
          }
        }}
        onDoubleClick={(featureProcess) => {
          history.push(
            `/feature/${featureProcess.featureId}/feature-process/${featureProcess.featureProcessId}/detail`
          );
        }}
      />
    </>
  );
};

export default FeatureProcessListPage;
