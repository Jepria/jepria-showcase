-- script: db/install/data/last/op_role.sql
declare

  -- Число изменённых ролей
  nChanged integer := 0;

  /*
    Добавление или обновление роли.
  */
  function mergeRoleLegacy(
    roleShortName varchar2
  , roleName      varchar2
  , roleNameEn    varchar2
  , description   varchar2
  )
  return integer
  is
    roleId integer;
    mergeRoleResult integer;
  begin
    execute immediate
    '
    select
      min( t.role_id)
    from
      op_role t
    where
      t.short_name = :shortName
    '
    into roleId
    using
      roleShortName
    ;
    if roleId is null then
      execute immediate
      '
    begin
      :roleId := pkg_Operator.createRole(
        roleName      => :roleName
        , roleNameEn  => :roleNameEn
        , shortName   => :shortName
        , description => :description
        , operatorId  => pkg_Operator.getCurrentUserId()
      );
    end;
      '
      using
        out roleId
        , in roleName
        , in roleNameEn
        , in roleShortName
        , in description
      ;
      dbms_output.put_line(
        'role created: ' || roleShortName || ' ( role_id=' || roleId || ')'
      );
      mergeRoleResult := 1;
    else
      mergeRoleResult := 0;
    end if;
    return mergeRoleResult;
  end mergeRoleLegacy;

  /*
    Добавление или обновление роли.
  */
  function mergeRole(
    roleShortName varchar2
  , roleName      varchar2
  , roleNameEn    varchar2
  , description   varchar2
  )
  return integer
  is
    mergeResult integer;
  begin
    execute immediate
      '
      begin
        :mergeResult :=
pkg_AccessOperator.mergeRole(
  roleShortName => :roleShortName
  , roleName    => :roleName
  , roleNameEn  => :roleNameEn
  , description => :description
);
      end;'
    using
      out mergeResult
      , in roleShortName
      , in roleName
      , in roleNameEn
      , in description
    ;
    return mergeResult;
  exception when others then
    return
      mergeRoleLegacy(
      roleShortName => roleShortName
    , roleName      => roleName
    , roleNameEn    => roleNameEn
    , description   => description
      );
  end mergeRole;

-- main
begin
  nChanged :=
  mergeRole(
    roleShortName => 'JrsEditFeature'
    , roleName    =>
        'ITM: Управление своими запросами на новый функционал'
    , roleNameEn  =>
        'ITM: Own feature request management'
    , description =>
        'Пользователь с данной ролью имеет доступ к функционалу управления своими запросами на новый функционал'
  )
  +
  mergeRole(
    roleShortName => 'JrsEditAllFeature'
    , roleName    =>
        'ITM: Управление всеми запросами на новый функционал'
    , roleNameEn  =>
        'ITM: All feature request management'
    , description =>
        'Пользователь с данной ролью, имеет доступ к функционалу управления всеми запросами на новый функционал'
  )
  +
  mergeRole(
    roleShortName => 'JrsAssignResponsibleFeature'
    , roleName    =>
        'ITM: Назначение ответственного за запрос на новый функционал'
    , roleNameEn  =>
        'ITM: Assign responsible for feature request'
    , description =>
        'Пользователь с данной ролью может назначать ответственного за запрос на новый функционал'
  )
  +
  mergeRole(
    roleShortName => 'JrsAssignWorkSequenceFeature'
    , roleName    =>
        'ITM: Назначение порядка выполнения запрос на новый функционал'
    , roleNameEn  =>
        'ITM: Assign work sequence for feature request'
    , description =>
        'Пользователь с данной ролью может назначать порядок выполнения запроса на новый функционал'
  )
  +
  mergeRole(
    roleShortName => 'JrsOperatorFeature'
    , roleName    =>
        'ITM: Указание пользователей запросов на новый функционал'
    , roleNameEn  =>
        'ITM: Setup operators for feature request'
    , description =>
        'Пользователь с данной ролью может указывать пользователей  запросов на новый функционал (из множества всех пользователей системы)'
  )
  +
  mergeRole(
    roleShortName => 'JrsEditGoods'
    , roleName    =>
        'JepRiaShowcase: Редактирование данных по товарам'
    , roleNameEn  =>
        'JepRiaShowcase: Access to edit goods data'
    , description =>
        'Пользователь с данной ролью может работать с данными о товарах'
  )
  +
  mergeRole(
    roleShortName => 'JrsEditRequest'
    , roleName    =>
        'JepRiaShowcase: Редактирование данных по запросам на закупку'
    , roleNameEn  =>
        'JepRiaShowcase: Access to edit goods data'
    , description =>
        'Пользователь с данной ролью может работать с данными по запросам на закупку'
  )
  +
  mergeRole(
    roleShortName => 'JrsEditRequestProcess'
    , roleName    =>
        'JepRiaShowcase: Редактирование данных по обработке запросов на закупку'
    , roleNameEn  =>
        'JepRiaShowcase: Access to edit goods data'
    , description =>
        'Пользователь с данной ролью может работать с данными по обработке запросов на закупку'
  )
  +
  mergeRole(
    roleShortName => 'JrsEditShopGoods'
    , roleName    =>
        'JepRiaShowcase: Редактирование данных по товарам в магазинах'
    , roleNameEn  =>
        'JepRiaShowcase: Access to edit data about goods in shop'
    , description =>
        'Пользователь с данной ролью может работать с данными по товарам в магазинах'
  )
  +
  mergeRole(
    roleShortName => 'JrsEditSupplier'
    , roleName    =>
        'JepRiaShowcase: Редактирование данных по поставщикам'
    , roleNameEn  =>
        'JepRiaShowcase: Access to edit suppliers data'
    , description =>
        'Пользователь с данной ролью может работать с данными о поставщиках'
  );
  dbms_output.put_line(
    'roles changed: ' || nChanged || ')'
  );
  commit;
end;
/
