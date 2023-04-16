package User

import little.json.Implicits.{iterableToJsonArray, stringToJsonString}
import little.json.{Json, JsonObject, JsonOutput, JsonValue}

import scala.annotation.unused
trait OutputJson {
   
  def getMenuMonthSelectorJson(data: Seq[MenuMonthSelector]): JsonValue = {
    @unused
    given JsonOutput[MenuMonthSelector] with
      def apply(u: MenuMonthSelector): JsonObject =
        Json.obj(
          "month" -> u.date.toString
        )
    Json.toJson(data)
  }

  def getMenuDaySelectorJson(data: Seq[MenuDaySelector]): JsonValue = {
    @unused
    given JsonOutput[MenuDaySelector] with
      def apply(date: MenuDaySelector): JsonObject =
        Json.obj(
          "localdate" -> date.localdate.toString,
          "duration" -> date.duration.toString
        )
    Json.toJson(data)
  }

  def getDailyDetailsReviewJson(data: Seq[FrameReview]): JsonValue = {
    @unused
    given JsonOutput[FrameReview] with
      def apply(u: FrameReview): JsonObject =
        Json.obj(
          "on" -> u.on.toString,
          "off" -> u.off.toString,
          "duration" -> u.duration.toString,
          "temper" -> u.temper.toString
        )
    Json.toJson(data)
  }

  def getLabelDurationJson(data: Seq[LabelDuration]): JsonValue = {
    @unused
    given JsonOutput[LabelDuration] with
      def apply(u: LabelDuration): JsonObject =
        Json.obj(
          "day" -> u.dayDuration.toString,
          "month" -> u.monthDuration.toString,
          "all" -> u.allDuration.toString
        )

    Json.toJson(data)
  }

}
