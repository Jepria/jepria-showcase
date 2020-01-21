-- script: Install/Schema/9.2.0/run.sql
-- Обновление объектов схемы до версии 9.2.0.
--
-- Основные изменения:
--  - добавление таблицы <jrs_feature_status>;
--  - добавление таблицы <jrs_feature_process>;
--  - добавление таблицы <jrs_feature_operator>;
--  - добавление поля responsible_id в <jrs_feature>
--

-- Определяем табличное пространство для индексов
@oms-set-indexTablespace.sql

@oms-run jrs_feature.sql


@oms-run Install/Schema/Last/jrs_feature_status.tab
@oms-run Install/Schema/Last/jrs_feature_process.tab
@oms-run Install/Schema/Last/jrs_feature_operator.tab


@oms-run Install/Schema/Last/jrs_feature_status.con
@oms-run Install/Schema/Last/jrs_feature_process.con
@oms-run Install/Schema/Last/jrs_feature_operator.con


@oms-run Install/Schema/Last/jrs_feature_process_seq.sqs
