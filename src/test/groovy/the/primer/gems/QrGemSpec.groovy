package the.primer.gems

import spock.lang.Specification
import spock.lang.Unroll

/**
 * To run this test class use the command below: <br /><br />
 * [./gradlew test --tests the.primer.gems.QrGemSpec]
 */
class QrGemSpec extends Specification {
  void "Generate Qr for a string and read the Qr from image"() {
    given: "I chose a sample string and a file name to create qr"
    def text = "This QR was created at ${new java.util.Date()}"
    def path = "./build/tmp/sampleFromTestCase.png"

    when: "I generate QR for the string"
    QrGem.writeQR(text, path, 'UTF-8', 100, 100)
    and: "read the qr file again"
    def textReadFromQR = QrGem.readQR(path)

    //What needs to be proven (प्रतिज्ञ)
    then: "The returned text shall match the input"
    textReadFromQR == text
  }

  @Unroll
  void "generate and read QR code with input data #text and save it as #path"() {
    given: "I chose a sample string to create qr and a file name"
    // Input text and path to save the QR code image are provided by the data table

    when: "I generate QR for the string"
    // Call the writeQR method to generate the QR code image
    QrGem.writeQR(text, path, charset, width, height)

    and: "read the qr file again"
    // Call the readQR method to read the content of the QR code image
    def textReadFromQR = QrGem.readQR(path, charset)

    then: "The returned text should match the input"
    // Verify that the content read from the QR code image is equal to the input text
    textReadFromQR == text

    where:
    // Data table providing various test scenarios
    text                                     | path                                  | charset | width | height
    "This QR was created at ${new Date()}"   | './build/tmp/sampleFromTestCase1.png' | 'UTF-8' | 100   | 100
    'https://example.com'                    | './build/tmp/sampleFromTestCase2.png' | 'UTF-8' | 200   | 200
    'Hello, World!'                          | './build/tmp/sampleFromTestCase3.png' | 'UTF-8' | 150   | 150
    'QR code with special characters: !@#$%' | './build/tmp/sampleFromTestCase4.png' | 'UTF-8' | 100   | 100
    //'QR code with Unicode: मेधा'              | './build/tmp/sampleFromTestCase5.png' | 'UTF-8' | 200   | 200
  }
}
