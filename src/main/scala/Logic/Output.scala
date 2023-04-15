package Logic

import Database.SetDataRepository.{ModelTableSummary, ModelTableSwitch}
import Case.SetOutput.{
  CaseDuration,
  CaseMenuMonthSelector,
  CaseModelMenuDaySelector,
  CaseReview
}
import Case.SetSeasonTime.CaseMarkerHeatingSeason

import java.time.LocalDate
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered
import scala.util.{Success, Try}

trait Output
    extends Utility.Validator
    with Utility.ReviewFormater
    with Utility.Calculator {

  def getMenuMonthSelector(
      data: Seq[ModelTableSwitch]
  ): Seq[CaseMenuMonthSelector] = {

    val a = data.groupBy(identity(x => x.localdate.getMonthValue)).keys.toSeq
    var result = ListBuffer[CaseMenuMonthSelector]()
    for (item <- a) {
      result += CaseMenuMonthSelector.apply(
        date = data
          .filter(x => x.localdate.getMonthValue == item)
          .head
          .localdate
      )
    }

    result.toList.sortBy(x => x.date)
  }

  def getMenuDaySelector(
      date: LocalDate,
      data: Seq[ModelTableSummary]
  ): Seq[CaseModelMenuDaySelector] = {
    val dataBasic = data.filter(x =>
      x.localdate.getYear == date.getYear
        &&
        x.localdate.getMonth == date.getMonth
    )
    val result = ListBuffer[CaseModelMenuDaySelector]()
    for (item <- dataBasic)
      result += CaseModelMenuDaySelector(
        localdate = item.localdate,
        duration = item.duration
      )
    result.toSeq
  }

  def getFrameDailyDetailsReview(
      date: LocalDate,
      data: Seq[ModelTableSwitch]
  ): Seq[CaseReview] = {
    Try {
      validateSingleDay(date, data)
    }.map(getReview(_)).get
  }

  def getLabelDuration(
      date: LocalDate,
      data: Seq[ModelTableSwitch]
  ): Seq[CaseDuration] = {
    var l = ListBuffer[CaseDuration]()
    Try { validateSingleDay(date, data) }.map(calculate(_)).get
    l += CaseDuration(
      Try { validateSingleDay(date, data) }.map(calculate(_)).get,
      Try { validateSingleSeason(data) }.map(calculate(_)).get,
      Try { validateSingleMonth(date, data) }.map(calculate(_)).get
    )
    l.toSeq
  }
}
