package Workbench

import Simulator.DataSimulator

import java.time.LocalDate
import scala.util.Try

object WorkTable
    extends App
    with Utility.Validator
    with Utility.ReviewFormater
    with User.Output {
  val day = LocalDate.of(2023, 1, 28)
  val simulator = new DataSimulator()
  val l = Try {
    Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 1, 1, 1),
      (2023, 4, 30),
      50000
    )
  }.map(simulator.simulateSwitchTable)
    .map(x => getFrameDailyDetailsReview(day, x))
    .get
    .map(_.productElementNames)
    .head
    .toSeq

  //val switchTable = simulator.simulateSwitchTable(setTable)
  //val l = getFrameDailyDetailsReview(day, switchTable)
  //private val it = l.map(_.productElementNames).head.toSeq

  println(l)

}
