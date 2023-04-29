package Simulator

case class SummaryTable(
    setFirstDate: (Int, Int, Int),
    setLastDate: (Int, Int, Int)
)

case class SwitchTable(
    setStav: Int,
    setDateTimeFirst: (Int, Int, Int, Int, Int, Int),
    setDateLast: (Int, Int, Int),
    setRecordRows: Int
)
case class IndexTable(
    setFirstDate: (Int, Int, Int),
    setLastDate: (Int, Int, Int),
    setIndex: Int
)
