select
  pkg_JepRiaShowcase.findFeatureOperator(
    operatorId => pkg_Operator.getCurrentUserId()
  )
from
  dual
/
