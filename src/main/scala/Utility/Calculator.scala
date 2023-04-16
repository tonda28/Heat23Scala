package Utility

import java.time.{Duration, LocalTime}

trait Calculator {
  def calculate(data: Seq[Database.ModelTableSwitch]): Duration = {
    def isEven(number: Int) = number % 2 == 0

    def isOdd(number: Int) = !isEven(number)

    val odd = data.filter(x => isOdd(x.stav))
    val even = data.filter(x => isEven(x.stav))

    val startStop: Seq[(LocalTime, LocalTime)] =
      odd.map(x => x.localtime) zip even.map(x => x.localtime)

    val oneRowDuration = startStop.map(x => Duration.between(x._1, x._2))
    val seconds = oneRowDuration.map(_.toSeconds).sum
    val result = Duration.ofSeconds(seconds)
    result
  }
}
