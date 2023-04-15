package Workbench

import Database.{DataRepository, SetDataRepository}
import Simulator.DataSimulator
import Simulator.SetSimulator.CaseSimulatorSumTable

import java.time.LocalDate

object WorkTable extends App {

  val l = CaseSimulatorSumTable.apply(
    setFirstDate = (2022, 10, 1),
    setLastDate = (2022, 11, 30)
  )
  val ll = CaseSimulatorSumTable.apply(
    setFirstDate = (2023, 1, 1),
    setLastDate = (2023, 2, 25)
  )
  val obj = new DataSimulator()
  val a = obj.simulateSumTable(ll)
  println(a)

//  val b = GivensDataRepository.given_CaseRepository.dataDailySum
//  println(b)
}
