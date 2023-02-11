package the.primer.controller


import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import the.primer.ThePrimerConfig
import the.primer.service.HashService
import the.primer.service.QrService

@Controller('/gems')
public class GemController {

  private static final Logger logger = LoggerFactory.getLogger(GemController.class)

  @Inject
  QrService qrService

  @Inject
  HashService hashService

  @Inject
  ThePrimerConfig thePrimerConfig

  @Get("/")
  @Produces(MediaType.APPLICATION_JSON)
  def ping() {
    def version = the.primer.Version?.getVersion()
    return [version: version]
  }

  @Get('/qr')
  @Produces(MediaType.APPLICATION_JSON)
  def readQR(HttpRequest request, @QueryValue String fileName) {
    logger.debug("Reading QR at $fileName")
    return ['data': qrService.readFromFile(thePrimerConfig.qrstoragepath + fileName, 'UTF-8')]
  }

  @Post('/qr')
  @Produces(MediaType.APPLICATION_JSON)
  def writeQR(@Body Map request) {
    logger.debug("Writing ${request.data} as QR to ${request.fileName}")
    qrService.writeToFile(request.data, thePrimerConfig.qrstoragepath + request.fileName, 'UTF-8', 400, 400)
    return ['result': 'SUCCESS']
  }

  @Get('/hash')
  @Produces(MediaType.APPLICATION_JSON)
  def readQR(@QueryValue String input, @QueryValue String algorithm) {
    logger.debug("Hashing $input with $algorithm")
    return ['data': hashService.hash(input, algorithm)]
  }

}
