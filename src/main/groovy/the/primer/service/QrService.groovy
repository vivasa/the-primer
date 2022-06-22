package the.primer.service

import jakarta.inject.Singleton

import the.primer.gems.QrGem

@Singleton
class QrService {

  def writeToFile(String data, String path, String charset,
  int width, int height) {
    QrGem.writeQR(data, path, charset, width, height)
  }

  def readFromFile(String path, String charset) {
    QrGem.readQR(path, charset)
  }

}
