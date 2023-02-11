package the.primer.gems

import spock.lang.Specification

class QrGemSpec extends Specification {
  void "basic qr generation"() {
    given: "I chose a sample string to create qr and a file name"
    def text = "This QR was created at ${new java.util.Date()}"
    def path = "./build/tmp/sqmpleFromTestCase.png"
    when: "I generate QR for the string"
    QrGem.writeQR(text, path, 'UTF-8', 100, 100)
    and: "read the qr file again"
    def textReadFromQR = QrGem.readQR(path)
    then: "The returned text should match the input"
    textReadFromQR == text
  }
}
