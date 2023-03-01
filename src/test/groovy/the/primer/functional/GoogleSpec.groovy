package the.primer.functional

import geb.spock.GebSpec
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import the.primer.pages.GooglePage

@MicronautTest
class GoogleSpec extends GebSpec {

  void "test if page launches"(){
    given:
    browser.baseUrl = "https://google.com"
    when:
    to GooglePage

    then:
    at GooglePage
  }

}
