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
      def qrRequest = [data: 'My Qr Input', path: 'tmp/myinp.png']
    when:
      HttpRequest request = HttpRequest.POST("/primer/api/v1.0/gems/qr",qrRequest)
      HttpResponse response = client.toBlocking().exchange(request, Argument.of(Map.class), Argument.of(JsonError))
    then:
      response.status().code == 200
      response.body()['result'] == 'SUCCESS'
    when:
      request = HttpRequest.GET("/primer/api/v1.0/gems/qr?path=${qrRequest.path}")
      response = client.toBlocking().exchange(request, Argument.of(Map.class), Argument.of(JsonError))
    then:
      response.status().code == 200
      response.body()['data'] == qrRequest.data
  }

}
