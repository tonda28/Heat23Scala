package Utility

import java.time.Duration

trait Pointer {
  def point(
      dataSummaryTable: List[Database.ModelTableSummary],
      dataSwitchTable: List[Database.ModelTableSwitch]
  ) = {
    val columnWithDate =
      dataSwitchTable.groupBy(x => x.localdate).keys.toList.sorted
    val columnWithIndex = columnWithDate.map(y =>
      dataSummaryTable.indexWhere(x => x.localdate == y)
    )

    (columnWithIndex zip columnWithDate)
      .filter(x => x._1 == -1)
  }
}
