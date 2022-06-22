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
    when:
      HttpRequest request = HttpRequest.GET("/primer/api/v1.0/gems/qr?path=tmp/myqr.png")
      HttpResponse response = client.toBlocking().exchange(request, Argument.of(Map.class), Argument.of(JsonError))
    then:
      response.status().code == 200
      response.body()['data'] == 'Data to be embedded in QR'

  }

}
