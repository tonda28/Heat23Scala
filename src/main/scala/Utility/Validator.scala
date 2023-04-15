package Utility

import Database.SetDataRepository.ModelTableSwitch

import java.time.{LocalDate, LocalTime}
import scala.collection.immutable.Seq
import scala.util.Try

trait Validator {

  private def setSingleDay(
      date: LocalDate,
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = {
    data
      .filter(x =>
        x.localdate.getYear() == date.getYear && x._4
          .getMonthValue() == date
          .getMonthValue() && x.localdate.getDayOfMonth == date.getDayOfMonth
      )
  }

  private def setSingleMonth(
      date: LocalDate,
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = {
    data
      .filter(x =>
        x.localdate.getYear() == date.getYear && x._4
          .getMonthValue() == date
          .getMonthValue()
      )
  }
  private def setSingleSeason(
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = { data }

  private def setDefaultRecords(
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] =
    if (data.size < 1) {
      Seq[ModelTableSwitch](
        ModelTableSwitch(1, 0, LocalTime.of(0, 0, 2), LocalDate.of(2022, 1, 1)),
        ModelTableSwitch(0, 0, LocalTime.of(0, 0, 3), LocalDate.of(2022, 1, 1))
      )
    } else {
      data
    }
  private def setLastMandatoryRecord(
      checkLast: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = {
    if (checkLast.last.stav != 0) {
      checkLast :+ ModelTableSwitch(
        0,
        6.22,
        LocalTime
          .of(23, 59, 59),
        LocalDate.of(2022, 1, 1)
      )
    } else {
      checkLast
    }
  }

  private def setFirstMandatoryRecord(
      checkFirst: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = if (checkFirst.head.stav != 1) {
    ModelTableSwitch(
      1,
      6.22,
      LocalTime.of(0, 0, 1),
      LocalDate.of(2022, 1, 1)
    ) +: checkFirst
  } else {
    checkFirst
  }

  def validateSingleDay(
      date: LocalDate,
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = {
    Try {
      setSingleDay(date, data)
    }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }

  def validateSingleMonth(
      date: LocalDate,
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = {
    Try {
      setSingleMonth(date, data)
    }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }

  def validateSingleSeason(
      data: Seq[ModelTableSwitch]
  ): Seq[ModelTableSwitch] = {
    Try { setSingleSeason(data) }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }
}
