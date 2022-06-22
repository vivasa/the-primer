package the.primer.gems

import java.io.File
import java.io.IOException
import java.util.HashMap
import java.util.Map
import javax.imageio.ImageIO

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.NotFoundException
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

import com.google.zxing.BinaryBitmap
import com.google.zxing.ChecksumException
import com.google.zxing.FormatException
import com.google.zxing.LuminanceSource
import com.google.zxing.NotFoundException
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.Reader
import com.google.zxing.Result
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import com.google.zxing.MultiFormatReader
import com.google.zxing.client.j2se.BufferedImageLuminanceSource

class QrGem {

  static void writeQR(String data, String path,
                                String charset,
                                int width, int height)
        throws WriterException, IOException {

    BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, width, height)

    MatrixToImageWriter.writeToFile(
            matrix,
            path.substring(path.lastIndexOf('.') + 1),
            new File(path))
        }

  public static void main(String[] args) {
    println 'I shall generate a QrCode of provided string data'

    // The data that the QR code will contain
    String data = 'https://vivasa.in'

    // The path where the image will get saved
    String path = 'myqr.png'

    // Encoding charset
    String charset = 'UTF-8'

    //writeQR(data, path, charset, 200, 200)
    readQR(path, charset)
    System.out.println('QR Code Generated!!! ')
  }

  static String readQR(String path, String charset) {
    Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>()
    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L)

    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
        new BufferedImageLuminanceSource(
            ImageIO.read(new FileInputStream(path)))))
    Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap)
    println('QRCode read is ' + qrCodeResult.getText())
    return qrCodeResult.getText()
  }

}
