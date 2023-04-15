package Simulator

object SetSimulator {

  case class CaseSimulatorSumTable(
      setFirstDate: (Int, Int, Int),
      setLastDate: (Int, Int, Int)
  )

  case class CaseSimulatorSwitchTable(
      setStav: Int,
      setDateTimeFirst: (Int, Int, Int, Int, Int, Int),
      setDateLast: (Int, Int, Int), 
      setRecordRows: Int
  )
}
