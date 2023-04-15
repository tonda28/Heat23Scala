package User

import User.{FrameReview, LabelDuration, MenuDaySelector, MenuMonthSelector}

case class Show(
                 selectDay: Seq[MenuDaySelector],
                 selectMonth: Seq[MenuMonthSelector],
                 showFrameDailyDetails: Seq[FrameReview],
                 showLabelDuration: Seq[LabelDuration]
)
