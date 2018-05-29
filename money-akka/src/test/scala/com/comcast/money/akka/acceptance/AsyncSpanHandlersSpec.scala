/*
 * Copyright 2012-2015 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.money.akka.acceptance

import akka.actor.ActorSystem
import akka.testkit.TestKit
import com.comcast.money.akka.MoneyExtension
import com.comcast.money.core.handlers.AsyncSpanHandler
import com.typesafe.config.ConfigFactory
import org.scalatest.{Matchers, WordSpecLike}

class AsyncSpanHandlersSpec(_system: ActorSystem) extends TestKit(_system) with WordSpecLike with Matchers {

  def this() = this {
    val configString: String =
      """
        | money {
        |  handling = {
        |    handlers = [
        |    {
        |      class = "com.comcast.money.akka.CollectingSpanHandler"
        |      log-level = "INFO"
        |    }
        |    ]
        |  }
        | }""".stripMargin

    ActorSystem("AsyncSpanHandlersSpec", ConfigFactory.parseString(configString))
  }

  "A MoneyExtension should always use Async Span Handlers" in {
    MoneyExtension(system).handler shouldBe an[AsyncSpanHandler]
  }
}