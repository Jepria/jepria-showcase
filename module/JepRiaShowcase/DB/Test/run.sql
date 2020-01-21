-- script: Test/run.sql
-- Выполняет все тесты.
--


@oms-run edit-feature-access.sql
rollback;

@oms-run feature-status.sql
rollback;


@oms-run feature-operator.sql
rollback;

@oms-run feature-find.sql
rollback;

@oms-run feature-set-work-sequence.sql
rollback;

@oms-run feature-set-responsible.sql
rollback;
