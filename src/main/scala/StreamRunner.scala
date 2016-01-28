import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

/**
  * Created by chelebithil on 28/01/2016.
  */
trait StreamRunner {
  implicit val actorSystem = ActorSystem("reactiveStreams")
  implicit val materilizer = ActorMaterializer()

}
