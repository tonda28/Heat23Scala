package User

import java.time.LocalDate

class ApplyInterfaceOne(selectedDate: String)
    extends User.Output
    with User.OutputJson {

  val arrDate = selectedDate.split("-")
  val year = arrDate(0).toInt
  val month = arrDate(1).toInt
  val day = arrDate(2).toInt

  def myDt = LocalDate.of(year, month, day).toString

  val date = LocalDate.parse(myDt)
  val dataRepo = new Database.DataRepository()
  val dataSwitch = dataRepo.durationSwitchOn
  val dataSum = dataRepo.dailyTrafficSummary

  val show = Show.apply(
    selectDay = getMenuDaySelector(date, dataSum),
    selectMonth = getMenuMonthSelector(dataSwitch),
    showFrameDailyDetails = getFrameDailyDetailsReview(date, dataSwitch),
    showLabelDuration = getLabelDuration(date, dataSwitch)
  )

  val showJson = CaseShowJson.apply(
    selectMonthJson =
      getMenuMonthSelectorJson(getMenuMonthSelector(dataSwitch)),
    selectDayJson = getMenuDaySelectorJson(getMenuDaySelector(date, dataSum)),
    showFrameDailyDetailsJson =
      getDailyDetailsReviewJson(getFrameDailyDetailsReview(date, dataSwitch)),
    showLabelDurationJson =
      getLabelDurationJson(getLabelDuration(date, dataSwitch))
  )
}
