package the.primer.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import the.primer.ThePrimerConfig
import the.primer.gems.QrGem

@Singleton
class QrService {

  @Inject
  ThePrimerConfig thePrimerConfig
  def writeToFile(String data, String path, String charset = 'UTF-8',
                  int width = 0, int height = 0) {
    QrGem.writeQR(data, path, charset, width > 0 ?: thePrimerConfig.qrFileWidth,
        height > 0 ?: thePrimerConfig.qrFileHeight)
  }

  def readFromFile(String path, String charset) {
    QrGem.readQR(path, charset)
  }

}
