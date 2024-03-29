package the.primer.gems

import com.google.zxing.*
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

import javax.imageio.ImageIO
import java.nio.file.Paths

/**
 * To run this class as a standalone java file use the command below: <br /><br />
 * <b> From any Linux Shell: </b> `./gradlew run -PmainClass=the.primer.gems.QrGem` <br />
 * <b> From Windows Prompt: </b> `.\gradlew.bat run -PmainClass=the.primer.gems.QrGem`*/
class QrGem extends Gem {

  static void writeQR(String data, String path,
                      String charset, int width, int height) throws WriterException, IOException {

    BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
        BarcodeFormat.QR_CODE, width, height)

    MatrixToImageWriter.writeToPath(matrix,
        path.substring(path.lastIndexOf('.') + 1),
        Paths.get(path))
  }

  public static void main(String[] args) {
    println 'I shall generate a QrCode of provided string data'

    String data = 'https://vivasa.in'

    String path = 'build/tmp/myqr.png'

    String charset = 'UTF-8'

    writeQR(data, path, charset, 200, 200)
    readQR(path, charset)
    System.out.println('QR Code Generated!!! ')
  }

  static String readQR(String path, String charset = 'UTF-8') {
    try {
      // Create a hintMap with ErrorCorrectionLevel and the provided charset
      Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>()
      hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L)
      hintMap.put(EncodeHintType.CHARACTER_SET, charset)

      // Read the image file and create a BinaryBitmap
      BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))))

      // Decode the QR code using the MultiFormatReader with the provided hintMap
      Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap)

      println("Qr code read from the file is ${qrCodeResult.getText()}")
      return qrCodeResult.getText()
    } catch (Exception e) {
      throw new RuntimeException("Failed to read QR code from file $path", e)
    }

  }

}
