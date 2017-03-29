package jp.co.sample.highcharts

import com.twitter.finagle.Http
import com.twitter.util.Await
import io.circe.generic.auto._
import io.finch.Application
import io.finch.circe._
import jp.co.sample.highcharts.controller.StockViewController

/**
  * Created by b07095 on 2017/03/29.
  */
object Main extends App {
  val api = StockViewController.all
  Await.ready(Http.server.serve("localhost:8080", api.toServiceAs[Application.Json]))
}
