import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDate
import scala.concurrent.duration

class CalculatorTest
    extends AnyFunSuite
    with Utility.Validator
    with Utility.Calculator {
  val simulator = new Simulator.DataSimulator()

  test("Calculate column with duration, result should be 9.") {
    val setSwitchTable = Simulator.SwitchTable.apply(
      1,
      (2022, 11, 1, 23, 59, 50),
      (2022, 11, 1),
      50000
    )
    val setDay = LocalDate.of(2022, 11, 1)

    val simulatedSwitchTable = simulator.simulateSwitchTable(setSwitchTable)
    val validatedData = validateSingleDay(setDay, simulatedSwitchTable)
    val duration = calculate(validatedData).toSeconds
    assert(duration == 9)
  }
}
