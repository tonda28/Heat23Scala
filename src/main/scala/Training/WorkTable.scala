package Training

import Set.SetOutput.CaseMenuMonthSelector
import Set.SetSeasonTime.CaseMarkerHeatingSeason

import java.time.LocalDate
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered
import scala.util.Try

object WorkTable {
  import Database.GivensDataRepository.given_CaseRepository
  def getMe(using data: Database.SetDataRepository.CaseRepository)
   = {

    val a = data.dataSwitchOn.groupBy(identity(x => x.localdate.getMonthValue)).keys.toSeq
    var result = ListBuffer[CaseMenuMonthSelector]()
          for (item <- a)
            result += CaseMenuMonthSelector.apply(
              date = data
                .dataSwitchOn
                .filter(x => x.localdate.getMonthValue == item)
                .head.localdate
            )
          result.toList.sortBy(x => x.date)
  }


  println(getMe)
}
