package Utility

import org.scalatest.{GivenWhenThen, funsuite}

import java.time.{LocalDate, LocalTime}
import scala.collection.mutable

class ValidatorTest
    extends funsuite.AnyFunSuite
    with Validator
    with GivenWhenThen {
  val simulator = new Simulator.DataSimulator()

  test(
    "Before midnight, must be stav.head = 0(off), after midnight must be stav.last = 1(on)"
  ) {
    val setParametrs = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 1, 1, 1),
      (2022, 11, 1),
      50000
    )
    val simulatedData = simulator.simulateSwitchTable(setParametrs)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), simulatedData)
    assert(data.head.stav == 1)
    assert(data.last.stav == 0)
  }

  test(
    "Before midnight must be virtualy set stav.last = 0(Off)" +
      "0,1,0,1... have to be changed to 1,0,1,0..."
  ) {
    val setParametrs = Simulator.SwitchTable.apply(
      0,
      (2022, 10, 1, 1, 1, 1),
      (2022, 11, 1),
      50000
    )
    val simulatedData = simulator.simulateSwitchTable(setParametrs)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), simulatedData)
    assert(data.head.stav == 1)
    assert(data.last.stav == 0)
  }

  test(
    "After midnight must be virtualy set stav.head = 1(On)" +
      "...0,1,0,1,0,1 must be changed to ...0,1,0,1,0,1,0"
  ) {
    //
    val setParametrs = Simulator.SwitchTable.apply(
      0,
      (2022, 10, 1, 1, 1, 1),
      (2022, 11, 10),
      6
    )
    val simulatedData = simulator.simulateSwitchTable(setParametrs)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), simulatedData)
    assert(data.head.stav == 1)
    assert(data.last.stav == 0)
  }

  test(
    "Before midnight, if stav.last = 1(On), last datetime of the day have to be set virtualy at 23-59-59" +
      " and stav.last = 0(Off)"
  ) {
    val setParametrs = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 23, 59, 55),
      (2022, 11, 10),
      10
    )
    val simulatedData = simulator.simulateSwitchTable(setParametrs)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), simulatedData)
    assert(data.last.stav == 0)
    assert(data.last.localtime == LocalTime.of(23, 59, 59))

  }

  test(
    "After midnight, if stav.head = 0, first datetime of the day have to be 0-1-1" +
      "and stav.head = 1(On)"
  ) {
    val setParametrs = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 31, 23, 59, 55),
      (2022, 11, 1),
      10000
    )
    val simulatedData = simulator.simulateSwitchTable(setParametrs)
    val data = validateSingleDay(LocalDate.of(2022, 11, 1), simulatedData)
    assert(data.head.stav == 1)
    assert(data.head.localtime == LocalTime.of(0, 0, 1))

  }

  
}
