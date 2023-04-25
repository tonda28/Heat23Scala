package Simulator

import org.scalatest.funsuite
import org.scalatest.funsuite.AnyFunSuite

import java.time.{DateTimeException, LocalDate, LocalDateTime, LocalTime}
import scala.util.Try
class DataSimulatorTest extends AnyFunSuite {

  val simulator = new Simulator.DataSimulator()

  test("Number of rows according to range should by 2") {
    Try {
      Simulator.SummaryTable.apply((2022, 10, 1), (2022, 10, 2))
    }.map(simulator.simulateSumTable)
      .map(simulatedSeq => assert(simulatedSeq.size == 2))
  }

  test("If last date is smaller then first, rows should be 0") {
    Try {
      Simulator.SummaryTable.apply((2022, 10, 1), (2022, 9, 2))
    }.map(x => simulator.simulateSumTable(x))
      .map(simulatedSeq => assert(simulatedSeq.size == 0))
  }

  test("Bad number of year-month-day should have throw exception") {
    val summaryTable = Simulator.SummaryTable.apply((2022, 105, 2), (2022, 101, 2))
    assertThrows[DateTimeException] {
      simulator.simulateSumTable(summaryTable)
    }
  }

  // Switch table tests

  test("simulateSwitchTable - first row stav is set on 1") {
    Try {
      Simulator.SwitchTable.apply(
        setStav = 1,
        setDateTimeFirst = (2022, 10, 1, 1, 1, 1),
        setDateLast = (2022, 11, 1),
        setRecordRows = 50000
      )
    }
      .map(simulator.simulateSwitchTable)
      .map(x => assert(x.head.stav == 1))
  }

  test("simulateSwitchTable - check LocalDate") {
    Try {
      Simulator.SwitchTable.apply(
        setStav = 1,
        setDateTimeFirst = (2022, 10, 1, 1, 1, 1),
        setDateLast = (2022, 11, 1),
        setRecordRows = 50000
      )
    }
      .map(simulator.simulateSwitchTable)
      .map(x => assert(x.head.localdate == LocalDate.of(2022, 11, 1)))
  }

  test("simulateSwitchTable - check LocalDateTime") {
    Try {
      Simulator.SwitchTable.apply(
        setStav = 1,
        setDateTimeFirst = (2022, 10, 1, 1, 1, 1),
        setDateLast = (2022, 11, 1),
        setRecordRows = 50000
      )
    }
      .map(simulator.simulateSwitchTable)
      .map(x =>
        assert(x.head.localtime == LocalDateTime.of(2022, 10, 1, 1, 1, 1))
      )
  }
  test("Bad number of month-day should have throw exception") {
    val s = Simulator.SwitchTable.apply(
      1,
      (2022, 105, 2, 1, 1, 1),
      (2022, 10, 2),
      50000
    )
    assertThrows[DateTimeException] {
      simulator.simulateSwitchTable(s)
    }
  }
}
