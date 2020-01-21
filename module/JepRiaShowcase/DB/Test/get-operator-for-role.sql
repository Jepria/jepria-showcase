var s refcursor

begin
  :s := pkg_JepRiaShowcase.getOperatorForRole(
    roleShortNameList => 'JrsEditFeature,JrsEditAllFeature'
  );
end;
/

select
  :s
from
  dual
/
