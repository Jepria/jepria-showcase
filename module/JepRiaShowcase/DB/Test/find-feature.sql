select
  pkg_JepRiaShowcase.findFeature(
    operatorId => pkg_Operator.getCurrentUserId()
    , maxRowCount => 10
  )
from
  dual
/
