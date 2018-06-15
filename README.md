# Akka HTTP

Akka HTTP is a streaming first library that uses Akka streams and actors. Actors deal with incoming HTTP events as messages.
Akka HTTP was initially known as Spray. It currently implements HTTP2 asynchronously, with the intention of speeding up the internet.
<br><br>
- Branch out to explore a minimal Akka HTTP Server
````
git checkout -b akka_http_server master
````
- Add the Akka Dependency to the build.sbt file:
````
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.5"
````
- Create a minimal server: <b>com.github.janikibichi.learnakka.http.MinimalHTTPServer.scala</b>
- Run the App
````
sbt "runMain com.github.janikibichi.learnakka.http.MinimalHTTPServerApp"
````
- Run a curl command to receive a GET response from the running server:
````
curl http://127.0.0.1:8088/v1/id/ALICE
````
<br><br>
- Branch out to explore consuming AKKA Http from client Side
````
git checkout -b client_side_akka_http akka_http_server
````
- Create file: <b>com.github.janikibichi.learnakka.http.ConnectionLevelClientAPIApp.scala</b>
- Create file: <b>com.github.janikibichi.learnakka.http.HostLevelClientAPIApp.scala</b>
- Create file: <b>com.github.janikibichi.learnakka.http.RequestLevelClientAPIApp.scala</b>
-Run the [connection level Client API App](https://asciinema.org/a/au41haWcDr8Yc4lHQU9RyfAzW)
````
sbt "runMain com.github.janikibichi.learnakka.http.ConnectionLevelClientAPIApp"
````
- Run the [host level client API App](https://asciinema.org/a/YJ48yhY3ckRFB9bN9cnEcpfyS)
````
sbt "runMain com.github.janikibichi.learnakka.http.HostLevelClientAPIApp"
````
-Run the [request level client API App](https://asciinema.org/a/FeyOM6b5yYWykLmvXtCn5HuGe)
````
sbt "runMain com.github.janikibichi.learnakka.http.RequestLevelClientAPIApp"
````
<br><br>
- Branch out to explore routing DSL for Http Servers
````
git checkout -b http_server_routing_dsl client_side_akka

````
- Create file: <b>com.github.janikibichi.learnakka.http.InMemoryStorageRESTAPI.scala</b>
- Create the objects to store in cache: <b>com.github.janikibichi.learnakka.http.TemperatureMeasurement.scala</b>
- Create the file to handle the Actual REST API: <b>com.github.janikibichi.learnakka.http.CRUDHandlers.scala</b>
- Create the file to min in the traits and extend Akka http: <b>com.github.janikibichi.learnakka.http.WritingRoutingDSL.scala</b>
- Run the App to test [In Memory Storage App](https://asciinema.org/a/w8qcJBRYsNiz3S1pkaDzOLDVh)
````
sbt "runMain com.github.janikibichi.learnakka.http.TemperatureInMemoryStorageApiApplication"
````
- Run requests in [CURL to test APIs](https://asciinema.org/a/T7iEGH0dEB0J1kExxpW3dnSrP)
````
curl -X POST --data "Chicago, 20.0" http://127.0.0.1:8088/v1/temperature/

curl -X POST --data "Madrid, 15.0" http://127.0.0.1:8088/v1/temperature/

curl -X GET http://127.0.0.1:8088/v1/temperature/
````
<br><br>
- Branch out to explore writing Http Akka configurations
````
git checkout -b akka_http_config http_server_routing_dsl
````
- Create a file application.conf inside src/main/resources
<br><br>
- Branch out to explore marshalling and unmarshalling
````
git checkout -b marshalling_unmarshalling akka_http_config
````
- Create a file: <b>com.github.janikibichi.learnakka.http.SpeedMeasurement.scala</b>
- Create a file to run the App: <b>com.github.janikibichi.learnakka.http.MarshallingApplication.scala</b>
- [Run the Application:](https://asciinema.org/a/doWA6WtUhWnhQTRsFbxOAbNjD)
````
sbt "runMain com.github.janikibichi.learnakka.http.MarshallingApplication"
````
- [Call the GET/POST CURL calls](https://asciinema.org/a/iqV8ti2oR5EcTaENhCEINrbqH)
````
curl -X POST --data "140000000 40.42015 -3.70578 56.0" http://127.0.0.1:8088/

curl -X GET http://127.0.0.1:8088/
````
<br><br>
- Branch out to explore encoding and decoding data
````
git checkout -b encoding_decoding_data marshalling_unmarshalling
````
- Create file for server code: <b>com.github.janikibichi.learnakka.http.EncodingDecodingServerApp.scala</b>
- Create file for client code: <b>com.github.janikibichi.learnakka.http.EncodingDecodingClientApp.scala</b>
- [Run the server application](https://asciinema.org/a/KdvX89bRPjGHIYUET2rMQqCr8)
````
sbt "runMain com.github.janikibichi.learnakka.http.EncodingDecodingServerApp"
````
- [Run the client application](https://asciinema.org/a/WYRjII2IEN59eZMYHpv96wOHn)
````
sbt "runMain com.github.janikibichi.learnakka.http.EncodingDecodingClientApp"
````
<br><br>
- Branch out to explore custom directives
````
git checkout -b custom_directives encoding_decoding_data
````
- Add new dependency for dropwizard to build.sbt
````
libraryDependencies += "io.dropwizard" % "dropwizard-core" % "1.0.6"
````
- Create a file: <b>com.github.janikibichi.learnakka.http.CustomDirectivesApp.scala</b>
- [Run the App to see the console reporter reporting metrics:](https://asciinema.org/a/6zoN4JDnX0EhU9cwIFF7uXdVJ)
````
sbt "runMain com.github.janikibichi.learnakka.http.CustomDirectivesApp"
````
- [Run CURL requests to the Akka Server:](https://asciinema.org/a/pPt79wzZLVscKIqeEnW2c4ptS)
````
curl -X POST http://127.0.0.1:8088
curl -X POST http://127.0.0.1:8088
curl -X GET http://127.0.0.1:8088
curl -X GET http://127.0.0.1:8088
curl -X PUT http://127.0.0.1:8088
````
<br><br>
- Branch out to explore exception handling
````
git checkout -b exception_handling custom_directives
````
- Create File: <b>com.github.janikibichi.learnakka.http.RouteExceptionHandler.scala</b>
- Create an actor that ignores all messages: <b>com.github.janikibichi.learnakka.http.UnresponsiveActor.scala</b>
- [Run the app to show HandlingExceptions]()
````
sbt "runMain com.github.janikibichi.learnakka.http.HandlingExceptionsApp"
````
- Send curl requests to test Exceptions
````
curl -X GET http://127.0.0.1:8088/divide?a=20&b=10
curl -X GET http://127.0.0.1:8088/divide?a=20&b=0
curl -X GET http://127.0.0.1:8088/futureTimingOut
````
<br><br>
-Branch to explore file uploading using Akka Http
````
git checkout -b file_uploading exception_handling
````
- Create a file to : <b>com.github.janikibichi.learnakka.http.UploadingFileServerApp.scala</b>
- Create a file to hit our endpoints: <b>com.github.janikibichi.learnakka.http.UploadingFileClient.scala</b>
- Create file testFile.txt in src/main/resources to be consumed
- [Run the server:](https://asciinema.org/a/ksNhscDR9ojDtGbXPTu79plCD)
````
sbt "runMain com.github.janikibichi.learnakka.http.UploadingFileServerApp"
````
- [Run the client to upload:](https://asciinema.org/a/bvdhIg14cSfvieNgk8n1U7nDc)
````
sbt "runMain com.github.janikibichi.learnakka.http.UploadingFileClient"
````
<br><br>
- Branch to explore JSON support in Akka Http
````
git checkout -b akka_http_json_support file_uploading
````
- Add the required spray-json dependency
````
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5"
````
- Create a file, with case classes,marshalling/unmarshalling:<b>com.github.janikibichi.learnakka.http.OrderModel.scala</b>
- Create file for route and app: <b>com.github.janikibichi.learnakka.http.OrderCalculatorJsonApp.scala</b>
- [Run the server App:]()
````
sbt "runMain com.github.janikibichi.learnakka.http.OrderCalculatorJsonServerApp"
````
- [Generate a random order from the endpoint:]()
````
curl -X GET http://127.0.0.1:8088/randomOrder
````
- POST to calculateGrandTotal endpoint
````
curl -X POST -H "Content-Type: application/json" --data
'{"deliveryPrice":40.78967065678465,
  "timestamp":1529072706538,
  "items":[{"id":0,"quantity":39,"unitPrice":62.71743225100119,"percentageDiscount":0.718874975037442},{"id":1,"quantity":97,"unitPrice":32.65086872858111,"percentageDiscount":0.2307965856942381},{"id":2,"quantity":74,"unitPrice":51.16980368241938},{"id":3,"quantity":61,"unitPrice":26.298491708980443,"percentageDiscount":0.3595474058385193},{"id":4,"quantity":61,"unitPrice":67.83301852775391,"percentageDiscount":0.01743796408711551}],
  "id":"甠Ḃ뎥慩",
  "metadata":{"notes":"random"}}' "http://127.0.0.1:8088/calculateGrandTotal"
````
<br><br>
- Branch out to explore XML support in Akka Http
````
git checkout -b akka_http_xml_support akka_http_json_support
````