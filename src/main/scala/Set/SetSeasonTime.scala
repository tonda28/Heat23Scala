package Set

import java.time.LocalDate

object SetSeasonTime {
  case class CaseMarkerHeatingSeason(
      begin: LocalDate = LocalDate.of(2021, 10, 1),
      end: LocalDate = LocalDate.of(2022, 4, 30),
      dateNow: LocalDate = LocalDate.now()
  )

  given CaseMarkerHeatingSeason = CaseMarkerHeatingSeason.apply()

}
