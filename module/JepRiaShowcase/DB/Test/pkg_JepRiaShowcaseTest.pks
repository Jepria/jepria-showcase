create or replace package pkg_JepRiaShowcaseTest is
/* package: pkg_JepRiaShowcaseTest
  Функции для тестирования модуля.

  SVN root: JEP/Module/JepRiaShowcase
*/



/* group: Функции */

/* pproc: endTestException
  Завершает тест с проверкой исключения.

  Параметры:
  expectedException           - ожидаемый текст исключения
  inExceptionFlag             - находится ли вызов в блоке обработки
                                исключения
  exceptionText               - текст исключения ( получается до вызова для
                                целостности стека)

  Замечание:
  - в случае дальнешего использования рассмотреть возможность переноса в
    TestUtility;

  ( <body::endTestException>)
*/
procedure endTestException(
  expectedException             varchar2
  , inExceptionFlag             integer
  , exceptionText               varchar2 := null
);

/* pproc: testEditFeatureAccess
  Проверка разграничения прав доступа на редактирование и удаление запроса на
  функционал.

  ( <body::testEditFeatureAccess>)
*/
procedure testEditFeatureAccess;

/* pproc: testFeatureStatus
  Проверка установки статуса запроса на новый функционал.

  ( <body::testFeatureStatus>)
*/
procedure testFeatureStatus;

/* pproc: testUserApi
  Тестирование API для пользовательского интерфейса.

  ( <body::testUserApi>)
*/
procedure testUserApi;

/* pproc: testFeatureOperator
  Тест пользователей запросов на функционал.

  ( <body::testFeatureOperator>)
*/
procedure testFeatureOperator;

/* pproc: testFindFeature
  Тест поиска запросов на функционал.

  ( <body::testFindFeature>)
*/
procedure testFindFeature;

/* pproc: testSetFeatureWorkSequence
  Тест установки порядка выполнения запросов на функционал.

  ( <body::testSetFeatureWorkSequence>)
*/
procedure testSetFeatureWorkSequence;

/* pproc: testSetFeatureResponsible
  Тест установки ответственного за запрос на новый функционал

  ( <body::testSetFeatureResponsible>)
*/
procedure testSetFeatureResponsible;

end pkg_JepRiaShowcaseTest;
/
