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
