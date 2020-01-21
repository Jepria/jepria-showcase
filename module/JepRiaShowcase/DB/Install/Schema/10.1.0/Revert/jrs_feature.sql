-- script: Install/Schema/10.1.0/Revert/jrs_feature.sql
-- Редактирование таблицы <jrs_feature>.


-- Удаление поля

alter table
  jrs_feature
drop (
  work_sequence
)
/