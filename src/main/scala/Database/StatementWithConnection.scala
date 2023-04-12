package Database

import java.sql.{DriverManager, ResultSet, Statement}
import scala.util.{Failure, Success, Try}

class StatementWithConnection {
  def withConnection[T](f: Statement => T): T = {
    Try {
      Try {

        DriverManager.getConnection(
          "jdbc:mysql://127.0.0.1:9906/traffic",
          "tata",
          "tata123")
      } .map { connection =>
        val statement = connection.createStatement(
          ResultSet.TYPE_SCROLL_INSENSITIVE,
          ResultSet.CONCUR_READ_ONLY
        )
        val result = f(statement)
        result
      }.get

    } match
      case Success(result) =>
        println(
          "Connection established, ResultSet is setup, Statement created!"
        )
        result
      case Failure(exception) =>
        println(exception)
        Failure(exception).get

  }
}