package Logic

import Database.SetDataRepository.CaseRepository
import Set.SetOutput.{CaseDuration, CaseMenuMonthSelector, CaseModelMenuDaySelector, CaseReview}
import Set.SetSeasonTime.CaseMarkerHeatingSeason

import java.time.LocalDate
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered
import scala.util.{Success, Try}

trait Output extends Utility.Validator
  with Utility.ReviewFormater
  with Utility.Calculator {

    def getMenuMonthSelector
    (using data: CaseRepository): Seq[CaseMenuMonthSelector]
    = {

      val a = data.dataSwitchOn.groupBy(identity(x => x.localdate.getMonthValue)).keys.toSeq
      var result = ListBuffer[CaseMenuMonthSelector]()
      for (item <- a)
      {  result += CaseMenuMonthSelector.apply(
        date = data.dataSwitchOn
          .filter(x => x.localdate.getMonthValue == item)
          .head.localdate)}

      result.toList.sortBy(x => x.date)
    }

    def getMenuDaySelector[A <: LocalDate, B <: CaseRepository, D >: CaseModelMenuDaySelector](date: A)(using data: B): Seq[D] = {
    val dataBasic = data.dataDailySum.filter(x =>
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

    def getFrameDailyDetailsReview(date: LocalDate): Seq[CaseReview] = {
    Try {
      validateSingleDay(date)
    }.map(getReview(_)).get
  }

    def getLabelDuration(date: LocalDate): Seq[CaseDuration]={
    var l = ListBuffer[CaseDuration]()
    Try{validateSingleDay(date)}.map(calculate(_)).get
        l += CaseDuration(
          Try{validateSingleDay(date)}.map(calculate(_)).get,
          Try{validateSingleSeason()}.map(calculate(_)).get,
          Try{validateSingleMonth(date)}.map(calculate(_)).get)
        l.toSeq
  }
}
