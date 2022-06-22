package the.primer.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.hateoas.JsonError
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.validation.constraints.NotEmpty

import the.primer.service.QrService

@Controller('/primer/api/v1.0/gems')
public class GemController {

  private static final Logger logger = LoggerFactory.getLogger(GemController.class)

  @Inject
  QrService qrService

  @Get('/qr')
  @Produces(MediaType.APPLICATION_JSON)
  def readQR(HttpRequest request, @QueryValue String path) {
    return ['data': qrService.readFromFile(path, 'UTF-8')]
  }

}
