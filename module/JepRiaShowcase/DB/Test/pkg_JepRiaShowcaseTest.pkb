create or replace package body itm.pkg_JepRiaShowcaseTest is
/* package body: pkg_JepRiaShowcaseTest::body */



/* group: Переменные */

/* ivar: logger
  Логер пакета.
*/
logger lg_logger_t := lg_logger_t.getLogger(
  moduleName    => pkg_JepRiaShowcase.Module_Name
  , objectName  => 'pkg_JepRiaShowcaseTest'
);



/* group: Функции */

/* iproc: checkFeatureStatusCode
  Проверяет статус обработки запроса на функционал.

  Параметры:
  featureId                   - id запроса на функционал
  expectedStatusCode          - ожидаемый код статуса
  operatorId                  - id оператора для доступа
*/
procedure checkFeatureStatusCode(
  featureId            integer
  , expectedStatusCode varchar2
  , operatorId         integer
)
is
  rc sys_refcursor;
-- getFeatureStatusCode
begin
  rc := pkg_JepRiaShowcase.findFeatureProcess(
    featureId           => featureId
    , featureStatusCode => expectedStatusCode
    , operatorId        => operatorId
  );
  pkg_TestUtility.compareRowCount(
    rc
  , expectedRowCount    => 1
  , failMessageText     => 'Неверный статус'
  );
exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка получения статуса обработки запроса на функционал'
      )
    , true
  );
end checkFeatureStatusCode;

/* proc: endTestException
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
*/
procedure endTestException(
  expectedException             varchar2
  , inExceptionFlag             integer
  , exceptionText               varchar2 := null
)
is
-- checkException
begin
  if expectedException is not null then
    if inExceptionFlag = 1 then
      if expectedException is not null
        and exceptionText not like '%' || expectedException || '%'
      then
        pkg_TestUtility.failTest(
          'Ожидаемый текст исключения ( '
          || '"' || expectedException || '") не содержится в тексте возникшего исключения ( '
          || '"' || exceptionText || '")'
        );
      end if;
    elsif
      inExceptionFlag = 0
      and expectedException is not null
    then
      pkg_TestUtility.failTest(
        'Ожидамое исключение: '
        || '"' || expectedException || '"'
      );
    end if;
  elsif inExceptionFlag = 1 then
    pkg_TestUtility.failTest(
      'Возникло неожиданное исключение ( "' || exceptionText || '")'
    );
  end if;
  pkg_TestUtility.endTest();
end endTestException;

/* proc: testEditFeatureAccess
  Проверка разграничения прав доступа на редактирование и удаление запроса на
  функционал.
*/
procedure testEditFeatureAccess
is

  -- Отступление от наименований переменных для улучшения читаемости
  -- и различимости цифр и значимой части

  -- Создаваемые пользователи
  operatorId_A integer;
  operatorId_B integer;
  operatorId_V integer;

  -- Создаваемые запросы
  feature_1    integer;
  feature_2    integer;

  /*
    Подготовка сценариев: Создаются пользователи с ролью JrsEditFeature:
    «Пользователь А», «Пользователь Б». Создается пользователь с ролью
    JrsEditAllFeature: «Пользователь В». «Пользователь А» создает запросы на
    новый функционал «Запрос 1» и «Запрос 2».
  */
  procedure initTest
  is
  begin
    operatorId_A := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'operator_A'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    operatorId_B := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'operator_B'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    operatorId_V := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'operator_C'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditAllFeature_RoleSName
        )
    );
    feature_1 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 1'
      , featureNameEn   => 'Feature 1'
      , operatorId      => operatorId_A
    );
    feature_2 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 2'
      , featureNameEn   => 'Feature 2'
      , operatorId      => operatorId_A
    );
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка подготовки сценариев'
        )
      , true
    );
  end initTest;

  /*
    Тестирование редактирования запроса функционала.

    Параметры:
    operatorId                - оператор, редактирующий/удаляющий запрос
    featureId                 - id запроса
    expectedException         - ожидаемый текст исключения
    deleteFlag                - флаг удаления
  */
  procedure testFeatureEdit(
    operatorId               integer
    , featureId              integer
    , expectedException      varchar2 := null
    , deleteFlag             integer := null
  )
  is
    rc sys_refcursor;
  begin
    if deleteFlag = 1 then
      delete from
        jrs_feature_process
      where
        feature_id = featureId;
      pkg_JepRiaShowcase.deleteFeature(
        featureId         => featureId
        , operatorId      => operatorId
      );
    else
      pkg_JepRiaShowcase.updateFeature(
        featureId         => featureId
        , featureName     => 'Обновление'
        , featureNameEn   => 'update'
        , operatorId      => operatorId
      );
    end if;
    rc := pkg_JepRiaShowcase.findFeature(
      featureId           => featureId
      , operatorId        => operatorId
    );
    pkg_TestUtility.compareRowCount(
      rc
    , expectedRowCount    =>
        case when
          deleteFlag = 1
        then
          0
        else
          1
        end
    , failMessageText     => 'Некорректное число записей в курсоре'
    );
    endTestException(
      expectedException   => expectedException
      , inExceptionFlag   => 0
    );
  exception when others then
    endTestException(
      exceptionText       => logger.getErrorStack()
      , expectedException => expectedException
      , inExceptionFlag   => 1
    );
  end testFeatureEdit;

-- testEditFeatureAccess
begin
  initTest();
  pkg_TestUtility.beginTest( 'Сценарий 1: Попытка изменения не своего запроса');
  testFeatureEdit(
    operatorId          => operatorId_B
    , featureId         => feature_1
    , expectedException => 'У Вас нет прав на изменение запроса на функционал!'
  );
  pkg_TestUtility.beginTest( 'Сценарий 2: Попытка удаления не своего запроса');
  testFeatureEdit(
    operatorId          => operatorId_B
    , featureId         => feature_1
    , expectedException => 'У Вас нет прав на изменение запроса на функционал!'
    , deleteFlag        => 1
  );
  pkg_TestUtility.beginTest(
    'Сценарий 3: Изменение запроса пользователем с правами радактирования'
    || ' всех запросов'
  );
  testFeatureEdit(
    operatorId          => operatorId_V
    , featureId         => feature_1
  );
  pkg_TestUtility.beginTest(
    'Сценарий 4: Удаление запроса пользователем с правами радактирования всех'
    || ' запросов'
  );
  testFeatureEdit(
    operatorId          => operatorId_V
    , featureId         => feature_1
    , deleteFlag        => 1
  );
  pkg_TestUtility.beginTest( 'Сценарий 5: Изменение своего запроса');
  testFeatureEdit(
    operatorId          => operatorId_A
    , featureId         => feature_2
  );
  pkg_TestUtility.beginTest( 'Сценарий 6: Удаление своего запроса');
  testFeatureEdit(
    operatorId          => operatorId_A
    , featureId         => feature_2
    , deleteFlag        => 1
  );
exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка проверки разграничения прав доступа на редактирования и'
        || ' удаление запроса функционала'
      )
    , true
  );
end testEditFeatureAccess;

