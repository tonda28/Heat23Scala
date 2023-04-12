package Logic

import Set.SetSeasonTime.given_CaseMarkerHeatingSeason
import Database.GivensDataRepository.given_CaseRepository
import Set.SetShow.CaseShow
import Set.SetShowJson.CaseShowJson

import java.time.LocalDate

class ApplyInterfaceOne(selectedDate: String)
    extends Logic.Output
    with Logic.OutputJson {

  val arrDate = selectedDate.split("-")
  val year = arrDate(0).toInt
  val month = arrDate(1).toInt
  val day = arrDate(2).toInt

  def myDt = LocalDate.of(year, month, day).toString

  val date = LocalDate.parse(myDt)

  val show = CaseShow.apply(
    selectDay = getMenuDaySelector(date),
    selectMonth = getMenuMonthSelector,
    showFrameDailyDetails = getFrameDailyDetailsReview(date),
    showLabelDuration = getLabelDuration(date)
  )

  val showJson = CaseShowJson.apply(
    selectMonthJson = getMenuMonthSelectorJson(getMenuMonthSelector),
    selectDayJson = getMenuDaySelectorJson(getMenuDaySelector(date)),
    showFrameDailyDetailsJson =
      getDailyDetailsReviewJson(getFrameDailyDetailsReview(date)),
    showLabelDurationJson = getLabelDurationJson(getLabelDuration(date))
  )
}
