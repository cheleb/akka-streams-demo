
import akka.stream.{FanOutShape2, ActorMaterializer, ClosedShape}
import akka.stream.scaladsl._

/**
  * Created by chelebithil on 28/01/2016.
  */

trait A
case class B(i: Int) extends A
case class C(i: Int) extends A

case class D(i: Int) extends A
case class E(i: Int) extends A
case class F(i: Int) extends A


object SplitFlows extends App with StreamRunner {

  val either = false

  val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[Unit] =>

    import GraphDSL.Implicits._

    //val src = Source(1 to 10).map(D(_))
    //val split = builder.add(new SplitStageGraph[D,E,F](a=>if(a.i%2==0)Left(E(a.i))else Right(F(a.i))))

    val src =  Source(1 to 10).map(x=>if(x%2==0)C(x)else B(x))
    val split = builder.add(new SplitOnTypeStageGraph)
    val odd = Sink.foreach[Any](x => println(s"odd $x"))
    val even = Sink.foreach[Any](x => println(s"even $x"))

    src ~> split.in
    split.out0 ~> odd
    split.out1 ~> even

    ClosedShape


  })

  g.run()
}
