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

  def String generateSMSQr(long phone, String message) {
    def smsString = "SMSTO:${phone}:${message}"
    String fileName = thePrimerConfig.qrstoragepath + hashService.hash(smsString, "MD5") + ".png"
    writeToFile(smsString, fileName)
    return fileName
  }

  def String generateEmailQr(String mailTo, String subject){
    //mailto:support@qrexplore.com?subject=Blog Post
  }

  def String generateVCardQr(String firstName, String lastName, long phoneNumber, String email){
    //MECARD:N:Gumo,Forrest;TEL:11115551212;EMAIL:forrestgump@example.com;;
  }
}
