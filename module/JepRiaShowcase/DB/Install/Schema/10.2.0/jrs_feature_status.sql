-- script: Install/Schema/10.2.0/jrs_feature_status.sql
-- Редактирование таблицы <jrs_feature_status>.


-- Добавление поля

alter table
  jrs_feature_status
add (
  status_order                  integer             default 0       not null
)
/


-- Добавление комментария к полю

comment on column jrs_feature_status.status_order is
  'Поле для упорядочивания выборки статусов (в общем случае — порядок следования статусов)'
/

