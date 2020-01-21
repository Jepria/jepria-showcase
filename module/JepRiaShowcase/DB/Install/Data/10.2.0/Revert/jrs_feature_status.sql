begin
delete from
  jrs_feature_status
where
  feature_status_code in (
    'NEW' -- pkg_JepRiaShowcase.New_FeatureStatusCode
  , 'SEQUENCED' -- pkg_JepRiaShowcase.Sequenced_FeatureStatusCode
  );
merge into
  jrs_feature_status d
using
  (
  select
    pkg_JepRiaShowcase.Assigned_FeatureStatusCode as feature_status_code
    , 'Назначен' as feature_status_name
    , 'Assigned' as feature_status_name_en
  from dual
  union all select
    pkg_JepRiaShowcase.InProgress_FeatureStatusCode as feature_status_code
    , 'В работе' as feature_status_name
    , 'In progress' as feature_status_name_en
  from dual
  union all select
    pkg_JepRiaShowcase.Remarks_FeatureStatusCode as feature_status_code
    , 'Работа над ремарками' as feature_status_name
    , 'Remarks process' as feature_status_name_en
  from dual
  union all select
    pkg_JepRiaShowcase.Done_FeatureStatusCode as feature_status_code
    , 'Реализован' as feature_status_name
    , 'Done' as feature_status_name_en
  from dual
  union all select
    pkg_JepRiaShowcase.Cancelled_FeatureStatusCode as feature_status_code
    , 'Отменён' as feature_status_name
    , 'Cancelled' as feature_status_name_en
  from dual
  minus
  select
    t.feature_status_code
    , t.feature_status_name
    , t.feature_status_name_en
  from
    jrs_feature_status t
  ) s
on
  (
  d.feature_status_code = s.feature_status_code
  )
when not matched then insert
  (
  feature_status_code
  , feature_status_name
  , feature_status_name_en
  )
values
  (
  s.feature_status_code
  , s.feature_status_name
  , s.feature_status_name_en
  )
when matched then update set
  d.feature_status_name            = s.feature_status_name
  , d.feature_status_name_en       = s.feature_status_name_en
;
  dbms_output.put_line( 'changed: ' || SQL%ROWCOUNT);
  commit;
end;
/
