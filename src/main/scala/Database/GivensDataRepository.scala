package Database

import Database.SetDataRepository.CaseRepository

object GivensDataRepository {
  val dataRepo = new DataRepository()

  given CaseRepository = CaseRepository.apply(
    dataDailySum = dataRepo.dailyTrafficSummary,
    dataSwitchOn = dataRepo.durationSwitchOn
  )
}
