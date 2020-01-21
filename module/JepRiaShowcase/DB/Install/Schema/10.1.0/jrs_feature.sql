-- script: Install/Schema/10.1.0/jrs_feature.sql
-- Редактирование таблицы <jrs_feature>.


-- Добавление поля

alter table
  jrs_feature
add (
  work_sequence integer
)
/


-- Добавление комментария к полю

comment on column jrs_feature.work_sequence is
  'Порядок выполнения запроса'
/


-- Индекс для обесечения уникальности по порядку выполнения запроса.
-- index: jrs_feature_ux_work_sequence

create unique index
  jrs_feature_ux_work_sequence
on
  jrs_feature(
    work_sequence
  )
tablespace &indexTablespace
/