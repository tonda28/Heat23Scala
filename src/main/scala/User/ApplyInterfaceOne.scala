package User

import java.time.LocalDate

class ApplyInterfaceOne(selectedDate: String)
    extends User.Output
    with User.OutputJson
    with Utility.Parser {

  val date: LocalDate = parse(selectedDate)

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
