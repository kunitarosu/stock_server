package jp.co.sample.highcharts.dto


case class CandleStick(
                        date: String,
                        time: String,
                        open: Double,
                        high: Double,
                        low: Double,
                        close: Double,
                        volume: Long
                      )
