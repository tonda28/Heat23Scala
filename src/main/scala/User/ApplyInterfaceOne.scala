package User

import Database.{DataRepository, ModelTableSummary, ModelTableSwitch}

import java.time.LocalDate

class ApplyInterfaceOne(selectedDate: String)
    extends User.Output
    with User.OutputJson
    with Utility.Parser
    {

  private val date: LocalDate = parse(selectedDate)
  private val dataSwitch: Seq[ModelTableSwitch] = Database.Table.switch
  private val dataSum: Seq[ModelTableSummary] = Database.Table.summary

  val show: Show = Show.apply(
    selectDay = getMenuDaySelector(date, dataSum),
    selectMonth = getMenuMonthSelector(dataSwitch),
    showFrameDailyDetails = getFrameDailyDetailsReview(date, dataSwitch),
    showLabelDuration = getLabelDuration(date, dataSwitch)
  )

  val showJson: CaseShowJson = CaseShowJson.apply(
    selectMonthJson =
      getMenuMonthSelectorJson(getMenuMonthSelector(dataSwitch)),
    selectDayJson = getMenuDaySelectorJson(getMenuDaySelector(date, dataSum)),
    showFrameDailyDetailsJson =
      getDailyDetailsReviewJson(getFrameDailyDetailsReview(date, dataSwitch)),
    showLabelDurationJson =
      getLabelDurationJson(getLabelDuration(date, dataSwitch))
  )

    
    }
