package com.monsanto.arch.cloudformation.model.resource

import com.monsanto.arch.cloudformation.model._
import org.scalatest.{FunSpec, Matchers}
import spray.json._

class Kinesis_UT extends FunSpec with Matchers {
  describe("Stream") {
    val streamName = "stream"
    val shardCount = 1
    val stream = `AWS::Kinesis::Stream`(streamName, shardCount, Seq(AmazonTag("Name", streamName)))

    it("should write a valid Kinesis stream") {
      stream.toJson shouldEqual JsObject(Map(
        "name" -> JsString("stream"),
        "ShardCount" -> JsNumber(1),
        "Tags" -> JsArray(JsObject(Map("Key" -> JsString("Name"), "Value" -> JsString("stream"))))
      ))
    }

    it("should have properly set public fields") {
      stream.name shouldEqual streamName
      stream.ShardCount shouldEqual IntToken(shardCount)
      stream.Tags.get shouldEqual Seq(AmazonTag("Name", streamName))
    }
  }
}
