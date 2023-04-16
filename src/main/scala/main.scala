import Database.DataRepository
import User.{ApplyInterfaceOne, OutputJson, Output}

import java.time.LocalDate

@main def main(): Unit = {
  println("New Begin!")

//  val ld = {
//    LocalDate.of(2022, 1, 28)
//  }
  val ld = "2022-2-1"

  val userInterfaceOne = new ApplyInterfaceOne(ld)

  println(userInterfaceOne.show.selectMonth)
  println(userInterfaceOne.show.selectDay)
  println(userInterfaceOne.show.showFrameDailyDetails)
  println(userInterfaceOne.show.showLabelDuration)
  println("xxxxxxxxxxx")
  println(userInterfaceOne.showJson.selectMonthJson)
  println(userInterfaceOne.showJson.selectDayJson)
  println(userInterfaceOne.showJson.showFrameDailyDetailsJson)
  println(userInterfaceOne.showJson.showLabelDurationJson)
}