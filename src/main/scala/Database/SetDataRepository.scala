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

object Table {
  val repository = new Database.DataRepository()
  val switch: Seq[ModelTableSwitch] = repository.tableSwith
  val summary: Seq[ModelTableSummary] = repository.tableSummary
}
