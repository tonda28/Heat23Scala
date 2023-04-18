package Utility

import org.scalatest.GivenWhenThen
import org.scalatest.funsuite.AnyFunSuite

import java.time.{DateTimeException, LocalDate}
import scala.collection.mutable

class ParserTest extends AnyFunSuite with Utility.Parser {
  test("Parser should have given date.") {
    assert(parse("2022-10-1") == LocalDate.of(2022, 10, 1))
  }

  test("Bad number of month-day should have throw exception") {
    assertThrows[DateTimeException] {
      parse("2022-101-3")
    }
  }

  test("Bad string should have throw exception.") {
    assertThrows[NumberFormatException] {
      parse("")
    }
  }

  test("Empty string shoud have throw exception.") {
    assertThrows[NumberFormatException] {
      parse("xx")
    }
  }
}
