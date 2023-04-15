package Database

import java.time.{Duration, LocalDate, LocalTime}

object SetDataRepository {
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

//  case class CaseRepository(
//      dataDailySum: Seq[ModelTableSummary],
//      dataSwitchOn: Seq[ModelTableSwitch]
//  )
}
