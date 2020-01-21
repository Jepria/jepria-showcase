-- script: Install/Schema/10.1.0/run.sql
-- Обновление объектов схемы до версии 10.1.0.


-- Определяем табличное пространство для индексов
@oms-set-indexTablespace.sql

@oms-run jrs_feature.sql