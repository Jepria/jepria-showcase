begin
  delete from
    jrs_feature_process
  where
    feature_status_code in (
      'NEW' -- pkg_JepRiaShowcase.New_FeatureStatusCode
    , 'SEQUENCED' -- pkg_JepRiaShowcase.Sequenced_FeatureStatusCode
    );
  pkg_JepRiaShowcase.normalizeFeatureProcess();
end;
/