/* proc: testFeatureStatus
  Проверка установки статуса запроса на новый функционал.
*/
procedure testFeatureStatus
is

  -- Создаваемые пользователи
  operatorId_A integer;
  operatorId_B integer;
  operatorId_V integer;

  -- Создаваемые запросы
  feature_1    integer;
  feature_2    integer;

  -- Id записей обработки запросов
  featureProcessIdInProgress integer;
  featureProcessIdRemarks    integer;

  /*
    Проверка существования записи.
  */
  procedure checkFeatureProcess(
    featureProcessId   integer
    , expectedRowCount integer
    , operatorId       integer
  )
  is
    rc sys_refcursor;
  begin
    rc := pkg_JepRiaShowcase.findFeatureProcess(
      featureProcessId    => featureProcessId
      , operatorId        => operatorId
    );
    pkg_TestUtility.compareRowCount(
      rc
    , expectedRowCount    => expectedRowCount
    , failMessageText     => 'Некорректное число записей в курсоре'
    );
  end checkFeatureProcess;

  /*
    Подготовка сценариев: Создаются пользователи с ролью JrsEditFeature:
    «Пользователь А», «Пользователь Б».  Создается пользователь с ролями
    JrsAssignResponsibleFeature, JrsEditAllFeature: «Пользователь В».
    «Пользователь А» создает «Запрос 1».  «Пользователь В» в качестве
    ответственного за «Запрос 1» назначает «Пользователя Б».
  */
  procedure initTest
  is
  begin
    operatorId_A := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_A'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    operatorId_B := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_B'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    operatorId_V := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_C'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditAllFeature_RoleSName
          , pkg_JepRiaShowcase.AssignResponsibleFea_RoleSName
        )
    );
    feature_1 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 1'
      , featureNameEn   => 'Feature 1'
      , operatorId      => operatorId_A
    );
    feature_2 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 2'
      , featureNameEn   => 'Feature 2'
      , operatorId      => operatorId_A
    );
    pkg_JepRiaShowcase.setFeatureResponsible(
      featureId         => feature_1
      , responsibleId   => operatorId_B
      , operatorId      => operatorId_V
    );
  end initTest;

  /*
    Установка статуса запроса на функционал.

    Параметры:
    operatorId                - оператор, устанавливающий статус
    featureId                 - id запроса на функционал
    featureStatusCode         - код статуса запроса
    expectedException         - ожидаемый текст исключения
    expectedLastStatusCode    - ожидаемый статус последней обработки

    Возврат:
    - id записи обработки функциоанла;
  */
  function setFeatureStatus(
    operatorId                integer
    , featureId               integer
    , featureStatusCode       varchar2
    , expectedException       varchar2 := null
    , expectedLastStatusCode  varchar2 := null
  )
  return integer
  is
    featureProcessId integer;
  begin
    featureProcessId := pkg_JepRiaShowcase.createFeatureProcess(
      featureId           => featureId
      , featureStatusCode => featureStatusCode
      , operatorId        => operatorId
    );
    if expectedLastStatusCode is not null then
      checkFeatureStatusCode(
        operatorId           => operatorId
        , featureId          => featureId
        , expectedStatusCode => expectedLastStatusCode
      );
    end if;
    checkFeatureProcess(
      featureProcessId => featureProcessId
    , expectedRowCount => 1
    , operatorId       => operatorId
    );
    endTestException(
      expectedException   => expectedException
      , inExceptionFlag   => 0
    );
    return featureProcessId;
  exception when others then
    endTestException(
      exceptionText       => logger.getErrorStack()
      , expectedException => expectedException
      , inExceptionFlag   => 1
    );
    return null;
  end setFeatureStatus;

  /*
    Установка статуса запроса на функционал.

    Параметры:
    operatorId                - оператор, устанавливающий статус
    featureId                 - id запроса на функционал
    featureStatusCode         - код статуса запроса
    expectedException         - ожидаемый текст исключения
    expectedLastStatusCode    - ожидаемый статус последней обработки
  */
  procedure setFeatureStatus(
    operatorId                integer
    , featureId               integer
    , featureStatusCode       varchar2
    , expectedException       varchar2 := null
    , expectedLastStatusCode  varchar2 := null
  )
  is
    featureProcessId integer;
  begin
    featureProcessId := setFeatureStatus(
      operatorId                => operatorId
      , featureId               => featureId
      , featureStatusCode       => featureStatusCode
      , expectedException       => expectedException
      , expectedLastStatusCode  => expectedLastStatusCode
    );
  end setFeatureStatus;

  /*
    Удаление записи обработки запроса.

    Параметры:
    featureProcessId          - id записи обработки запроса
    operatorId                - id пользователя для удаления
    expectedException         - ожидаемый текст исключения
  */
  procedure deleteFeatureStatus(
    featureProcessId     integer
    , operatorId         integer
    , expectedException varchar2 := null
  )
  is
  -- deleteFeatureStatus
  begin
    pkg_JepRiaShowcase.deleteFeatureProcess(
      featureProcessId    => featureProcessId
      , operatorId        => operatorId
    );
    checkFeatureProcess(
      featureProcessId => featureProcessId
    , expectedRowCount => 0
    , operatorId       => operatorId
    );
    endTestException(
      expectedException   => expectedException
      , inExceptionFlag   => 0
    );
  exception when others then
    endTestException(
      exceptionText       => logger.getErrorStack()
      , expectedException => expectedException
      , inExceptionFlag   => 1
    );
  end deleteFeatureStatus;

-- testFeatureStatus
begin
  initTest();
  pkg_TestUtility.beginTest(
    'Сценарий 11: Установка статуса ответственным за'
    || ' запрос пользователем'
  );
  -- Пользователь Б для «Запрос 1» добавляет статус «В работе»
  featureProcessIdInProgress := setFeatureStatus(
    operatorId                => operatorId_B
    , featureId               => feature_1
    , featureStatusCode       => pkg_JepRiaShowcase.InProgress_FeatureStatusCode
  );
  pkg_TestUtility.beginTest(
    'Сценарий 12: Установка статуса запроса пользователем с правами'
    || ' редактирования всех запросов'
  );
  -- «Пользователь В» для «Запрос 1» добавляет статус «Работа над ремарками»
  featureProcessIdRemarks := setFeatureStatus(
    operatorId                => operatorId_V
    , featureId               => feature_1
    , featureStatusCode       => pkg_JepRiaShowcase.Remarks_FeatureStatusCode
    , expectedLastStatusCode  => pkg_JepRiaShowcase.Remarks_FeatureStatusCode
  );
  pkg_TestUtility.beginTest(
    'Сценарий 13: Попытка установки статуса создателем запроса'
  );
  -- «Пользователь А» для «Запрос 1» добавляет статус «Отменено»
  setFeatureStatus(
    operatorId                => operatorId_A
    , featureId               => feature_1
    , featureStatusCode       => pkg_JepRiaShowcase.Cancelled_FeatureStatusCode
    , expectedException       =>
    'У Вас нет прав на изменение статуса запроса на функционал!'
  );
  pkg_TestUtility.beginTest(
    'Сценарий 14: Попытка удаления непоследнего статуса запроса'
  );
  -- «Пользователь В» у «Запрос 1» удаляет непоследний статус (статус «В
  -- работе»).
  deleteFeatureStatus(
    operatorId                => operatorId_V
    , featureProcessId        => featureProcessIdInProgress
    , expectedException       =>
    'Удалять можно только запись по последней обработке!'
  );
  pkg_TestUtility.beginTest(
    'Сценарий 15: Удаление последнего статуса запроса'
  );
  -- «Пользователь В» у «Запрос 1» удаляет последний статус (статус «Работа
  -- над ремарками»)
  deleteFeatureStatus(
    operatorId                => operatorId_V
    , featureProcessId        => featureProcessIdRemarks
  );
exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка проверки установки статуса запроса на новый функционал'
      )
    , true
  );
end testFeatureStatus;

/* proc: testUserApi
  Тестирование API для пользовательского интерфейса.
*/
procedure testUserApi
is

  -- Результат выполнения функции
  rc sys_refcursor;

  -- Оператор для вызова API-функций
  operatorId integer := pkg_Operator.getCurrentUserId();

  -- Тестовые данные
  supplierId integer;
  supplierId2 integer;

  goodsId integer;
  goodsId2 integer;

  requestId integer;



  /*
    Проверяет число записей в курсоре.
  */
  procedure checkCursor(
    functionName varchar2
    , expectedRowCount integer := null
  )
  is
  begin
    pkg_TestUtility.compareRowCount(
      rc
      , expectedRowCount => coalesce( expectedRowCount, 0)
      , failMessageText  =>
          functionName || ': Некорректное число записей в курсоре'
    );
  end checkCursor;



  /*
    Проверяет число записей в курсоре по числу записей в таблице.
  */
  procedure checkCursor(
    functionName varchar2
    , tableName varchar2
    , whereSql varchar2 := null
    , filterCondition varchar2 := null
  )
  is

    expectedRowCount integer;

  begin
    execute immediate
'select
  count(*)
from
  ' || tableName || ' t'
|| case when whereSql is not null then
'
where
  ' || whereSql
