package Simulator

import Database.SetDataRepository.{ModelTableSummary, ModelTableSwitch}
import Simulator.SetSimulator.CaseSimulatorSumTable

import java.time.temporal.ChronoUnit
import java.time.{Duration, LocalDate, LocalDateTime}
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.DAYS
import scala.math.Ordered.orderingToOrdered
import scala.util.Random

class DataSimulator {

  def simulateSumTable(sim: CaseSimulatorSumTable): Seq[ModelTableSummary] = {

    var i = 1
    var duration = Duration.ofSeconds(10000)
    val dayStart =
      LocalDate.of(
        sim.setFirstDate._1,
        sim.setFirstDate._2,
        sim.setFirstDate._3
      )
    val dayStop =
      LocalDate.of(sim.setLastDate._1, sim.setLastDate._2, sim.setLastDate._3)
    val rowsHowMany = ChronoUnit.DAYS.between(dayStart, dayStop) + 1
    var day = dayStart

    val l = ListBuffer[ModelTableSummary]()
    while (i <= rowsHowMany) {
      duration = Duration.ofSeconds(new Random().between(10000, 13000))
      l += ModelTableSummary.apply(localdate = day, duration = duration)
      day = day.plusDays(1)
      i += 1
    }
    l.toSeq
  }

  def simulateSwitchTable(
      setFirstStav: Int,
      setDateFirst: (Int, Int, Int, Int, Int, Int),
      setDateLast: (Int, Int, Int),
      setRecordsRows: Int
  ) = {
    var stavStart = setFirstStav
    val temper = 4.44
    var dateTimeStart = LocalDateTime.of(
      setDateFirst._1,
      setDateFirst._2,
      setDateFirst._3,
      setDateFirst._4,
      setDateFirst._5,
      setDateFirst._6
    )
    val localdateEnd =
      LocalDate.of(setDateLast._1, setDateLast._2, setDateLast._3)
    val rowsHowMany = setRecordsRows

    val l =
      ListBuffer[ModelTableSwitch]()
    var i = 1
    while (i <= rowsHowMany && dateTimeStart.toLocalDate <= localdateEnd) {
      l += ModelTableSwitch(
        stav = stavStart,
        temper = temper,
        localtime = dateTimeStart.toLocalTime,
        localdate = dateTimeStart.toLocalDate
      )
      i += 1

      stavStart =
        if (stavStart == 1) 0
        else 1

      dateTimeStart =
        if (stavStart != 1) dateTimeStart.plusSeconds(70)
        else dateTimeStart.plusSeconds(780)
    }
    l.toSeq
  }

}
