package Workbench

import java.time.{Duration, LocalDate}
import scala.util.Try

object One extends App with Utility.Pointer {

  val a = Simulator.IndexTable.apply(
    (2022, 10, 1),
    (2022, 10, 6),
    3
  )
  val obj = new Simulator.DataSimulator()
  val t = obj.simulateIndex(a)
  val tableSwitch = t._1.toList
  val tableSummary = t._2.toList

  println(point(tableSummary, tableSwitch))

}
