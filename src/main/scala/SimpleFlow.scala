import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

/**
  * Created by chelebithil on 27/01/2016.
  */
object SimpleFlow extends App {

  implicit val actorSystem = ActorSystem("reactiveStreams")
  implicit val materilizer = ActorMaterializer()


  val source = Source(1 to 10)
  val sink = Sink.fold[Int, Int](0)(_ + _)

  val fut: Future[Int] = source.runWith(sink)
  fut.onSuccess {
    case i =>
      println(i)
  }
  fut.onComplete {
    case Success(e) =>
      actorSystem.terminate()
  }

}
