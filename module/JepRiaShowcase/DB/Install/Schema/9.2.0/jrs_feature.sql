alter table
  jrs_feature
add (
  responsible_id                integer
)
/
comment on column jrs_feature.responsible_id is
  'Ответственный пользователь'
/


-- Индекс по ответственному. для внешнего ключа.
create index
  jrs_feature_ix_responsible
on
  jrs_feature (
    responsible_id
  )
tablespace &indexTablespace
/


alter table
  jrs_feature
add constraint
  jrs_feature_fk_jrs_responsible
foreign key
  ( responsible_id)
references
  op_operator ( operator_id)
/


