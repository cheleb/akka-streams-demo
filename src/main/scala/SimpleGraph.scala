
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.stream.scaladsl._

/**
  * Created by chelebithil on 27/01/2016.
  */
object SimpleGraph extends App with StreamRunner {

  val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[Unit] =>
    import GraphDSL.Implicits._

    val in = Source(1 to 10)
    val out = Sink.foreach[Int](x => println(x))

    val bcast = builder.add(Broadcast[Int](2))
    val merge = builder.add(Merge[Int](2))


    val f1, f2, f3, f4 = Flow[Int].map(_ + 10)

    in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out
    bcast ~> f4 ~> merge
    ClosedShape


  })

  val f = g.run()

}
