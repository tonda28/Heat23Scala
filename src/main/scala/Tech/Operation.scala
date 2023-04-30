package Tech

import Database.{ModelTableSummary, ModelTableSwitch}

import java.time.LocalDate
import scala.collection.mutable.ListBuffer
import scala.util.Try

class Operation(
    tableSwitch: Seq[ModelTableSwitch],
    tableSummary: Seq[ModelTableSummary]
) extends Utility.Validator
    with Utility.Calculator
    with Utility.Pointer {
  private def summaryDay(
      date: LocalDate,
      data: Seq[Database.ModelTableSwitch]
  ) = {
    Try {
      validateSingleDay(date, data)
    }.map(calculate)
  }.get

  def setSummaryTableAccordingToIndex() = {
    val listSummary = point(tableSummary, tableSwitch)
    var l = ListBuffer[ModelTableSummary]()
    for (item <- listSummary) {
      l += ModelTableSummary(
        localdate = item._2,
        duration = summaryDay(item._2, tableSwitch)
      )

    }
    l.toSeq
  }

}
