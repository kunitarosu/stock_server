package jp.co.sample.highcharts.controller

import io.finch.circe._
import io.finch.{Endpoint, _}
import io.circe.generic.auto._
import jp.co.sample.highcharts.dto.CandleStick
import jp.co.sample.highcharts.util.{CsvReader, Mt4Converter}

object StockViewController {

  lazy val all = csvToJson :+: csvToHighchartsData

  lazy val csvToJson: Endpoint[Seq[CandleStick]] = post("csvToJson" :: jsonBody[Map[String, String]]) { (map: Map[String, String]) =>

    val resoucePath = getClass.getResource(s"/${map("fileName")}");
    Ok(CsvReader.read(resoucePath.getPath))
  }

  lazy val csvToHighchartsData: Endpoint[Seq[Seq[BigDecimal]]] = post("csvToHighchartsOHLC" :: jsonBody[Map[String, String]]) { (map: Map[String, String]) =>

    val resoucePath = getClass.getResource(s"/${map("fileName")}");
    val ohlcResults: Seq[Seq[BigDecimal]] = CsvReader.read(resoucePath.getPath)
      .map(candleStick => Seq(
        BigDecimal(Mt4Converter.toTimeInMilles(candleStick.date, candleStick.time)),
        BigDecimal(candleStick.open),
        BigDecimal(candleStick.high),
        BigDecimal(candleStick.low),
        BigDecimal(candleStick.close),
        BigDecimal(candleStick.volume)
      ))
    Ok(ohlcResults)
  }
}
