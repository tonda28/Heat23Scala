package Case

case class Show(
    selectDay: Seq[Case.MenuDaySelector],
    selectMonth: Seq[Case.MenuMonthSelector],
    showFrameDailyDetails: Seq[Case.FrameReview],
    showLabelDuration: Seq[Case.LabelDuration]
)
