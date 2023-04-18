package Workbench

import Simulator.DataSimulator

import java.time.LocalDate

object WorkTable extends App with Utility.Validator {
  val a = Simulator.SwitchTable.apply(
    1,
    (2022, 10, 31, 23, 59, 55),
    (2022, 11, 1),
    10
  )
  val obj = new DataSimulator()
  val b = obj.simulateSwitchTable(a)
  val data = validateSingleDay(LocalDate.of(2022, 11, 1), b)
  println(b)
  println(data)
}
