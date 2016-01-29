import akka.stream._
import akka.stream.stage.{GraphStage, GraphStageLogic, InHandler, OutHandler}

/**
  * Created by chelebithil on 28/01/2016.
  */
class SplitOnTypeStageGraph extends GraphStage[FanOutShape2[A, B, C]] {

  override def initialAttributes = Attributes.name("Split")

  override val shape = new FanOutShape2[A, B, C]("Split")

  def in = shape.in

  def out0 = shape.out0

  def out1 = shape.out1


  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {
    setHandler(in, new InHandler {
      override def onPush(): Unit = {
        val aa: A = grab(in)
        aa match {
          case c: C =>
            if (isAvailable(out1))
              push(out1, c)
            else
              println("ouille")
          case b: B =>
            if (isAvailable(out0))
              push(out0, b)
            else
              println("ouille")

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