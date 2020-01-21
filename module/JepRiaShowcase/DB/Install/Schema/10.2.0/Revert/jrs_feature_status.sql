-- script: Install/Schema/10.2.0/Revert/jrs_feature_status.sql
-- Редактирование таблицы <jrs_feature_status>.


-- Удаление поля

alter table
  jrs_feature_status
drop (
  status_order
)
/
