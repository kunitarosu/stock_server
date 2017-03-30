package jp.co.sample.highcharts.controller

import java.net.URL

import better.files._
import io.finch.circe._
import io.finch.{Endpoint, _}
import io.circe.generic.auto._
import jp.co.sample.highcharts.dto.CandleStick
import jp.co.sample.highcharts.util.{CsvReader, Mt4Converter}

import scala.io.Source

object StockViewController {

  val CSV_DIR = s"""${System.getProperty("user.home")}/workspace/stock_server/src/main/resources"""

  lazy val all = csvToJson :+: csvToHighchartsData

  lazy val csvToJson: Endpoint[Seq[CandleStick]] = get("csvToJson" :: param("fileName")) { fileName: String =>

    val filePath = file"$CSV_DIR/$fileName"
    Ok(CsvReader.read(filePath.path.toString))
  }

  lazy val csvToHighchartsData: Endpoint[Seq[Seq[BigDecimal]]] = get("csvToHighchartsOHLC" :: param("fileName")) { fileName: String =>

    val filePath = file"$CSV_DIR/$fileName"
    val ohlcResults: Seq[Seq[BigDecimal]] = CsvReader.read(filePath.path.toString)
      .map(candleStick => Seq(
        BigDecimal(Mt4Converter.toTimeInMilles(candleStick.date, candleStick.time)),
        BigDecimal(candleStick.open),
        BigDecimal(candleStick.high),
        BigDecimal(candleStick.low),
        BigDecimal(candleStick.close),
        BigDecimal(candleStick.volume)
      ))
    Ok(ohlcResults).withHeader("Access-Control-Allow-Origin", "*")
  }
}
