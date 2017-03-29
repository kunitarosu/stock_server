package jp.co.sample.highcharts.util

import better.files.{File => ScalaFile}
import jp.co.sample.highcharts.dto.CandleStick

object CsvReader {

  def read(filepath: String): Seq[CandleStick] = {
    ScalaFile(filepath).lines
      .map(line => {
        val values = line.split(",")
        CandleStick(
          values(0),
          values(1),
          values(2).toDouble,
          values(3).toDouble,
          values(4).toDouble,
          values(5).toDouble,
          values(6).toLong
        )
      }).toSeq
  }
}

