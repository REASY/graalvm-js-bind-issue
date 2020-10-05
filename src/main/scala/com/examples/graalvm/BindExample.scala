package com.examples.graalvm

import com.examples.graalvm.blink.Document
import com.typesafe.scalalogging.LazyLogging
import org.graalvm.polyglot.{Context, Source}

object BindExample extends LazyLogging {
  def execute(script: String): Unit = {
    val ctx = Context.newBuilder("js")
      .allowAllAccess(true)
      .allowExperimentalOptions(true)
      .option("js.experimental-foreign-object-prototype", "true")
      .build()

    // Set this as a window
    ctx.eval(Source.newBuilder("js","var window = this;", "init.js").build)

    // Get a reference to window to set document member
    val jsWindow = ctx.eval("js", "window;")
    jsWindow.putMember("document", new Document)

    // Execute passed script
    ctx.eval(Source.newBuilder("js", script, "bind_example.js").build())
    ()
  }

  def main(args: Array[String]): Unit = {
    val scriptToExecute = FileUtil.read(getClass.getResourceAsStream("/bind_example.js")).toString
    execute(scriptToExecute)
    ()
  }
}
