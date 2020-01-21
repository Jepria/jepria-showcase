begin
merge into
  jrs_feature_status d
using
  (
  select
    pkg_JepRiaShowcase.New_FeatureStatusCode as feature_status_code
    , 'Новый' as feature_status_name
    , 'New' as feature_status_name_en
    , 1 as status_order
  from dual
  union all select
    pkg_JepRiaShowcase.Sequenced_FeatureStatusCode as feature_status_code
    , 'Установлен порядок выполнения' as feature_status_name
    , 'Sequence defined' as feature_status_name_en
    , 2 as status_order
  from dual
  union all select
    pkg_JepRiaShowcase.Assigned_FeatureStatusCode as feature_status_code
    , 'Назначен' as feature_status_name
    , 'Assigned' as feature_status_name_en
    , 3 as status_order
  from dual
  union all select
    pkg_JepRiaShowcase.InProgress_FeatureStatusCode as feature_status_code
    , 'В работе' as feature_status_name
    , 'In progress' as feature_status_name_en
    , 4 as status_order
  from dual
  union all select
    pkg_JepRiaShowcase.Remarks_FeatureStatusCode as feature_status_code
    , 'Работа над ремарками' as feature_status_name
    , 'Remarks process' as feature_status_name_en
    , 5 as status_order
  from dual
  union all select
    pkg_JepRiaShowcase.Done_FeatureStatusCode as feature_status_code
    , 'Реализован' as feature_status_name
    , 'Done' as feature_status_name_en
    , 6 as status_order
  from dual
  union all select
    pkg_JepRiaShowcase.Cancelled_FeatureStatusCode as feature_status_code
    , 'Отменён' as feature_status_name
    , 'Cancelled' as feature_status_name_en
    , 7 as status_order
  from dual
  minus
  select
    t.feature_status_code
    , t.feature_status_name
    , t.feature_status_name_en
    , t.status_order
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
  , status_order
  )
values
  (
  s.feature_status_code
  , s.feature_status_name
  , s.feature_status_name_en
  , s.status_order
  )
when matched then update set
  d.feature_status_name            = s.feature_status_name
  , d.feature_status_name_en       = s.feature_status_name_en
  , d.status_order                 = s.status_order
;
  dbms_output.put_line( 'changed: ' || SQL%ROWCOUNT);
  commit;
end;
/
