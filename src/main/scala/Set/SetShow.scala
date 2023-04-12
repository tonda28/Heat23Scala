package Set

import Set.SetOutput.{CaseDuration, CaseMenuMonthSelector, CaseModelMenuDaySelector, CaseReview}

object SetShow {
  case class CaseShow(
      selectDay: Seq[CaseModelMenuDaySelector],
      selectMonth: Seq[CaseMenuMonthSelector],
      showFrameDailyDetails: Seq[CaseReview],
      showLabelDuration: Seq[CaseDuration]
  )
}
