package Workbench

import Database.ModelTableSummary

import java.time.{Duration, LocalDate}
import scala.collection.mutable.ListBuffer
import scala.util.Try

object One
    extends App
    with Utility.Pointer
    with Utility.Validator
    with Utility.Calculator {

  val a = Simulator.IndexTable.apply(
    (2022, 10, 1),
    (2022, 10, 6),
    3
  )
  val obj = new Simulator.DataSimulator()
  val t = obj.simulateIndex(a)
  val tableSwitch = t._1
  val tableSummary = t._2

  //println(point(tableSummary, tableSwitch))

  //val dt = LocalDate.of(2022, 10, 2)
  def sum(date: LocalDate, data: Seq[Database.ModelTableSwitch]) = {
    val a = validateSingleDay(date, data)
    val b = calculate(a)
    b

  }

  def Iter() = {
    val a = point(tableSummary, tableSwitch)
    var l = ListBuffer[ModelTableSummary]()
    for (item <- a) {
      l += ModelTableSummary(
        localdate = item._2,
        duration = sum(item._2, tableSwitch)
      )

    }
    l.toSeq
  }

   println(Iter())

}
