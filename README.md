# stock_server

## Development server

```
$ git clone https://github.com/kunitarosu/stock_server.git

$ cd stock_server

$ sbt assembly

$ java -jar target/scala-2.12/stock_server-assembly-1.0.jar

$ curl -X GET http://localhost:8080/csvToHighchartsOHLC?fileName=EURJPY1440.csv
```
