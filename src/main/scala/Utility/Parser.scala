package Utility

import java.time.LocalDate

trait Parser {
  def parse(selectedDate: String): LocalDate = {
    val arrDate = selectedDate.split("-")
    val year = arrDate(0).toInt
    val month = arrDate(1).toInt
    val day = arrDate(2).toInt

    def myDt = LocalDate.of(year, month, day).toString
    LocalDate.parse(myDt)
  }
}
