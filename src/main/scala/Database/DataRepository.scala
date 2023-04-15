package Database

import Database.StatementWithConnection

import java.sql.{DriverManager, ResultSet, Statement}
import java.time.{Duration, LocalDate, LocalTime}
import scala.util.{Failure, Success, Try}

class DataRepository {

 private val conn = new StatementWithConnection()

  val durationSwitchOn: Seq[ModelTableSwitch] =
    Try {
      Try {
        conn.withConnection(statement =>
          statement.executeQuery("select * from stav")
        )
      }
        .map(x => {
          Iterator
            .continually((x, x.next))
            .takeWhile(_._2)
            .map(_._1)
            .map { res =>
              val stav = res.getInt("stav")
              val temper = res.getDouble("temper")
              val localtime = res.getTime("datetime").toLocalTime
              val localdate = res.getDate("datetime").toLocalDate

              ModelTableSwitch.apply(stav, temper, localtime, localdate)
            }
            .toSeq
        })
        .get
    } match
      case Success(value) =>
        println("DataSwitch are on the stage!")
        value
      case Failure(exception) => println(exception)
        Seq[Database.ModelTableSwitch]()




  val dailyTrafficSummary: Seq[Database.ModelTableSummary] =
    Try {
      Try {
        conn.withConnection(statement =>
          statement.executeQuery("select * from stavSum")
        )
      }.map(x => {
        Iterator
          .continually((x, x.next))
          .takeWhile(_._2)
          .map(_._1)
          .map { res =>
            val localdate = res.getDate("datetime").toLocalDate
            val duration = Duration.ofSeconds(
              x.getTime("daysum").toLocalTime.toSecondOfDay
            )

            ModelTableSummary.apply(localdate, duration)
          }
          .toSeq
      }).get
    } match
      case Success(value) =>
        println("DataSummary are on the stage!")
        value
      case Failure(exception) => println(exception)
        Seq[Database.ModelTableSummary]()
}
