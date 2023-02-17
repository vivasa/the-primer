package the.primer.service


import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification
import the.primer.ThePrimerConfig

@MicronautTest
class QrServiceSpec extends Specification {

  @Inject
  QrService qrService

  @Inject
  ThePrimerConfig thePrimerConfig

  /**
   * Command to execute this test <br />
   * `./gradlew test --tests "the.primer.service.QrServiceSpec.service spec"`
   */
  void "service spec"() {
    given:
    def data = this.class.name
    def fileName = thePrimerConfig.qrstoragepath + this.class.name + ".png"

    when:
    qrService.writeToFile(data, fileName)
    and:
    def readData = qrService.readFromFile(fileName)

    then:
    readData == data
  }

  /**
   * Configuring a Wi-Fi network involves recalling the Wi-Fi credentials – “network name” (SSID) and “password” (PSK) – and entering this information on every new connected product that a user wants to add to their network. <br />
   * <li /> Wi-Fi Easy Connect makes setting up a Wi-Fi network and adding devices to it even easier than WPS
   * <li /> To set up a Wi-Fi network or add devices, you can simply scan the device-specific QR code.
   * <li /> To generate the QR Code, the text should use this pattern: `WIFI:T:WPA;S:<SSID>;P:<PASSWORD>;H:true;`
   *
   */
  void "share wifi credentials through QR"() {
    given:
    def ssid = "My Wifi SSID"
    def password = "secret123!"

    when:
    def wifiQrFile = qrService.generateWifiQr(ssid, password)
    then:
    //How do you verify if the file is generated?
    //How do you verity that wifiQrFile is usable?
    //How do you verity that wifiQrFile indeed allows users to connect to their wifi ?
    true

  }
}
