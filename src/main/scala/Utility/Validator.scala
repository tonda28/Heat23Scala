package Utility

import Database.GivensDataRepository.given_CaseRepository
import Database.SetDataRepository.{CaseRepository, ModelTableSwitch}

import java.time.{LocalDate, LocalTime}
import scala.collection.immutable.Seq
import scala.util.Try

trait Validator {

   private def setSingleDay[A <: LocalDate, B >: ModelTableSwitch, C <: CaseRepository](
      date: A)(
      using data: C
  ): Seq[B] = {
    data.dataSwitchOn
      .filter(x =>
        x.localdate.getYear() == date.getYear && x._4
          .getMonthValue() == date
          .getMonthValue() && x.localdate.getDayOfMonth == date.getDayOfMonth
      )
  }

    private def setSingleMonth[A <: LocalDate, B >: ModelTableSwitch, C <: CaseRepository](date: A)(
    using data: C):Seq[B]={
    data.dataSwitchOn
      .filter(x =>
        x.localdate.getYear() == date.getYear && x._4
          .getMonthValue() == date
          .getMonthValue()
      )
  }
    private def setSingleSeason[A <: LocalDate, B >: ModelTableSwitch, C <: CaseRepository](
    using data: C): Seq[B] = {data.dataSwitchOn}

    private def setDefaultRecords[B >: ModelTableSwitch <: ModelTableSwitch](data: Seq[B]):Seq[B] =
      if (data.size < 1) {
      Seq[B](
        ModelTableSwitch(1, 0, LocalTime.of(0, 0, 2), LocalDate.of(2022, 1, 1)),
        ModelTableSwitch(0, 0, LocalTime.of(0, 0, 3), LocalDate.of(2022, 1, 1))
      )
    } else {
      data
    }
    private def setLastMandatoryRecord[B >: ModelTableSwitch <: ModelTableSwitch](checkLast: Seq[B]): Seq[B] = {
      if (checkLast.last.stav != 0) {checkLast :+ ModelTableSwitch(
      0,
      6.22,
      LocalTime
        .of(23, 59, 59),
      LocalDate.of(2022, 1, 1)
    )
  } else {
    checkLast
  }}

    private def setFirstMandatoryRecord[B >: ModelTableSwitch <: ModelTableSwitch](
      checkFirst: Seq[B]
  ): Seq[B] = if (checkFirst.head.stav != 1) {
    ModelTableSwitch(
      1,
      6.22,
      LocalTime.of(0, 0, 1),
      LocalDate.of(2022, 1, 1)
    ) +: checkFirst
  } else {
    checkFirst
  }

    def validateSingleDay[
      A <: LocalDate,
      B >: ModelTableSwitch <: ModelTableSwitch
  ](date: A): Seq[B] = {
    Try {
      setSingleDay(date)
    }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }

    def validateSingleMonth[
    A <: LocalDate,
    B >: ModelTableSwitch <: ModelTableSwitch
  ](date: A): Seq[B] = {
    Try {
      setSingleMonth(date)
    }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }



    def validateSingleSeason(): Seq[ModelTableSwitch] = {
    val a = setSingleSeason
    val b = setDefaultRecords(a)
    val c = setLastMandatoryRecord(b)
    val d = setFirstMandatoryRecord(c)
    d
  }
}
