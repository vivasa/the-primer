package the.primer.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import the.primer.ThePrimerConfig
import the.primer.gems.QrGem

@Singleton
class QrService {

  @Inject
  ThePrimerConfig thePrimerConfig

  @Inject
  HashService hashService

  def writeToFile(String data, String path, String charset = 'UTF-8',
                  int width = thePrimerConfig.qrFileWidth, int height = thePrimerConfig.qrFileHeight) {
    QrGem.writeQR(data, path, charset, width, height)
  }

  def readFromFile(String path, String charset = 'UTF-8') {
    QrGem.readQR(path, charset)
  }

  def String generateWifiQr(String ssid, String passKey) {
    def wifiString = "WIFI:T:WPA;S:${ssid};P:${passKey};H:true;"
    def fileName = thePrimerConfig.qrstoragepath + hashService.hash(wifiString, "MD5") + ".png"
    writeToFile(wifiString, fileName)
    return fileName
  }
}
