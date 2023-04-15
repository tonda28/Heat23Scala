package Case

import Logic.Output
import little.json.JsonValue

import java.time.{Duration, LocalDate, LocalTime, Month}

object SetOutput {
  case class CaseMenuMonthSelector(
      date: LocalDate
  )

  case class CaseModelMenuDaySelector(
      localdate: LocalDate,
      duration: Duration
  )

  case class CaseReview(
      on: LocalTime,
      off: LocalTime,
      duration: Duration,
      temper: Double
  )

  case class CaseDuration(
      dayDuration: Duration,
      monthDuration: Duration,
      allDuration: Duration
  )
}
