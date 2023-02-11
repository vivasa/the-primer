package the.primer

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.hateoas.JsonError
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class GemControllerSpec extends Specification {

  @Shared
  @AutoCleanup
  EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

  @Shared
  @AutoCleanup
  HttpClient client = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.getURL())

  void setupSpec() {}

  void "read existing qr"() {
    given:
    def qrRequest = [data: 'My Qr Input', fileName: 'myinp.png']
    when:
    HttpRequest request = HttpRequest.POST('/gems/qr', qrRequest)
    HttpResponse response = client.toBlocking().exchange(request, Argument.of(Map), Argument.of(JsonError))
    then:
    response.status().code == 200
    response.body()['result'] == 'SUCCESS'
    when:
    request = HttpRequest.GET("/gems/qr?fileName=${qrRequest.fileName}")
    response = client.toBlocking().exchange(request, Argument.of(Map), Argument.of(JsonError))
    then:
    response.status().code == 200
    response.body()['data'] == qrRequest.data
  }

  void "test hash algorithms"() {
    given:
    def input = "TestInputString"

    when:
    //def uri = UriBuilder.of("/gems/hash?input=$input&algorithm=$algo")

    HttpRequest request = HttpRequest.GET("/gems/hash?input=$input&algorithm=$algo")
    HttpResponse<Map> response = client.toBlocking().exchange(request, Argument.of(Map.class), Argument.of(JsonError))
    then:
    response.status().code == 200
    response.body()['data']['hash'] == hash
    where:
    algo      | hash
    "MD5"     | "EB30F6BDEE70979FA6060A41EA73117D"
    "SHA-1"   | "A29F2D7698613CB6CE4058B5DA2B7E2ACFF05C49"
    "SHA-256" | "2E62D8E7229854FD8CD0B26D25F36F1C0963FF6B271CA40DF8E8D035FBED79B0"
  }

}
