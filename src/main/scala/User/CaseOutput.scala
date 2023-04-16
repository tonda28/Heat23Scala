package User

import java.time.{Duration, LocalDate, LocalTime, Month}

case class MenuMonthSelector(
    date: LocalDate
)

case class MenuDaySelector(
    localdate: LocalDate,
    duration: Duration
)

case class FrameReview(
    on: LocalTime,
    off: LocalTime,
    duration: Duration,
    temper: Double
)

case class LabelDuration(
    dayDuration: Duration,
    monthDuration: Duration,
    allDuration: Duration
)

case class Show(
    selectDay: Seq[MenuDaySelector],
    selectMonth: Seq[MenuMonthSelector],
    showFrameDailyDetails: Seq[FrameReview],
    showLabelDuration: Seq[LabelDuration]
)

case class CaseShowJson(
    selectMonthJson: little.json.JsonValue,
    selectDayJson: little.json.JsonValue,
    showFrameDailyDetailsJson: little.json.JsonValue,
    showLabelDurationJson: little.json.JsonValue
)
