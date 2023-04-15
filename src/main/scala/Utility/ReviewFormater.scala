package Utility

import Database.SetDataRepository.ModelTableSwitch
import Case.SetOutput.CaseReview

import java.time.Duration
import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

trait ReviewFormater {
  private def checkValidation[B <: ModelTableSwitch](dataValidated: Seq[B]): Seq[B] = {
    Try {
      assert(dataValidated.head.stav == 1)
      assert(dataValidated.last.stav == 0)
    } match
      case Failure(exception) => Failure(exception).get
      case Success(value) => dataValidated
  }

  private def reformatToReview[B <: ModelTableSwitch, C >: CaseReview](dataValidated: Seq[B]): Seq[C] = {

    def isEven(number: Int) = number % 2 == 0

    def isOdd(number: Int) = !isEven(number)

    val even = dataValidated.filter(x => isEven(x.stav))
    val odd = dataValidated.filter(x => isOdd(x.stav))

    val oddIsStart = odd.map(x => x.localtime)
    val evenIsStop = even.map(x => x.localtime)

    val duration =
      evenIsStop zip oddIsStart map (x => Duration.between(x._1, x._2))
    val temper = odd.map(x => x.temper)
    var result = ListBuffer[CaseReview]()
    oddIsStart zip evenIsStop zip duration zip temper map {
      case (((a, b), c), d) =>
        // {(a, b, c, d)
        result += CaseReview(on = a, off = b, duration = c, temper = d)
    }

    result.toSeq
  }

  def getReview(dataValidated: Seq[ModelTableSwitch]): Seq[CaseReview] = {
    reformatToReview(checkValidation(dataValidated))
  }
  
}
