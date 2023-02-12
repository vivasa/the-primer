package the.primer.controller

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

import java.nio.charset.StandardCharsets

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
    def input = "श्रीमाता श्रीमहाराज्ञी श्रीमत्-सिंहासनेश्वरी"
    String encodedInput = URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
    when:
    //def uri = UriBuilder.of("/gems/hash?input=$input&algorithm=$algo")

    HttpRequest request = HttpRequest.GET("/gems/hash?algorithm=${algo}&input=${encodedInput}")
    HttpResponse<Map> response = client.toBlocking().exchange(request, Argument.of(Map.class), Argument.of(JsonError))
    then:
    response.status().code == 200
    response.body()['data']['hash'] == hash
    where:
    algo      | hash
    "MD5"     | "4027194CCC56EBAAE8463107D757F756"
    "SHA-1"   | "D875C0BF9BC820C0970B4DE1FDA2E3939738DA8E"
    "SHA-256" | "185015366232932EEE3F7488F1A344D259B97B58F4A7CB2C08C3AD316312EA13"
  }

}