end
    into
      expectedRowCount
    ;
    pkg_TestUtility.compareRowCount(
      rc
      , expectedRowCount => expectedRowCount
      , filterCondition  => filterCondition
      , failMessageText  =>
          functionName || ': Некорректное число записей в курсоре'
    );
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при сравнении числа записей в курсоре и таблице ('
          || ' tableName="' || tableName || '"'
          || ', whereSql="' || whereSql || '"'
          || ').'
        )
      , true
    );
  end checkCursor;



  /*
    Тест функций %Supplier.
  */
  procedure testSupplierApi
  is
  begin
    supplierId := pkg_JepRiaShowcase.createSupplier(
      supplierName            => '$TEST-Supplier 1'
      , contractFinishDate    => DATE '4099-05-14'
      , exclusiveSupplierFlag => 1
      , privilegeSupplierFlag => 1
      , phoneNumber           => '98-01-01'
      , faxNumber             => '98-02-01'
      , bankBic               => '044585187'
      , recipientName         => 'Test recipient 1'
      , settlementAccount     => '000001'
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_supplier'
      , filterCondition   => '
supplier_id = ''' || supplierId || '''
and supplier_name = ''$TEST-Supplier 1''
and contract_finish_date = DATE ''4099-05-14''
and exclusive_supplier_flag = 1
and privilege_supplier_flag = 1
and phone_number = ''98-01-01''
and fax_number = ''98-02-01''
and bank_bic = ''044585187''
and recipient_name = ''Test recipient 1''
and settlement_account = ''000001''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createSupplier: запись не создана или некорректна'
    );

    pkg_JepRiaShowcase.updateSupplier(
      supplierId              => supplierId
      , supplierName          => '$TEST-Supplier 2'
      , contractFinishDate    => DATE '4099-05-15'
      , exclusiveSupplierFlag => 0
      , privilegeSupplierFlag => 0
      , phoneNumber           => '98-01-02'
      , faxNumber             => '98-02-02'
      , bankBic               => '044525448'
      , recipientName         => 'Test recipient 2'
      , settlementAccount     => '000002'
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_supplier'
      , filterCondition   => '
supplier_id = ''' || supplierId || '''
and supplier_name = ''$TEST-Supplier 2''
and contract_finish_date = DATE ''4099-05-15''
and exclusive_supplier_flag = 0
and privilege_supplier_flag = 0
and phone_number = ''98-01-02''
and fax_number = ''98-02-02''
and bank_bic = ''044525448''
and recipient_name = ''Test recipient 2''
and settlement_account = ''000002''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'updateSupplier: запись не изменена или некорректна'
    );

    rc := pkg_JepRiaShowcase.findSupplier(
      supplierName            => '$TEST-Supplier 1'
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findSupplier: absent name', 0);

    rc := pkg_JepRiaShowcase.findSupplier(
      supplierId              => supplierId
      , operatorId            => operatorId
    );
    checkCursor( 'findSupplier: id', 1);

    rc := pkg_JepRiaShowcase.findSupplier(
      supplierId              => supplierId
      , supplierName          => '$test-SUPP%2'
      , contractFinishDateFrom  => DATE '4099-05-01'
      , contractFinishDateTo    => DATE '4099-05-28'
      , exclusiveSupplierFlag => 0
      , privilegeSupplierFlag => 0
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findSupplier: all args', 1);

    rc := pkg_JepRiaShowcase.findSupplier(
      supplierName            => '$TEST-SUPP%'
      , contractFinishDateTo  => DATE '4099-05-15'
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findSupplier: name/dateTo', 1);

    rc := pkg_JepRiaShowcase.findSupplier(
      contractFinishDateFrom  => DATE '4099-05-14'
      , exclusiveSupplierFlag => 0
      , privilegeSupplierFlag => 0
      , operatorId            => operatorId
    );
    checkCursor( 'findSupplier: dateFrom/exlFlag', 1);

    pkg_JepRiaShowcase.deleteSupplier(
      supplierId              => supplierId
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_supplier'
      , filterCondition   => '
supplier_id = ''' || supplierId || '''
'
      , expectedRowCount  => 0
      , failMessageText   =>
          'deleteSupplier: запись не была удалена'
    );

    -- Создаем данные для последующих тестов
    supplierId := pkg_JepRiaShowcase.createSupplier(
      supplierName            => '$TEST-Supplier 1'
      , contractFinishDate    => DATE '4099-05-14'
      , exclusiveSupplierFlag => 1
      , privilegeSupplierFlag => 1
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_supplier'
      , filterCondition   => 'supplier_id = ''' || supplierId || ''''
      , expectedRowCount  => 1
      , failMessageText   =>
          'createSupplier: T1: запись не создана'
    );
    supplierId2 := pkg_JepRiaShowcase.createSupplier(
      supplierName            => '$TEST-Supplier 2'
      , contractFinishDate    => DATE '4099-05-01'
      , exclusiveSupplierFlag => 1
      , privilegeSupplierFlag => 1
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_supplier'
      , filterCondition   => 'supplier_id = ''' || supplierId2 || ''''
      , expectedRowCount  => 1
      , failMessageText   =>
          'createSupplier: T2: запись не создана'
    );
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций %Supplier.'
        )
      , true
    );
  end testSupplierApi;



  /*
    Тест функций %Goods.
  */
  procedure testGoodsApi
  is

    parentGoodsCatalogId integer;
    goodsCatalogId integer;
    maxGoodsCatalogId integer;
    otherGoodsCatalogId integer;

  begin
    goodsId := pkg_JepRiaShowcase.createGoods(
      supplierId                => supplierId2
      , goodsName               => '$TEST-Молоко, 1 л.'
      , goodsTypeCode           => 'FOOD'
      , unitCode                => 'L'
      , purchasingPrice         => 20.75
      , motivationTypeCode      => 'MONTH'
      , goodsPhotoMimeType      => 'jpeg'
      , goodsPhotoExtension     => 'jpg'
      , goodsPortfolioMimeType  => 'text-rtf'
      , goodsPortfolioExtension => 'rtf'
      , operatorId              => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
and goods_name = ''$TEST-Молоко, 1 л.''
and goods_type_code = ''FOOD''
and unit_code = ''L''
and purchasing_price = 20.75
and motivation_type_code = ''MONTH''
and goods_photo_mime_type = ''jpeg''
and goods_photo_extension = ''jpg''
and goods_portfolio_mime_type = ''text-rtf''
and goods_portfolio_extension = ''rtf''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createGoods: all args: запись не создана или некорректна'
    );

    pkg_JepRiaShowcase.updateGoods(
      goodsId                   => goodsId
      , supplierId              => supplierId
      , goodsName               => '$TEST-Мыло детское'
      , goodsTypeCode           => 'INDUSTRIAL'
      , unitCode                => 'ITEM'
      , purchasingPrice         => 18.00
      , motivationTypeCode      => 'QUARTER'
      , goodsPhotoMimeType      => 'image-png'
      , goodsPhotoExtension     => 'png'
      , goodsPortfolioMimeType  => 'text-plain'
      , goodsPortfolioExtension => 'txt'
      , operatorId              => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
and goods_name = ''$TEST-Мыло детское''
and goods_type_code = ''INDUSTRIAL''
and unit_code = ''ITEM''
and purchasing_price = 18.00
and motivation_type_code = ''QUARTER''
and goods_photo_mime_type = ''image-png''
and goods_photo_extension = ''png''
and goods_portfolio_mime_type = ''text-plain''
and goods_portfolio_extension = ''txt''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'updateGoods: запись не изменена или некорректна'
    );

    -- %GoodsSegmentLink
    pkg_JepRiaShowcase.createGoodsSegmentLink(
      goodsId                 => goodsId
      , goodsSegmentCode      => 'EVERYDAY'
      , operatorId            => operatorId
    );
    pkg_JepRiaShowcase.createGoodsSegmentLink(
      goodsId                 => goodsId
      , goodsSegmentCode      => 'HOME'
      , operatorId            => operatorId
    );
    pkg_JepRiaShowcase.createGoodsSegmentLink(
      goodsId                 => goodsId
      , goodsSegmentCode      => 'TOY'
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods_segment_link'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
and goods_segment_code in ( ''EVERYDAY'', ''HOME'',  ''TOY'')
'
      , expectedRowCount  => 3
      , failMessageText   =>
          'createGoodsSegmentLink: записи не добавлены или некорректны'
    );
    pkg_JepRiaShowcase.deleteGoodsSegmentLink(
      goodsId                 => goodsId
      , goodsSegmentCode      => 'TOY'
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods_segment_link'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
and goods_segment_code in ( ''EVERYDAY'', ''HOME'',  ''TOY'')
'
      , expectedRowCount  => 2
      , failMessageText   =>
          'deleteGoodsSegmentLink: запись не удалена'
    );
    rc := pkg_JepRiaShowcase.getGoodsSegmentLink(
      goodsId                 => goodsId
      , operatorId            => operatorId
    );
    checkCursor( 'getGoodsSegmentLink', 2);

    -- %GoodsCatalogLink
    select
      min( gc.parent_goods_catalog_id)
        keep( dense_rank first order by gc.goods_catalog_id)
      , min( gc.goods_catalog_id)
        keep( dense_rank first order by gc.goods_catalog_id)
      , max( gc.goods_catalog_id)
    into parentGoodsCatalogId, goodsCatalogId, maxGoodsCatalogId
    from
      jrs_goods_catalog gc
    where
      gc.parent_goods_catalog_id is not null
    ;
    select
      min( gc.goods_catalog_id)
    into otherGoodsCatalogId
    from
      jrs_goods_catalog gc
    where
      gc.goods_catalog_id not in (
        parentGoodsCatalogId
        , goodsCatalogId
        , maxGoodsCatalogId
      )
    ;
    pkg_JepRiaShowcase.setGoodsCatalogLink(
      goodsId                 => goodsId
      , goodsCatalogIdList    =>
          parentGoodsCatalogId
          || ',' || otherGoodsCatalogId
          || ', ,, '
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods_catalog_link'
      , filterCondition   => 'goods_id = ''' || goodsId || ''''
      , expectedRowCount  => 2
      , failMessageText   =>
          'setGoodsCatalogLink: некорректное число записей'
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods_catalog_link'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
and goods_catalog_id in (
  ''' || parentGoodsCatalogId || '''
  , ''' || otherGoodsCatalogId || '''
)
'
      , expectedRowCount  => 2
      , failMessageText   =>
          'setGoodsCatalogLink: записи некорректны'
    );

    pkg_JepRiaShowcase.setGoodsCatalogLink(
      goodsId                 => goodsId
      , goodsCatalogIdList    =>
          parentGoodsCatalogId
          || ',' || goodsCatalogId
          || ',' || maxGoodsCatalogId
          || ', ,, '
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods_catalog_link'
      , filterCondition   => 'goods_id = ''' || goodsId || ''''
      , expectedRowCount  => 3
      , failMessageText   =>
          'setGoodsCatalogLink(2): некорректное число записей'
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods_catalog_link'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
and goods_catalog_id in (
  ''' || parentGoodsCatalogId || '''
  , ''' || goodsCatalogId || '''
  , ''' || maxGoodsCatalogId || '''
)
'
      , expectedRowCount  => 3
      , failMessageText   =>
          'setGoodsCatalogLink(2): записи некорректны'
    );

    -- getGoodsCatalog
    rc := pkg_JepRiaShowcase.getGoodsCatalog();
    checkCursor(
      'getGoodsCatalog( no args)'
      , 'jrs_goods_catalog'
      , 'parent_goods_catalog_id is null'
      ,
-- здесь и ниже не проверяем условие по descendant_goods_link_flag из-за
-- ошибки в pkg_TestUtility
'has_child_flag = 1
and goods_link_flag is null
--and descendant_goods_link_flag is null
'
    );

    rc := pkg_JepRiaShowcase.getGoodsCatalog(
      parentGoodsCatalogId  => null
      , goodsId             => goodsId
    );
    checkCursor(
      'getGoodsCatalog( goodsId)'
      , 'jrs_goods_catalog'
      , 'parent_goods_catalog_id is null'
      ,
'has_child_flag = 1
and goods_link_flag in ( 0, 1)
--and descendant_goods_link_flag in ( 0, 1)
'
    );

    select
      gc.parent_goods_catalog_id
    into otherGoodsCatalogId
    from
      jrs_goods_catalog gc
    where
      gc.goods_catalog_id = parentGoodsCatalogId
    ;
    rc := pkg_JepRiaShowcase.getGoodsCatalog(
      parentGoodsCatalogId  => otherGoodsCatalogId
      , goodsId             => goodsId
    );
    pkg_TestUtility.compareRowCount(
      rc
      , expectedRowCount  => 1
      , filterCondition   =>
'goods_catalog_id = ' || parentGoodsCatalogId || '
and has_child_flag = 1
and goods_link_flag = 1
--and descendant_goods_link_flag = 1
'
      , failMessageText   =>
          'getGoodsCatalog( parent): Некорректные данные записи'
    );


    -- findGoods
    rc := pkg_JepRiaShowcase.findGoods(
      maxRowCount             => 1
      , operatorId            => operatorId
    );
    checkCursor( 'findGoods: maxRowCount', 1);

    rc := pkg_JepRiaShowcase.findGoods(
      goodsName               => '$TEST-???'
      , operatorId            => operatorId
    );
    checkCursor( 'findGoods: absent name', 0);

    rc := pkg_JepRiaShowcase.findGoods(
      goodsIdList             => to_char( goodsId)
      , operatorId            => operatorId
    );
    checkCursor( 'findGoods: id', 1);

    rc := pkg_JepRiaShowcase.findGoods(
      goodsIdList               => ',' || to_char( goodsId) || ',,,'
      , supplierId              => supplierId
      , goodsName               => '$test-%мыло%'
      , goodsTypeCode           => 'INDUSTRIAL'
      , goodsSegmentCodeList    => 'TOY,HOME'
      , goodsCatalogIdList      => to_char( parentGoodsCatalogId) || ',-1'
      , maxRowCount             => 5
      , operatorId              => operatorId
    );
    checkCursor( 'findGoods: all args', 1);

    rc := pkg_JepRiaShowcase.findGoods(
      supplierId              => supplierId
      , goodsName             => '$test-%'
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findGoods: supplier/name', 1);

    rc := pkg_JepRiaShowcase.findGoods(
      goodsName               => '$test-%'
      , goodsCatalogIdList    => '-5,' || to_char( maxGoodsCatalogId)
      , operatorId            => operatorId
    );
    checkCursor( 'findGoods: name/catalog', 1);

    rc := pkg_JepRiaShowcase.getGoods(
      goodsName               => '$test-%'
    );
    checkCursor( 'getGoods: name', 1);

    rc := pkg_JepRiaShowcase.getGoods(
      maxRowCount             => 1
    );
    checkCursor( 'getGoods: maxRowCount', 1);

    pkg_JepRiaShowcase.deleteGoods(
      goodsId                 => goodsId
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
'
      , expectedRowCount  => 0
      , failMessageText   =>
          'deleteGoods: запись не была удалена'
    );

    -- Создаем данные для последующих тестов
    goodsId := pkg_JepRiaShowcase.createGoods(
      supplierId                => supplierId
      , goodsName               => '$TEST-Молоко, 1 л.'
      , goodsTypeCode           => 'FOOD'
      , unitCode                => 'L'
      , purchasingPrice         => 20.75
      , motivationTypeCode      => 'MONTH'
      , goodsPhotoMimeType      => 'jpeg'
      , goodsPhotoExtension     => 'jpg'
      , goodsPortfolioMimeType  => 'text-rtf'
      , goodsPortfolioExtension => 'rtf'
      , operatorId              => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods'
      , filterCondition   => '
goods_id = ''' || goodsId || '''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createGoods: test1: запись не создана или некорректна'
    );
    goodsId2 := pkg_JepRiaShowcase.createGoods(
      supplierId                => supplierId
      , goodsName               => '$TEST-Мыло детское'
      , goodsTypeCode           => 'INDUSTRIAL'
      , unitCode                => 'ITEM'
      , purchasingPrice         => 18.00
      , operatorId              => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_goods'
      , filterCondition   => '
goods_id = ''' || goodsId2 || '''
and motivation_type_code = ''USUAL''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createGoods: test2: запись не создана или некорректна'
    );

    -- findGoods для нескольких записей
    rc := pkg_JepRiaShowcase.findGoods(
      goodsIdList             => goodsId2 || ',,' || goodsId2 || ',' || goodsId
      , operatorId            => operatorId
    );
    checkCursor( 'findGoods: few id', 2);
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций %Goods.'
        )
      , true
    );
  end testGoodsApi;



  /*
    Тест функций %ShopGoods.
  */
  procedure testShopGoodsApi
  is

    shopId integer;
    shopGoodsId integer;

  begin
    select
      min( t.shop_id)
    into shopId
    from
      jrs_shop t
    ;
    shopGoodsId := pkg_JepRiaShowcase.createShopGoods(
      shopId                  => shopId
      , goodsId               => goodsId
      , goodsQuantity         => 5.0001
      , sellPrice             => 85.88
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_shop_goods'
      , filterCondition   => '
shop_goods_id = ''' || shopGoodsId || '''
and shop_id = ''' || shopId || '''
and goods_id = ''' || goodsId || '''
and goods_quantity = 5.0001
and sell_price = 85.88
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createShopGoods: запись не создана или некорректна'
    );

    pkg_JepRiaShowcase.updateShopGoods(
      shopGoodsId             => shopGoodsId
      , goodsQuantity         => 3
      , sellPrice             => 60
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_shop_goods'
      , filterCondition   => '
shop_goods_id = ''' || shopGoodsId || '''
and goods_quantity = 3
and sell_price = 60
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'updateShopGoods: запись не изменена или некорректна'
    );

    rc := pkg_JepRiaShowcase.findShopGoods(
      maxRowCount             => 1
      , operatorId            => operatorId
    );
    checkCursor( 'findShopGoods: maxRowCount', 1);

    rc := pkg_JepRiaShowcase.findShopGoods(
      shopGoodsId             => shopGoodsId
      , operatorId            => operatorId
    );
    checkCursor( 'findShopGoods: id', 1);

    rc := pkg_JepRiaShowcase.findShopGoods(
      shopGoodsId             => shopGoodsId
      , shopId                => shopId
      , goodsId               => goodsId
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findShopGoods: all args', 1);

    rc := pkg_JepRiaShowcase.findShopGoods(
      goodsId                 => goodsId
      , operatorId            => operatorId
    );
    checkCursor( 'findShopGoods: goods', 1);

    rc := pkg_JepRiaShowcase.getShop(
      maxRowCount             => 1
    );
    checkCursor( 'getShop: maxRowCount', 1);

    pkg_JepRiaShowcase.deleteShopGoods(
      shopGoodsId             => shopGoodsId
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_shop_goods'
      , filterCondition   => '
shop_goods_id = ''' || shopGoodsId || '''
'
      , expectedRowCount  => 0
      , failMessageText   =>
          'deleteShopGoods: запись не была удалена'
    );
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций %ShopGoods.'
        )
      , true
    );
  end testShopGoodsApi;



  /*
    Тест функций %Request.
  */
  procedure testRequestApi
  is

    shopId integer;

  begin
    select
      min( t.shop_id)
    into shopId
    from
      jrs_shop t
    ;
    requestId := pkg_JepRiaShowcase.createRequest(
      shopId                  => shopId
      , goodsId               => goodsId2
      , goodsQuantity         => 5.0001
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_request'
      , filterCondition   => '
request_id = ''' || requestId || '''
and shop_id = ''' || shopId || '''
and goods_id = ''' || goodsId2 || '''
and goods_quantity = 5.0001
and request_status_code = ''NEW''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createRequest: запись не создана или некорректна'
    );

    pkg_JepRiaShowcase.updateRequest(
      requestId               => requestId
      , requestStatusCode     => 'REJECTED'
      , goodsId               => goodsId
      , goodsQuantity         => 3
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_request'
      , filterCondition   => '
request_id = ''' || requestId || '''
and request_status_code = ''REJECTED''
and goods_id = ''' || goodsId || '''
and goods_quantity = 3
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'updateRequest: запись не изменена или некорректна'
    );

    rc := pkg_JepRiaShowcase.findRequest(
      maxRowCount             => 1
      , operatorId            => operatorId
    );
    checkCursor( 'findRequest: maxRowCount', 1);

    rc := pkg_JepRiaShowcase.findRequest(
      requestId               => requestId
      , operatorId            => operatorId
    );
    checkCursor( 'findRequest: id', 1);

    rc := pkg_JepRiaShowcase.findRequest(
      requestId               => requestId
      , shopId                => shopId
      , requestDateFrom       => trunc( sysdate)
      , requestDateTo         => trunc( sysdate)
      , requestStatusCode     => 'REJECTED'
      , goodsId               => goodsId
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findRequest: all args', 1);

    rc := pkg_JepRiaShowcase.findRequest(
      goodsId                 => goodsId
      , operatorId            => operatorId
    );
    checkCursor( 'findRequest: goods', 1);

    pkg_JepRiaShowcase.deleteRequest(
      requestId               => requestId
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_request'
      , filterCondition   => '
request_id = ''' || requestId || '''
'
      , expectedRowCount  => 0
      , failMessageText   =>
          'deleteRequest: запись не была удалена'
    );

    -- Создаем данные для последующих тестов
    requestId := pkg_JepRiaShowcase.createRequest(
      shopId                  => shopId
      , goodsId               => goodsId
      , goodsQuantity         => 4
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_request'
      , filterCondition   => '
request_id = ''' || requestId || '''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createRequest: test1: запись не создана или некорректна'
    );
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций %Request.'
        )
      , true
    );
  end testRequestApi;



  /*
    Тест функций %RequestProcess.
  */
  procedure testRequestProcessApi
  is

    requestProcessId integer;

  begin
    requestProcessId := pkg_JepRiaShowcase.createRequestProcess(
      requestId               => requestId
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_request_process'
      , filterCondition   => '
request_process_id = ''' || requestProcessId || '''
and request_id = ''' || requestId || '''
'
      , expectedRowCount  => 1
      , failMessageText   =>
          'createRequestProcess: запись не создана или некорректна'
    );

    rc := pkg_JepRiaShowcase.findRequestProcess(
      maxRowCount             => 1
      , operatorId            => operatorId
    );
    checkCursor( 'findRequestProcess: maxRowCount', 1);

    rc := pkg_JepRiaShowcase.findRequestProcess(
      requestProcessId        => requestProcessId
      , operatorId            => operatorId
    );
    checkCursor( 'findRequestProcess: id', 1);

    rc := pkg_JepRiaShowcase.findRequestProcess(
      requestProcessId        => requestProcessId
      , requestId             => requestId
      , dateInsFrom           => trunc( sysdate)
      , dateInsTo             => trunc( sysdate)
      , insertOperatorId      => operatorId
      , maxRowCount           => 5
      , operatorId            => operatorId
    );
    checkCursor( 'findRequestProcess: all args', 1);

    rc := pkg_JepRiaShowcase.findRequestProcess(
      requestId               => requestId
      , operatorId            => operatorId
    );
    checkCursor( 'findRequestProcess: requestId', 1);

    pkg_JepRiaShowcase.deleteRequestProcess(
      requestProcessId        => requestProcessId
      , operatorId            => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_request_process'
      , filterCondition   => '
request_process_id = ''' || requestProcessId || '''
'
      , expectedRowCount  => 0
      , failMessageText   =>
          'deleteRequestProcess: запись не была удалена'
    );
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций %RequestProcess.'
        )
      , true
    );
  end testRequestProcessApi;



  /*
    Тест функций заполнения списков get%
  */
  procedure testGetFunction
  is

    bankBic varchar2(100);

  begin
    rc := pkg_JepRiaShowcase.getBank(
      bankBic => '$'
    );
    checkCursor( 'getBank[ bad bic]', 0);

    select
      t.bic
    into bankBic
    from
      v_bic_bank t
    where
      rownum <= 1
    ;
    rc := pkg_JepRiaShowcase.getBank(
      bankBic => bankBic
    );
    checkCursor( 'getBank[ ok bic]', 1);

    rc := pkg_JepRiaShowcase.getBank(
      bankBic         => '%'
      , maxRowCount   => 3
    );
    checkCursor( 'getBank[ all params]', 3);

    rc := pkg_JepRiaShowcase.getBank();
    checkCursor( 'getBank[ all]', 'v_bic_bank');
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций get%.'
        )
      , true
    );
  end testGetFunction;



  /*
    Тест функций %Feature.
  */
  procedure testFeature
  is

     featureId integer;

     featureId3 integer;


  begin
    featureId := pkg_JepRiaShowcase.createFeature(
      featureName     => '$TEST-Запрос'
      , featureNameEn => '$TEST-Request-en'
      , operatorId    => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_feature'
      , filterCondition   => '
feature_id = ' || coalesce( to_char( featureId), 'null') || '
and feature_name = ''$TEST-Запрос''
and feature_name_en = ''$TEST-Request-en'''
       , expectedRowCount  => 1
       , failMessageText   =>
'createFeature: запись не создана или некорректна'
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'v_jrs_feature_lob'
      , filterCondition   =>
        'feature_id = ' || coalesce( to_char( featureId), 'null')
      , expectedRowCount  => 1
      , failMessageText   =>
        'v_jrs_feature_lob: не найдена запись'
    );
    pkg_JepRiaShowcase.updateFeature(
      featureId        => featureId
      , featureName    => '$TEST-Запрос2'
      , featureNameEn  => '$TEST-Request2-en'
      , operatorId     => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_feature'
      , filterCondition   => '
feature_id = ' || coalesce( to_char( featureId), 'null') || '
and feature_name = ''$TEST-Запрос2''
and feature_name_en = ''$TEST-Request2-en'''
       , expectedRowCount  => 1
       , failMessageText   =>
'updateFeature: запись не изменена или некорректна'
    );
    featureId3 := pkg_JepRiaShowcase.createFeature(
      featureName     => '$TEST-Запрос3'
      , featureNameEn => '$TEST-Request3-en'
      , operatorId    => operatorId
    );

    -- Поиск по id
    rc := pkg_JepRiaShowcase.findFeature(
      featureId           => featureId
    );
    checkCursor( 'findFeature: featureId', 1);
    -- Поиск по наименованию на английском ( проверка условия "ИЛИ")
    rc := pkg_JepRiaShowcase.findFeature(
      featureName         => '$%$%$%$%$%$'
      , featureNameEn     => '$TEST-Request2-en'
      , dateInsFrom       => sysdate - 1
      , dateInsTo         => sysdate + 1
      , operatorId        => operatorId
    );
    checkCursor( 'findFeature: featureNameEn: OR', 1);
    -- Поиск по наименованию ( проверка условия "И")
    rc := pkg_JepRiaShowcase.findFeature(
      featureId           => featureId
      , featureName       => '$%$%$%$%$%$'
      , operatorId        => operatorId
    );
    checkCursor( 'findFeature: featureName: AND', 0);
    -- Поиск по date_ins
    rc := pkg_JepRiaShowcase.findFeature(
      dateInsFrom         => sysdate + 1
      , featureName       => ''
      , operatorId        => operatorId
    );
    checkCursor( 'findFeature: fromDateIns', 0);
    -- Поиск по date_ins
    rc := pkg_JepRiaShowcase.findFeature(
      dateInsTo           => sysdate - 1
      , featureName       => '$TEST-Запрос2'
      , operatorId        => operatorId
    );
    checkCursor( 'findFeature: toDateIns', 0);
    -- Поиск по date_ins и featureName
    rc := pkg_JepRiaShowcase.findFeature(
      dateInsTo           => sysdate + 1
      , featureName       => '$TEST-Запрос2'
      , operatorId        => operatorId
    );
    checkCursor( 'findFeature: toDateIns', 1);
    rc := pkg_JepRiaShowcase.findFeature(
      dateInsTo           => sysdate + 1
      , featureName       => '$TEST-Запрос2'
      , operatorId        => operatorId
    );
    checkCursor( 'findFeature: toDateIns', 1);
    rc := pkg_JepRiaShowcase.findFeature(
      maxRowCount         => 1
    );
    checkCursor( 'findFeature: rowCount', 1);
    pkg_JepRiaShowcase.deleteFeature(
      featureId           => featureId
      , operatorId        => operatorId
    );
    pkg_JepRiaShowcase.deleteFeature(
      featureId           => featureId3
      , operatorId        => operatorId
    );
    pkg_TestUtility.compareRowCount(
      tableName           => 'jrs_feature'
      , filterCondition   => '
feature_id = ' || coalesce( to_char( featureId), 'null') || '
or feature_id = ' || coalesce( to_char( featureId3), 'null')
      , expectedRowCount  => 0
      , failMessageText   =>
'updateFeature: записи не удалены'
    );
    for i in 1..1000 loop
      featureId := pkg_JepRiaShowcase.createFeature(
        featureName     => '$TEST-Запрос' || to_char( i)
        , featureNameEn => '$TEST-Request-en' || to_char( i)
        , operatorId    => operatorId
      );
    end loop;
    rc := pkg_JepRiaShowcase.findFeature(
      featureName => '$TEST-Запрос1'
    );
    checkCursor( 'findFeature: find performance: count', 1);
    for testFeature in (
    select
      feature_id
    from
      jrs_feature
    where
      feature_name like '$TEST-Запрос%'
    )
    loop
      pkg_JepRiaShowcase.deleteFeature(
        featureId    => testFeature.feature_id
        , operatorId => operatorId
      );
    end loop;
    -- Одна секунда на все тесты
    if pkg_TestUtility.getTestTimeSecond() > 1 then
      pkg_TestUtility.failTest(
        failMessageText =>
          'find performance test : '
          || to_char( pkg_TestUtility.getTestTimeSecond(), 'FM9990.000') || ' сек.'
      );
    end if;
  exception when others then
    raise_application_error(
      pkg_Error.ErrorStackInfo
      , logger.errorStack(
          'Ошибка при тестировании функций %Feature.'
        )
      , true
    );
  end testFeature;


-- testUserApi
begin
  pkg_TestUtility.beginTest(
    'user API'
  );
  testSupplierApi();
  testGoodsApi();
  testShopGoodsApi();
  testRequestApi();
  testRequestProcessApi();
  testGetFunction();
  testFeature();
  pkg_TestUtility.endTest();
  rollback;
exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка при тестировании API для пользовательского интерфейса.'
      )
    , true
  );
end testUserApi;

/* proc: testFeatureOperator
  Тест пользователей запросов на функционал.
*/
procedure testFeatureOperator
is

  rc sys_refcursor;

  featureOperatorId integer;

  /*
    Очистка пользователя.
  */
  procedure clearFeatureOperator
  is
  begin
    pkg_JepRiaShowcase.deleteFeatureOperator(
      featureOperatorId => pkg_Operator.getCurrentUserId()
      , operatorId      => pkg_Operator.getCurrentUserId()
    );
  end clearFeatureOperator;

-- testFeatureOperator
begin
  pkg_TestUtility.beginTest( 'Сценарий 18: Дата создания записи пользователя запросов');
  clearFeatureOperator();
  featureOperatorId := pkg_JepRiaShowcase.createFeatureOperator(
    featureOperatorId => pkg_Operator.getCurrentUserId()
    , operatorId      => pkg_Operator.getCurrentUserId()
  );
  rc := pkg_JepRiaShowcase.findFeatureOperator(
    featureOperatorId => featureOperatorId
    , operatorId      => pkg_Operator.getCurrentUserId()
  );
  pkg_TestUtility.compareRowCount(
    rc
    , expectedRowCount => 1
    -- Создано менее минуты назад
    , filterCondition => 'date_ins >= sysdate - 1/24/60'
    , failMessageText  =>
        'Новая запись не найдена'
  );
  pkg_TestUtility.endTest();
end testFeatureOperator;

/* proc: testFindFeature
  Тест поиска запросов на функционал.
*/
procedure testFindFeature
is

  -- Создаваемые пользователи
  operatorId_A integer;
  operatorId_B integer;

  -- Создаваемые запросы
  feature_1    integer;
  feature_2    integer;
  feature_3    integer;

  -- Результат выполнения функции
  rc           sys_refcursor;

  /*
    Подготовка сценариев: Создаются пользователи с ролью JrsEditFeature:
    «Пользователь А», «Пользователь Б».
    «Пользователь А» создает «Запрос 1» и «Запрос 2».
    «Пользователь Б» создает «Запрос 3».
  */
  procedure initTest
  is
    featureProcessId integer;
  begin
    operatorId_A := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_A'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
          , pkg_JepRiaShowcase.EditAllFeature_RoleSName
        )
    );
    operatorId_B := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_B'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    feature_1 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 1'
      , featureNameEn   => 'Test_feature_1'
      , operatorId      => operatorId_A
    );
    feature_2 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 2'
      , featureNameEn   => 'Test_feature_2'
      , operatorId      => operatorId_A
    );
    feature_3 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 3'
      , featureNameEn   => 'Test_feature_3'
      , operatorId      => operatorId_B
    );
    featureProcessId := pkg_JepRiaShowcase.createFeatureProcess(
      featureId           => feature_1
      , featureStatusCode => pkg_JepRiaShowcase.Assigned_FeatureStatusCode
      , operatorId        => operatorId_A
    );
    featureProcessId := pkg_JepRiaShowcase.createFeatureProcess(
      featureId           => feature_3
      , featureStatusCode => pkg_JepRiaShowcase.InProgress_FeatureStatusCode
      , operatorId        => operatorId_A
    );
  end initTest;

  /*
    Проверяет число записей в курсоре.
  */
  procedure checkCursor(
    functionName varchar2
    , expectedRowCount integer := null
  )
  is
  begin
    pkg_TestUtility.compareRowCount(
      rc                 => rc
      , expectedRowCount => coalesce( expectedRowCount, 0)
      , failMessageText  =>
          functionName
          || ': Некорректное число записей в курсоре'
    );
  end checkCursor;


  /*
    Тест функции findFeature по автору.
  */
  procedure findFeatureByAuthor
  is
  -- findFeatureByAuthor
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 19: Поиск по автору создания запроса'
    );

    -- feature_1
    rc := pkg_JepRiaShowcase.findFeature(
      featureId       => feature_1
      , authorId      => operatorId_A
      , operatorId    => operatorId_B
    );
    checkCursor( 'findFeatureByAuthor: rowCount', 1);

    -- feature_2
    rc := pkg_JepRiaShowcase.findFeature(
      featureId       => feature_2
      , authorId      => operatorId_A
      , operatorId    => operatorId_B
    );
    checkCursor( 'findFeatureByAuthor: rowCount', 1);

    -- feature_3
    rc := pkg_JepRiaShowcase.findFeature(
      featureId       => feature_3
      , authorId      => operatorId_A
      , operatorId    => operatorId_B
    );
    checkCursor( 'findFeatureByAuthor: rowCount', 0);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );
  end findFeatureByAuthor;

  /*
    Тест функции findFeature по статусам запроса.
  */
  procedure findFeatureByStatusCode
  is
    featureStatusCodeList varchar2(1000) :=
      pkg_JepRiaShowcase.Assigned_FeatureStatusCode || ';'
      || pkg_JepRiaShowcase.InProgress_FeatureStatusCode;
  -- findFeatureByStatusCode
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 20: Поиск по нескольким статусам запроса'
    );

    -- feature_1
    rc := pkg_JepRiaShowcase.findFeature(
      featureId               => feature_1
      , featureStatusCodeList => featureStatusCodeList
      , operatorId            => operatorId_B
    );
    checkCursor( 'findFeatureByAuthor: rowCount', 1);

    -- feature_2
    rc := pkg_JepRiaShowcase.findFeature(
      featureId               => feature_2
      , featureStatusCodeList => featureStatusCodeList
      , operatorId            => operatorId_B
    );
    checkCursor( 'findFeatureByAuthor: rowCount', 0);

    -- feature_3
    rc := pkg_JepRiaShowcase.findFeature(
      featureId               => feature_3
      , featureStatusCodeList => featureStatusCodeList
      , operatorId            => operatorId_B
    );
    checkCursor( 'findFeatureByAuthor: rowCount', 1);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );
  end findFeatureByStatusCode;

-- testFindFeature
begin

  -- Подготовка сценариев
  initTest();

  -- Сценарий 19
  findFeatureByAuthor();

  -- Сценарий 20
  findFeatureByStatusCode();

exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка при тестировании поиска запросов на функционал.'
      )
    , true
  );
end testFindFeature;

/* proc: testSetFeatureWorkSequence
  Тест установки порядка выполнения запросов на функционал.
*/
procedure testSetFeatureWorkSequence
is

  -- Создаваемые пользователи
  operatorId_A integer;
  operatorId_B integer;
  operatorId_C integer;

  -- Создаваемые запросы
  feature_1    integer;
  feature_2    integer;
  feature_3    integer;

  -- Результат выполнения функции
  rc           sys_refcursor;

  /*
    Подготовка сценариев:
    Создаются пользователи с ролью JrsEditFeature: «Пользователь А», «Пользователь Б».
    Создается пользователь с ролью JrsAssignWorkSequenceFeature: «Пользователь В».
    «Пользователь А» создает запросы на новый функционал «Запрос 1» и «Запрос 2».
    «Пользователь Б» создает запросы на новый функционал «Запрос 3».
  */
  procedure initTest
  is
    featureProcessId integer;
  begin
    operatorId_A := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_A'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    operatorId_B := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_B'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
          , pkg_JepRiaShowcase.EditAllFeature_RoleSName
        )
    );
    operatorId_C := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_C'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.AssignWrkSequenceFea_RoleSName
        )
    );
    feature_1 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 1'
      , featureNameEn   => 'Test_feature_1'
      , operatorId      => operatorId_A
    );
    feature_2 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 2'
      , featureNameEn   => 'Test_feature_2'
      , operatorId      => operatorId_A
    );
    feature_3 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 3'
      , featureNameEn   => 'Test_feature_3'
      , operatorId      => operatorId_B
    );
  end initTest;

  /*
    Проверяет число записей в курсоре.
  */
  procedure checkCursor(
    functionName varchar2
    , expectedRowCount integer := null
  )
  is
  begin
    pkg_TestUtility.compareRowCount(
      rc                 => rc
      , expectedRowCount => coalesce( expectedRowCount, 0)
      , failMessageText  =>
          functionName
          || ': Некорректное число записей в курсоре'
    );
  end checkCursor;

  /*
    Тест процедуры setFeatureWorkSequence при недостаточности прав.
  */
  procedure setWorkSequenceWithoutAccess
  is

    expectedException varchar2(100) := 'нет прав на выполнение данной операции';

  -- setWorkSequenceWithoutAccess
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 24: Попытка установки порядка выполенния при недостаточности прав'
    );

    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId => feature_1
      , workSequence => 1
      , operatorId => operatorId_A
    );

    endTestException(
      expectedException   => expectedException
      , inExceptionFlag   => 0
    );
  exception
    when others then
      endTestException(
        expectedException   => expectedException
        , inExceptionFlag   => 1
      );
  end setWorkSequenceWithoutAccess;

  /*
    Тест процедуры setFeatureWorkSequence при изменении порядка выполнения запроса
  */
  procedure setWorkSequenceLessOne
  is

  -- setWorkSequenceLessOne
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 25. Установка порядка выполнения меньше 1'
    );

    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId => feature_1
      , workSequence => 0
      , operatorId => operatorId_C
    );

    -- feature_1
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_1
      , workSequenceFrom => 1
      , workSequenceTo   => 1
      , operatorId       => operatorId_B
    );
    checkCursor( 'setWorkSequenceLessOne: rowCount', 1);

    -- проверка статуса
    rc := pkg_JepRiaShowcase.findFeatureProcess(
      featureId          => feature_1
      , featureStatusCode => pkg_JepRiaShowcase.Sequenced_FeatureStatusCode
      , operatorId       => operatorId_B
    );
    checkCursor( 'setWorkSequenceLessOne: process SEQUENCED rowCount', 1);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );

    -- Возвращаем исходное значение
    -- для успешного прохождения последующих
    -- тестов
    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId => feature_1
      , workSequence => null
      , operatorId => operatorId_C
    );
    --

  end setWorkSequenceLessOne;

  /*
    Тест процедуры setFeatureWorkSequence при недостаточности прав.
  */
  procedure setWorkSequenceMax
  is

    maxWorkSequence integer;

  -- setWorkSequenceMax
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 26: Установка порядка выполнения больше, чем существующий максимальный порядок + 1'
    );

    select
      nvl( max( f.work_sequence), 0)
    into
      maxWorkSequence
    from
      jrs_feature f
    ;

    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_1
      , workSequence => maxWorkSequence + 10
      , operatorId   => operatorId_C
    );

    -- feature_1
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_1
      , workSequenceFrom => maxWorkSequence + 1
      , workSequenceTo   => maxWorkSequence + 1
      , operatorId       => operatorId_B
    );
    checkCursor( 'setWorkSequenceMax: rowCount', 1);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );

    -- Возвращаем исходное значение
    -- для успешного прохождения последующих
    -- тестов
    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId => feature_1
      , workSequence => null
      , operatorId => operatorId_C
    );
    --

  end setWorkSequenceMax;

  /*
    Тест процедуры setFeatureWorkSequence при недостаточности прав.
  */
  procedure setWorkSequenceOrder
  is
  -- setWorkSequenceOrder
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 27: Установка порядка выполнения равного существующему порядку выполнения'
    );

    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_1
      , workSequence => 1
      , operatorId   => operatorId_C
    );

    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_2
      , workSequence => 2
      , operatorId   => operatorId_C
    );

    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_3
      , workSequence => 1
      , operatorId   => operatorId_C
    );

    -- feature_1
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_1
      , workSequenceFrom => 2
      , workSequenceTo   => 2
      , operatorId       => operatorId_B
    );
    checkCursor( 'setWorkSequenceOrder: rowCount', 1);

    -- feature_2
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_2
      , workSequenceFrom => 3
      , workSequenceTo   => 3
      , operatorId       => operatorId_B
    );
    checkCursor( 'setWorkSequenceOrder: rowCount', 1);

    -- feature_3
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_3
      , workSequenceFrom => 1
      , workSequenceTo   => 1
      , operatorId       => operatorId_B
    );
    checkCursor( 'setWorkSequenceOrder: rowCount', 1);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );
  end setWorkSequenceOrder;

  /*
    Тест на наличие разреженности последовательности
    при удалении запроса на функционал.
  */
  procedure checkWorkSequenceOnFeatureDel
  is

    workSequenceCount pls_integer;
    workSequenceMax   pls_integer;

  -- checkWorkSequenceOnFeatureDel
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 33: Проверка на наличие разреженности последовательности при удалении запроса на функционал'
    );

    -- Подготавливаем последовательность
    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_1
      , workSequence => 1
      , operatorId   => operatorId_C
    );
    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_2
      , workSequence => 2
      , operatorId   => operatorId_C
    );
    pkg_JepRiaShowcase.setFeatureWorkSequence(
      featureId      => feature_3
      , workSequence => 3
      , operatorId   => operatorId_C
    );
    --

    -- Удаляем второй элемент последовательности
    delete from
      jrs_feature_process
    where
      feature_id = feature_2
    ;
    pkg_JepRiaShowcase.deleteFeature(
      featureId    => feature_2
      , operatorId => operatorId_B
    );

    -- feature_3
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_3
      , workSequenceFrom => 2
      , workSequenceTo   => 2
      , operatorId       => operatorId_B
    );
    checkCursor( 'checkWorkSequenceOnFeatureDel: rowCount', 1);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );
  end checkWorkSequenceOnFeatureDel;

-- testSetFeatureWorkSequence
begin

  -- Подготовка сценариев
  initTest();

  -- Сценарий 24
  setWorkSequenceWithoutAccess();

  -- Сценарий 25
  setWorkSequenceLessOne();

  -- Сценарий 26
  setWorkSequenceMax();

  -- Сценарий 27
  setWorkSequenceOrder();

  -- Сценарий 33
  checkWorkSequenceOnFeatureDel();

exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка при тестировании установки порядка '
        || 'выполнения запросов на функционал.'
      )
    , true
  );
end testSetFeatureWorkSequence;

/* proc: testSetFeatureResponsible
  Тест установки ответственного за запрос на новый функционал
*/
procedure testSetFeatureResponsible
is

  -- Создаваемые пользователи
  operatorId_A integer;
  operatorId_B integer;

  -- Создаваемые запросы
  feature_1    integer;

  -- Результат выполнения функции
  rc           sys_refcursor;

  /*
    Подготовка сценариев:
    Создаются пользователь с ролью JrsEditFeature: «Пользователь А».
    Создается пользователь с ролью JrsAssignResponsibleFeature: «Пользователь В».
    «Пользователь А» создает запрос на новый функционал «Запрос 1».
  */
  procedure initTest
  is
  begin
    operatorId_A := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_A'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditFeature_RoleSName
        )
    );
    operatorId_B := pkg_AccessOperatorTest.getTestOperatorId(
      baseName        => 'Operator_B'
      , roleSNameList => cmn_string_table_t(
          pkg_JepRiaShowcase.EditAllFeature_RoleSName
          , pkg_JepRiaShowcase.AssignResponsibleFea_RoleSName
        )
    );
    feature_1 := pkg_JepRiaShowcase.createFeature(
      featureName       => 'Запрос 1'
      , featureNameEn   => 'Test_feature_1'
      , operatorId      => operatorId_A
    );
  end initTest;

  /*
    Проверяет число записей в курсоре.
  */
  procedure checkCursor(
    functionName varchar2
    , expectedRowCount integer := null
  )
  is
  begin
    pkg_TestUtility.compareRowCount(
      rc                 => rc
      , expectedRowCount => coalesce( expectedRowCount, 0)
      , failMessageText  =>
          functionName
          || ': Некорректное число записей в курсоре'
    );
  end checkCursor;

  /*
    Тест процедуры setFeatureResponsible с изменением статуса на ASSIGNED
  */
  procedure setFeatureChangeResponsible
  is

  -- setFeatureChangeResponsible
  begin
    pkg_TestUtility.beginTest(
      'Сценарий 34. Изменение ответственного за запрос'
    );

    pkg_JepRiaShowcase.setFeatureResponsible(
      featureId => feature_1
      , responsibleId  => operatorId_A
      , operatorId => operatorId_B
    );

    -- feature_1
    rc := pkg_JepRiaShowcase.findFeature(
      featureId          => feature_1
      , responsibleId    => operatorId_A
      , operatorId       => operatorId_B
    );
    checkCursor( 'setFeatureChangeResponsible: rowCount', 1);

    -- проверка статуса
    rc := pkg_JepRiaShowcase.findFeatureProcess(
      featureId          => feature_1
      , featureStatusCode => pkg_JepRiaShowcase.Assigned_FeatureStatusCode
      , operatorId       => operatorId_B
    );
    checkCursor( 'setFeatureChangeResponsible: process ASSIGNED rowCount', 1);

    endTestException(
      expectedException   => null
      , inExceptionFlag   => 0
    );
  end setFeatureChangeResponsible;

-- testSetFeatureResponsible
begin

  -- Подготовка сценариев
  initTest();

  -- Сценарий 34
  setFeatureChangeResponsible();

exception when others then
  raise_application_error(
    pkg_Error.ErrorStackInfo
    , logger.errorStack(
        'Ошибка при тестировании установки ответственного'
        || ' за запрос на новый функционал .'
      )
    , true
  );
end testSetFeatureResponsible;

end pkg_JepRiaShowcaseTest;
/
