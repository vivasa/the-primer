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
}
