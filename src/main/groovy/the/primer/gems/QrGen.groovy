package the.primer.gems

import java.io.File
import java.io.IOException
import java.util.HashMap
import java.util.Map
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.NotFoundException
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

class QrGen {

  static void createQR(String data, String path,
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

    createQR(data, path, charset, 200, 200)
    System.out.println('QR Code Generated!!! ')
  }

}
