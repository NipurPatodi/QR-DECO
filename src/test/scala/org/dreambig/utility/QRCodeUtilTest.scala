package org.dreambig.utility


import java.io.File

import org.scalatest.FlatSpec

import scala.io.Source


class QRCodeUtilTest extends FlatSpec {

  "QR Code" should " create QR files" in {
   val file = s"dummyfile-${System.currentTimeMillis()}.png"
   val res =QRCodeUtil.create(file,"Test payload")
    assert( res.isSuccess)
    new File(file).deleteOnExit()
  }

  "QR Code" should " read QR files" in {
    val input = new File(getClass.getResource("/dummy.png").getPath)
    val res = QRCodeUtil.read(input)
    assert( res.isSuccess)
    assertResult("Test payload")(res.get)
  }


}
