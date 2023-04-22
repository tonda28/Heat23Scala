import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDate
import scala.concurrent.duration

class CalculatorTest
    extends AnyFunSuite
    with Utility.Validator
    with Utility.Calculator {
  val simulator = new Simulator.DataSimulator()

  test("Calculate column with duration, result should be 9.") {
    val switchTable = Simulator.SwitchTable.apply(
      1,
      (2022, 11, 1, 23, 59, 50),
      (2022, 11, 1),
      50000
    )
    val b = simulator.simulateSwitchTable(switchTable)
    val data = validateSingleDay(LocalDate.of(2022, 11, 1), b)
    val duration = calculate(data).toSeconds
    assert(duration == 9)
  }
}
