package Utility

import org.scalatest.{GivenWhenThen, funsuite}

import java.time.{LocalDate, LocalTime}
import scala.collection.mutable

class ValidatorTest
    extends funsuite.AnyFunSuite
    with Validator
    with GivenWhenThen {
  val obj = new Simulator.DataSimulator()

  test("Seq.head.stav should be 1 and last 0, validator changed nothing.") {
    val a = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 1, 1, 1),
      (2022, 11, 1),
      50000
    )
    val b = obj.simulateSwitchTable(a)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), b)
    assert(data.head.stav == 1)
    assert(data.last.stav == 0)
  }

  test(
    "Seq.head.stav 0 must be changed to value 1 and Seq.last.stav have to be 0"
  ) {
    val a = Simulator.SwitchTable.apply(
      0, // Have to be changed to 1
      (2022, 10, 1, 1, 1, 1),
      (2022, 11, 1),
      50000
    )
    val b = obj.simulateSwitchTable(a)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), b)
    assert(data.head.stav == 1)
    assert(data.last.stav == 0)
  }

  test("Seq.last.stav 1 must be changed to value 0") {
    val a = Simulator.SwitchTable.apply(
      0,
      (2022, 10, 1, 1, 1, 1),
      (2022, 11, 10),
      10
    )
    val b = obj.simulateSwitchTable(a)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), b)
    assert(data.head.stav == 1)
    assert(data.last.stav == 0)
  }

  test(
    "Before midnight, last datetime of the day have to be 23-59-59 or less and stav have to be 0"
  ) {
    val a = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 1, 23, 59, 55),
      (2022, 11, 10),
      10
    )
    val b = obj.simulateSwitchTable(a)
    val data = validateSingleDay(LocalDate.of(2022, 10, 1), b)
    assert(data.last.stav == 0)
    assert(data.last.localtime == LocalTime.of(23, 59, 59))

  }

  test(
    "After midnight, first datetime of the day have to be 0-1-1 or more and stav have to be 1"
  ) {
    val a = Simulator.SwitchTable.apply(
      1,
      (2022, 10, 31, 23, 59, 55),
      (2022, 11, 1),
      10000
    )
    val b = obj.simulateSwitchTable(a)
    val data = validateSingleDay(LocalDate.of(2022, 11, 1), b)
    assert(data.head.stav == 1)
    assert(data.head.localtime == LocalTime.of(0, 0, 1))

  }

}
