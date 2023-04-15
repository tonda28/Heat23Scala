package Logic

import java.time.LocalDate
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered
import scala.util.{Success, Try}

trait Output
    extends Utility.Validator
    with Utility.ReviewFormater
    with Utility.Calculator {

  def getMenuMonthSelector(
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Case.MenuMonthSelector] = {

    val a = data.groupBy(identity(x => x.localdate.getMonthValue)).keys.toSeq
    val result = ListBuffer[Case.MenuMonthSelector]()
    for (item <- a) {
      result += Case.MenuMonthSelector.apply(
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
      data: Seq[Database.ModelTableSummary]
  ): Seq[Case.MenuDaySelector] = {
    val dataBasic = data.filter(x =>
      x.localdate.getYear == date.getYear
        &&
        x.localdate.getMonth == date.getMonth
    )
    val result = ListBuffer[Case.MenuDaySelector]()
    for (item <- dataBasic)
      result += Case.MenuDaySelector(
        localdate = item.localdate,
        duration = item.duration
      )
    result.toSeq
  }

  def getFrameDailyDetailsReview(
      date: LocalDate,
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Case.FrameReview] = {
    Try {
      validateSingleDay(date, data)
    }.map(getReview(_)).get
  }

  def getLabelDuration(
      date: LocalDate,
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Case.LabelDuration] = {
    val l = ListBuffer[Case.LabelDuration]()
    Try { validateSingleDay(date, data) }.map(calculate(_)).get
    l += Case.LabelDuration(
      Try { validateSingleDay(date, data) }.map(calculate(_)).get,
      Try { validateSingleSeason(data) }.map(calculate(_)).get,
      Try { validateSingleMonth(date, data) }.map(calculate(_)).get
    )
    l.toSeq
  }
}
