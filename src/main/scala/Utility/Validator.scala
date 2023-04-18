package Utility

import java.time.{LocalDate, LocalTime}
import scala.collection.immutable.Seq
import scala.collection.immutable.Stream.{Empty, empty}
import scala.util.Try
import scala.xml.Null

trait Validator {

  private def setSingleDay(
      date: LocalDate,
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = {
    data
      .filter(x =>
        x.localdate.getYear() == date.getYear && x._4
          .getMonthValue() == date
          .getMonthValue() && x.localdate.getDayOfMonth == date.getDayOfMonth
      )
  }

  private def setSingleMonth(
      date: LocalDate,
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = {
    data
      .filter(x =>
        x.localdate.getYear() == date.getYear && x._4
          .getMonthValue() == date
          .getMonthValue()
      )
  }

  private def setSingleSeason(
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = { data }

  private def setDefaultRecords(
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] =
    if (data.size < 1) {
      Seq[Database.ModelTableSwitch](
        Database.ModelTableSwitch(
          1,
          0,
          LocalTime.of(0, 0, 2),
          LocalDate.of(2022, 1, 1)
        ),
        Database.ModelTableSwitch(
          0,
          0,
          LocalTime.of(0, 0, 3),
          LocalDate.of(2022, 1, 1)
        )
      )
    } else {
      data
    }
  private def setLastMandatoryRecord(
      checkLast: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = {
    if (checkLast.last.stav != 0) {
      checkLast :+ Database.ModelTableSwitch(
        0,
        6.22,
        LocalTime
          .of(23, 59, 59),
        LocalDate.now
      )
    } else {
      checkLast
    }
  }

  private def setFirstMandatoryRecord(
      checkFirst: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = if (checkFirst.head.stav != 1) {
    Database.ModelTableSwitch(
      1,
      6.22,
      LocalTime.of(0, 0, 1),
      LocalDate.now()
    ) +: checkFirst
  } else {
    checkFirst
  }

  def validateSingleDay(
      date: LocalDate,
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = {
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
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = {
    Try {
      setSingleMonth(date, data)
    }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }

  def validateSingleSeason(
      data: Seq[Database.ModelTableSwitch]
  ): Seq[Database.ModelTableSwitch] = {
    Try { setSingleSeason(data) }
      .map(setDefaultRecords)
      .map(setLastMandatoryRecord)
      .map(setFirstMandatoryRecord)
      .get
  }
}
