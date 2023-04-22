package Utility

import User.FrameReview
import java.time.Duration
import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

trait ReviewFormater {
  private def checkValidation(dataValidated: Seq[Database.ModelTableSwitch]): Seq[Database.ModelTableSwitch] = {
    Try {
      assert(dataValidated.head.stav == 1)
      assert(dataValidated.last.stav == 0)
    } match
      case Failure(exception) => Failure(exception).get
      case Success(_) => dataValidated
  }

  private def reformatToReview[C >: FrameReview](dataValidated: Seq[Database.ModelTableSwitch]): Seq[C] = {

    def isEven(number: Int) = number % 2 == 0

    def isOdd(number: Int) = !isEven(number)

    val even = dataValidated.filter(x => isEven(x.stav))
    val odd = dataValidated.filter(x => isOdd(x.stav))

    val oddIsStart = odd.map(x => x.localtime)
    val evenIsStop = even.map(x => x.localtime)

    val duration =
      evenIsStop zip oddIsStart map (x => Duration.between(x._1, x._2))
    val temper = odd.map(x => x.temper)
    val result = ListBuffer[FrameReview]()
    oddIsStart zip evenIsStop zip duration zip temper map {
      case (((a, b), c), d) =>
        // {(setTable, switchTable, c, d)
        result += FrameReview(on = a, off = b, duration = c, temper = d)
    }

    result.toSeq
  }

  def format(dataValidated: Seq[Database.ModelTableSwitch]): Seq[FrameReview] = {
    reformatToReview(checkValidation(dataValidated))
  }
  
}
