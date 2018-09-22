package org.dreambig.utility

import java.io.{File, FileInputStream}
import java.nio.charset.StandardCharsets

import scala.util.Try
import scala.collection.JavaConverters._
import com.google.zxing._
import com.google.zxing.client.j2se.{BufferedImageLuminanceSource, MatrixToImageWriter}
import com.google.zxing.common.{BitMatrix, HybridBinarizer}
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import javax.imageio.ImageIO

/** *
  * Utility Class to create QR Code
  */
object QRCodeUtil {

  /** *
    * Method to create and encode to QR Code
    * @param outputFile: Name of file you need to create
    * @param payLoad: Data to encode in QR
    * @return :Success(true) if created Successfully
    */
  def create(outputFile: String, payLoad: String): Try[Boolean] = Try {
    val hintMap = mapAsJavaMap(Map(EncodeHintType.ERROR_CORRECTION -> ErrorCorrectionLevel.L))
    val matrix = new MultiFormatWriter().encode(new String(payLoad.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
      BarcodeFormat.QR_CODE, 200, 200, hintMap)
    MatrixToImageWriter.writeToFile(matrix, "png", new File(outputFile))
    println(s"QR Created at $outputFile successfully!!!")
    true
  }

  /***
    * Method to read and decode QR
    * @param file 
    * @return
    */
  def read(file: File): Try[String] = Try {
    val hintMap = mapAsJavaMap(Map(DecodeHintType.PURE_BARCODE -> ErrorCorrectionLevel.L))
    val bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(file)))))
    new MultiFormatReader().decode(bitmap, hintMap).getText

  }
}
