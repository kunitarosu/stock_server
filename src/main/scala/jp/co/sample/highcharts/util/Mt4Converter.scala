package jp.co.sample.highcharts.util

import java.time.format.DateTimeFormatter
import java.time.{Instant, LocalDateTime, ZoneId}

object Mt4Converter {

  private val mt4DateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

  def toTimeInMilles(date: String, time: String): Long = {
    LocalDateTime.parse(s"$date $time", mt4DateTimeFormatter)
      .toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.EPOCH)).toEpochMilli;
  }
}
