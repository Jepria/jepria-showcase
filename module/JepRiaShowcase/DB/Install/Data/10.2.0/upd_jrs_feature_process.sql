-- script: DB\Install\Schema\10.2.0\revert.sql
-- Добавление записи обработки в существующие запросы.

begin
  insert into
    jrs_feature_process (
      feature_process_id
    , feature_id
    , feature_status_code
    , is_last
    , operator_id
    )
  select
      jrs_feature_process_seq.nextval
    , f.feature_id
    , case
      when f.work_sequence is null
        and f.responsible_id is null
      then
        pkg_JepRiaShowcase.New_FeatureStatusCode
      when f.work_sequence is not null
        and f.responsible_id is null
      then
        pkg_JepRiaShowcase.Sequenced_FeatureStatusCode
      else
        pkg_JepRiaShowcase.Assigned_FeatureStatusCode
      end
    , 1
    , pkg_Operator.getCurrentUserId()
  from
    jrs_feature f
  where
    not exists (
      select
        1
      from
        jrs_feature_process p
      where
        p.feature_id = f.feature_id
    )
  ;
  dbms_output.put_line( 'changed: ' || SQL%ROWCOUNT);
  commit;
end;
/
