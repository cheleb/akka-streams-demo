import akka.stream._
import akka.stream.scaladsl.Balance
import akka.stream.stage.{OutHandler, InHandler, GraphStageLogic, GraphStage}

/**
  * Created by chelebithil on 28/01/2016.
  */
class SplitStageGraph[A, B, C](p: A => Either[B, C]) extends GraphStage[FanOutShape2[A, B, C]] {

  override def initialAttributes = Attributes.name("Split")

  override val shape = new FanOutShape2[A, B, C]("Split")

  def in = shape.in

  def out0 = shape.out0

  def out1 = shape.out1


  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {
    setHandler(in, new InHandler {
      override def onPush(): Unit = {
        p(grab(in)) match {
          case Right(c) =>
            if (isAvailable(out1))
              push(out1, c)
          case Left(b) =>
            if (isAvailable(out0))
              push(out0, b)

        }
      }
    })

    setHandler(out0, new OutHandler {
      override def onPull(): Unit = {
        if (isAvailable(out0) && isAvailable(out1))
          pull(in)
      }
    })

    setHandler(out1, new OutHandler {
      override def onPull(): Unit =
        if (isAvailable(out0) && isAvailable(out1))
          pull(in)
    })
  }
}