package Logic

import Case.SetOutput.{CaseDuration, CaseMenuMonthSelector, CaseModelMenuDaySelector, CaseReview}
import little.json.Implicits.{iterableToJsonArray, stringToJsonString}
import little.json.{Json, JsonOutput, JsonValue}
trait OutputJson {

  def getMenuMonthSelectorJson[A <: CaseMenuMonthSelector, B >: JsonValue](data: Seq[A]): B = {
    given ModelSummaryToJson: JsonOutput[A] with
      def apply(u: A) =
        Json.obj(
          "month" -> u.date.toString
        )
    Json.toJson(data)
  }

  def getMenuDaySelectorJson[A <: CaseModelMenuDaySelector, B >: JsonValue](data: Seq[A]): B = {
    given ModelSummaryToJson: JsonOutput[A] with
      def apply(date: A) =
        Json.obj(
          "localdate" -> date.localdate.toString,
          "duration" -> date.duration.toString
        )
    Json.toJson(data)
  }

  def getDailyDetailsReviewJson(data: Seq[CaseReview]) = {
    given ModelReviewToJson: JsonOutput[CaseReview] with
      def apply(u: CaseReview) =
        Json.obj(
          "on" -> u.on.toString,
          "off" -> u.off.toString,
          "duration" -> u.duration.toString,
          "temper" -> u.temper.toString
        )
    Json.toJson(data)
  }

  def getLabelDurationJson(data: Seq[CaseDuration]) = {

    given Sum: JsonOutput[CaseDuration] with
      def apply(u: CaseDuration) =
        Json.obj(
          "day" -> u.dayDuration.toString,
          "month" -> u.monthDuration.toString,
          "all" -> u.allDuration.toString
        )

    Json.toJson(data)
  }

}
