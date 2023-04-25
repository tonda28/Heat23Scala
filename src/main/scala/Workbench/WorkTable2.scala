package Workbench

import Database.ModelTableSwitch
import Simulator.SwitchTable

import java.time.{Duration, LocalDate}
import scala.annotation.unused
import scala.util.Try

object WorkTable2 extends App with Utility.Validator with Utility.Calculator {
  val simulator = new Simulator.DataSimulator()

  Simulator.SwitchTable.apply(
    1,
    (2022, 11, 1, 23, 59, 50),
    (2022, 11, 1),
    50000
  )

  LocalDate.of(2022, 11, 1)

}
