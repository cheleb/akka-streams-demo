import akka.stream.ClosedShape
import akka.stream.scaladsl._

/**
  * Created by chelebithil on 28/01/2016.
  */
object UnzipSample extends App with StreamRunner {
  val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[Unit] =>

    import GraphDSL.Implicits._

    val src = Source(1 to 10)
    val odd = Sink.foreach[Int](x => println(s"odd $x"))
    val even = Sink.foreach[Int](x => println(s"even $x"))

    val split = builder.add(UnzipWith[Int,Int,Int]((b:Int)=>(b,b)))
    src ~> split.in
    split.out0 ~> odd
    split.out1 ~> even

    ClosedShape


  })

  val f = g.run()


}
