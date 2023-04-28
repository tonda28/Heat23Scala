package Workbench

import Simulator.DataSimulator

object One extends App {

  val a = Simulator.IndexTable.apply(
    (2022, 10, 1),
    (2022, 10, 6),
    2
  )
  val obj = new DataSimulator()
  val t = obj.simulateIndex(a)
  println(t._2)
}
