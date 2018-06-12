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
- [Run the App](https://asciinema.org/a/4cFK8XdtEQ29kUQ41bbLeZaKC)
````
sbt "runMain com.github.janikibichi.learnakka.http.MinimalHTTPServerApp"
````
- [Run a curl command to receive a GET response from the running server:](https://asciinema.org/a/LS9gM1R3jbixtXLktWICKfXRS)
````
curl http://127.0.0.1:8088/v1/id/ALICE
````
- [Run a curl command to recieve a POST response from the running server:](https://asciinema.org/a/CHsLNXZl80ab1Yl3EoV23dJhJ)
````
curl -X POST --data "Akka Http is Cool" http://localhost:8088/v1/id/ALICE
````

