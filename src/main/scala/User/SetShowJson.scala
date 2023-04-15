package User

import little.json.{JsonOutput, JsonValue}

case class CaseShowJson(
    selectMonthJson: JsonValue,
    selectDayJson: JsonValue,
    showFrameDailyDetailsJson: JsonValue,
    showLabelDurationJson: JsonValue
)
