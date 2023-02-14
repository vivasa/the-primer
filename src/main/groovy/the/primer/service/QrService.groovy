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
                  int width = thePrimerConfig.qrFileWidth, int height = thePrimerConfig.qrFileHeight) {
    QrGem.writeQR(data, path, charset, width, height)
  }

  def readFromFile(String path, String charset = 'UTF-8') {
    QrGem.readQR(path, charset)
  }

}
