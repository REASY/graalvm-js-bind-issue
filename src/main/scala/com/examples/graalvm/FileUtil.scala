package com.examples.graalvm

import java.io.{BufferedReader, InputStream, InputStreamReader}

import scala.annotation.tailrec

object FileUtil {
  def read(in: InputStream): StringBuffer = {
    val br: BufferedReader = new BufferedReader(new InputStreamReader(in))
    read(br)
  }

  def read(in: BufferedReader): StringBuffer = {
    @tailrec
    def readBuffer(maybeLine: Option[String], content: StringBuffer): StringBuffer = {
      maybeLine match {
        case Some(line) =>
          content.append(line)
          content.append("\r\n")
          readBuffer(Option(in.readLine()), content)
        case None =>
          content
      }
    }
    readBuffer(Option(in.readLine()), new StringBuffer)
  }

}
