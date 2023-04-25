package User

import Simulator.DataSimulator
import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDate

class OutputTest extends AnyFunSuite with Output {
  val simulator = new Simulator.DataSimulator()

  test("Select months for menu.") {
    val a = Simulator.SwitchTable.apply(
      setStav = 1,
      setDateTimeFirst = (2022, 10, 1, 1, 1, 1),
      setDateLast = (2023, 4, 30),
      setRecordRows = 50000
    )
    val switchTable = simulator.simulateSwitchTable(a)
    val testedData = getMenuMonthSelector(switchTable)
    assert(testedData.size == 7)
    assert(testedData.head.date == LocalDate.of(2022, 10, 1))
    assert(testedData.last.date == LocalDate.of(2023, 4, 1))
  }

  test("Select day for menu. ") {
    val day = LocalDate.of(2023, 1, 28)
    val a = Simulator.SummaryTable.apply(
      setFirstDate = (2022, 10, 1),
      setLastDate = (2023, 4, 30)
    )
    val summaryTable = simulator.simulateSumTable(a)
    val testedData = getMenuDaySelector(day, summaryTable)
    assert(testedData.size == 31)
    assert(testedData.head.localdate == LocalDate.of(2023, 1, 1))
    assert(testedData.last.localdate == LocalDate.of(2023, 1, 31))
  }

  test("DetailReview - new format.") {
    val day = LocalDate.of(2023, 1, 28)
    val setSimulator = Simulator.SwitchTable.apply(
      setStav = 1,
      setDateTimeFirst = (2022, 10, 1, 1, 1, 1),
      setDateLast = (2023, 4, 30),
      setRecordRows = 50000
    )
    val switchTable = simulator.simulateSwitchTable(setSimulator)
    val testedData = getFrameDailyDetailsReview(day, switchTable)
    val prefix = testedData.map(x => x.productPrefix).head
    val elements = testedData.map(_.productElementNames).head.toList
    assert(prefix == "FrameReview")
    assert(elements.size == 4)
    assert(
      (
        elements.head,
        elements(1),
        elements(2),
        elements(3)
      ) == ("on", "off", "duration", "temper")
    )
  }

}
