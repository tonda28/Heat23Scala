package Workbench

import Database.{DataRepository, SetDataRepository}

import java.time.LocalDate

object WorkTable extends Logic.Output {
  val ld = LocalDate.of(2022,1,28)
  val obj = new Database.DataRepository()
  //val a = obj.durationSwitchOn
  import Database.GivensDataRepository.given_CaseRepository
  def a = getMenuDaySelector(ld)
}
