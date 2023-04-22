import User.FrameReview
import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDate

class ReviewFormaterTest
    extends AnyFunSuite
    with Utility.ReviewFormater
    with User.Output {
  val simulator = new Simulator.DataSimulator()
  test("Check productPrefix") {
    val setParametrs = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 1, 1, 1),
      (2023, 4, 30),
      50000
    )
    val simulatedData = simulator.simulateSwitchTable(setParametrs)
    val data = format(simulatedData)
    val result = data.map(x => x.productPrefix).head
    assert(result == "FrameReview")

  }

  test("Check elements in FrameReview") {
    val day = LocalDate.of(2023, 1, 28)
    val setParametrs = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 1, 1, 1),
      (2023, 4, 30),
      50000
    )

    val item = format { simulator.simulateSwitchTable(setParametrs) }
      .map(_.productElementNames)
      .head
      .toSeq

    assert(
      (
        item(0),
        item(1),
        item(2),
        item(3)
      ) == ("on", "off", "duration", "temper")
    )
  }

}
