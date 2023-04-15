package Case

import little.json.{JsonOutput, JsonValue}

object SetShowJson {
  case class CaseShowJson(
      selectMonthJson: JsonValue,
      selectDayJson: JsonValue,
      showFrameDailyDetailsJson: JsonValue,
      showLabelDurationJson: JsonValue
  )

}
