package User

import little.json.Implicits.{iterableToJsonArray, stringToJsonString}
import little.json.{Json, JsonOutput, JsonValue}
trait OutputJson {

  def getMenuMonthSelectorJson[A <: MenuMonthSelector, B >: JsonValue](data: Seq[A]): B = {
    given ModelSummaryToJson: JsonOutput[A] with
      def apply(u: A) =
        Json.obj(
          "month" -> u.date.toString
        )
    Json.toJson(data)
  }

  def getMenuDaySelectorJson[A <: MenuDaySelector, B >: JsonValue](data: Seq[A]): B = {
    given ModelSummaryToJson: JsonOutput[A] with
      def apply(date: A) =
        Json.obj(
          "localdate" -> date.localdate.toString,
          "duration" -> date.duration.toString
        )
    Json.toJson(data)
  }

  def getDailyDetailsReviewJson(data: Seq[FrameReview]) = {
    given ModelReviewToJson: JsonOutput[FrameReview] with
      def apply(u: FrameReview) =
        Json.obj(
          "on" -> u.on.toString,
          "off" -> u.off.toString,
          "duration" -> u.duration.toString,
          "temper" -> u.temper.toString
        )
    Json.toJson(data)
  }

  def getLabelDurationJson(data: Seq[LabelDuration]) = {

    given Sum: JsonOutput[LabelDuration] with
      def apply(u: LabelDuration) =
        Json.obj(
          "day" -> u.dayDuration.toString,
          "month" -> u.monthDuration.toString,
          "all" -> u.allDuration.toString
        )

    Json.toJson(data)
  }

}