package org.dreambig

import java.io.File

import org.dreambig.utility.QRCodeUtil

import scala.util.{Failure, Success, Try}

/***
  * Entry Point
  */
object App  {


  def main(args: Array[String]): Unit = {
    Try {
      args.toList match {

        case "create" :: output :: payload :: Nil => QRCodeUtil.create(output, payload).get
        case "read" :: input :: Nil => QRCodeUtil.read(new File(input)).get
        case _ => throw new IllegalArgumentException("Usage \n\tqr-deco.jar create <output file> <payload>\n\t qr-deco.jar read <input QR>")
      }
    } match {
      case Success(x) => println(s"Process Complete with result [${x.toString}]")
      case Failure(exception) => println (s"Process Complete with result")
                                exception.printStackTrace()
    }

  }

}
