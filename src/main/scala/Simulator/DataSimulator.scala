package Simulator

import Database.SetDataRepository.{ModelTableSummary, ModelTableSwitch}

import java.time.temporal.ChronoUnit
import java.time.{Duration, LocalDate, LocalDateTime}
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.DAYS
import scala.math.Ordered.orderingToOrdered
import scala.util.Random

class DataSimulator {

  def simulateSumTable(
      setFirstDate: Tuple3[Int, Int, Int],
      setLastDate: Tuple3[Int, Int, Int]
  ): Seq[ModelTableSummary] = {

    var i = 1
    var duration = Duration.ofSeconds(10000)
    var dayStart =
      LocalDate.of(setFirstDate._1, setFirstDate._2, setFirstDate._3)
    var dayStop = LocalDate.of(setLastDate._1, setLastDate._2, setLastDate._3)
    var rowsHowMany = ChronoUnit.DAYS.between(dayStart, dayStop) + 1
    var day = dayStart

    var l = ListBuffer[ModelTableSummary]()
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
      setDateFirst: Tuple6[Int, Int, Int, Int, Int, Int],
      setDateLast: Tuple3[Int, Int, Int],
      setRecordsRows: Int
  ) = {
    var stavStart = setFirstStav
    var temper = 4.44
    var dateTimeStart = LocalDateTime.of(
      setDateFirst._1,
      setDateFirst._2,
      setDateFirst._3,
      setDateFirst._4,
      setDateFirst._5,
      setDateFirst._6
    )
    var localdateEnd =
      LocalDate.of(setDateLast._1, setDateLast._2, setDateLast._3)
    var rowsHowMany = setRecordsRows

    var l =
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
