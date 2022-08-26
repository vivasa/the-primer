package the.primer

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.uri.UriBuilder
import io.micronaut.http.client.HttpClient
import io.micronaut.http.hateoas.JsonError
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class GemControllerSpec extends Specification {

  @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

  @Shared
  @AutoCleanup
  HttpClient client = embeddedServer.applicationContext.createBean(HttpClient, embeddedServer.getURL())

  void setupSpec() {
  }

  void "read existing qr"() {
    given:
      def qrRequest = [data: 'My Qr Input', path: 'build/tmp/myinp.png']
    when:
      HttpRequest request = HttpRequest.POST('/gems/qr',qrRequest)
      HttpResponse response = client.toBlocking().exchange(request, Argument.of(Map), Argument.of(JsonError))
    then:
      response.status().code == 200
      response.body()['result'] == 'SUCCESS'
    when:
      request = HttpRequest.GET("/gems/qr?path=${qrRequest.path}")
      response = client.toBlocking().exchange(request, Argument.of(Map), Argument.of(JsonError))
    then:
      response.status().code == 200
      response.body()['data'] == qrRequest.data
  }

  void "test hash algorithms" (){
    given:
      def input = "TestInputString"
      
    when:
      def uri = UriBuilder.of("/gems/hash?input=$input&algo=$algo")

      HttpRequest request = HttpRequest.GET("/gems/hash")
      HttpResponse<Map> response = client.toBlocking().exchange(request,Argument.of(Map.class), Argument.of(JsonError))
    then:
      response.status().code == 200
      response.body()['data']['hash'] == hash
    where:
      algo | hash
      "MD5" | "C2C82C3E1AC15F1450D869843E98F846"
      "SHA-1" | "636AB5DCE84418085116FFA3329576C42A885C1F"
      "SHA-256" | "2FCE253679356D03F10E9FE87F5042853028B885D790E7E6C23B11B904E063F0"
  }

}
