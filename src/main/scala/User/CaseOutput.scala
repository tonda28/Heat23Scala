package User

import User.Output
import little.json.JsonValue

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
