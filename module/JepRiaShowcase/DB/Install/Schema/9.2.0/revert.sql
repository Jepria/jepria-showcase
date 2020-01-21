-- script: DB\Install\Schema\9.2.0\revert.sql
-- Отменяет изменения в объектах схемы, внесенные при установке версии 1.1.0.
--


-- Таблицы

drop table jrs_feature_process
/
drop table jrs_feature_operator
/
drop table jrs_feature_status
/

drop sequence jrs_feature_process_seq
/

@oms-run Install/Schema/9.2.0/Revert/jrs_feature.sql
