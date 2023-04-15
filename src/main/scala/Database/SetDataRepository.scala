package Database

import java.time.{Duration, LocalDate, LocalTime}

case class ModelTableSwitch(
    stav: Int,
    temper: Double,
    localtime: LocalTime,
    localdate: LocalDate
)

case class ModelTableSummary(
    localdate: LocalDate,
    duration: Duration
)
