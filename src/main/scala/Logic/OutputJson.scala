package Logic

import little.json.Implicits.{iterableToJsonArray, stringToJsonString}
import little.json.{Json, JsonOutput, JsonValue}
trait OutputJson {

  def getMenuMonthSelectorJson[A <: Case.MenuMonthSelector, B >: JsonValue](data: Seq[A]): B = {
    given ModelSummaryToJson: JsonOutput[A] with
      def apply(u: A) =
        Json.obj(
          "month" -> u.date.toString
        )
    Json.toJson(data)
  }

  def getMenuDaySelectorJson[A <: Case.MenuDaySelector, B >: JsonValue](data: Seq[A]): B = {
    given ModelSummaryToJson: JsonOutput[A] with
      def apply(date: A) =
        Json.obj(
          "localdate" -> date.localdate.toString,
          "duration" -> date.duration.toString
        )
    Json.toJson(data)
  }

  def getDailyDetailsReviewJson(data: Seq[Case.FrameReview]) = {
    given ModelReviewToJson: JsonOutput[Case.FrameReview] with
      def apply(u: Case.FrameReview) =
        Json.obj(
          "on" -> u.on.toString,
          "off" -> u.off.toString,
          "duration" -> u.duration.toString,
          "temper" -> u.temper.toString
        )
    Json.toJson(data)
  }

  def getLabelDurationJson(data: Seq[Case.LabelDuration]) = {

    given Sum: JsonOutput[Case.LabelDuration] with
      def apply(u: Case.LabelDuration) =
        Json.obj(
          "day" -> u.dayDuration.toString,
          "month" -> u.monthDuration.toString,
          "all" -> u.allDuration.toString
        )

    Json.toJson(data)
  }

}
