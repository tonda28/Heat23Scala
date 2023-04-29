package Simulator

import Database.ModelTableSwitch

import java.time.temporal.ChronoUnit
import java.time.{Duration, LocalDate, LocalDateTime}
import scala.annotation.unused
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.DAYS
import scala.math.Ordered.orderingToOrdered
import scala.util.Random

class DataSimulator {

  def simulateSumTable(
      sim: Simulator.SummaryTable
  ): Seq[Database.ModelTableSummary] = {

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

    val l = ListBuffer[Database.ModelTableSummary]()
    while (i <= rowsHowMany) {
      duration = Duration.ofSeconds(new Random().between(10000, 13000))
      l += Database.ModelTableSummary.apply(
        localdate = day,
        duration = duration
      )
      day = day.plusDays(1)
      i += 1
    }
    l.toSeq
  }

  @unused
  def simulateSwitchTable(
      sim: Simulator.SwitchTable
  ): Seq[ModelTableSwitch] = {
    var stavStart = sim.setStav
    val temper = 4.44
    var dateTimeStart = LocalDateTime.of(
      sim.setDateTimeFirst._1,
      sim.setDateTimeFirst._2,
      sim.setDateTimeFirst._3,
      sim.setDateTimeFirst._4,
      sim.setDateTimeFirst._5,
      sim.setDateTimeFirst._6
    )
    val localdateEnd =
      LocalDate.of(sim.setDateLast._1, sim.setDateLast._2, sim.setDateLast._3)
    val rowsHowMany = sim.setRecordRows

    val l =
      ListBuffer[Database.ModelTableSwitch]()
    var i = 1
    while (i <= rowsHowMany && dateTimeStart.toLocalDate <= localdateEnd) {
      l += Database.ModelTableSwitch(
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

  def simulateIndex(sim: Simulator.IndexTable) = {

    val dayStart = LocalDate.of(
      sim.setFirstDate._1,
      sim.setFirstDate._2,
      sim.setFirstDate._3
    )
    val dayStop = LocalDate.of(
      sim.setLastDate._1,
      sim.setLastDate._2,
      sim.setLastDate._3
    )

    var dateTimeStart = LocalDateTime.of(
      sim.setFirstDate._1,
      sim.setFirstDate._2,
      sim.setFirstDate._3,
      1,
      1,
      1
    )

    val minus = sim.setIndex
    val HowManyDays = ChronoUnit.DAYS.between(dayStart, dayStop) + 1
    val temper = 4.44
    var duration = Duration.ofSeconds(10000)
    var day = dayStart
    var stavStart = 1

    val tableSummary = ListBuffer[Database.ModelTableSummary]()
    var i = 1
    while (i <= HowManyDays - minus) {
      duration = Duration.ofSeconds(new Random().between(10000, 13000))
      tableSummary += Database.ModelTableSummary.apply(
        localdate = day,
        duration = duration
      )
      day = day.plusDays(1)
      i += 1
    }
    tableSummary.toSeq

    val tableSwitch =
      ListBuffer[Database.ModelTableSwitch]()
    //var ii = 1
    while (dateTimeStart.toLocalDate <= dayStop) {
      tableSwitch += Database.ModelTableSwitch(
        stav = stavStart,
        temper = temper,
        localtime = dateTimeStart.toLocalTime,
        localdate = dateTimeStart.toLocalDate
      )
      // ii += 1

      stavStart =
        if (stavStart == 1) 0
        else 1

      dateTimeStart =
        if (stavStart != 1) dateTimeStart.plusSeconds(70)
        else dateTimeStart.plusSeconds(780)
    }
    tableSwitch.toSeq

    (tableSwitch.toSeq, tableSummary.toSeq)

  }
}
