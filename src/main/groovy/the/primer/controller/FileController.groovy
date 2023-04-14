package the.primer.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.http.multipart.StreamingFileUpload
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.s3.model.S3Object
import the.primer.service.S3FileService

import java.io.InputStream

@Controller("/files")
@Secured(SecurityRule.IS_AUTHENTICATED)
class FileController {
  Logger logger = LoggerFactory.getLogger(FileController)

  private final S3FileService s3FileService

  FileController(S3FileService s3FileService) {
    this.s3FileService = s3FileService
  }

  @Get("/list")
  @Produces(MediaType.APPLICATION_JSON)
  List<Map> listFiles() {
    logger.info("Listing files")
    return s3FileService.listFiles()
  }

  @Post(consumes = MediaType.MULTIPART_FORM_DATA)
  HttpResponse<String> uploadFile(@Body StreamingFileUpload file) {
    String key = file.getFilename()
    s3FileService.uploadFile(key, file)
    return HttpResponse.ok("File uploaded successfully")
  }

  @Get("/{key}")
  HttpResponse<InputStream> downloadFile(String key) {
    InputStream fileStream = s3FileService.downloadFile(key)
    return HttpResponse.ok(fileStream).contentType(MediaType.APPLICATION_OCTET_STREAM)
  }

  @Delete("/{key}")
  HttpResponse<String> deleteFile(String key) {
    s3FileService.deleteFile(key)
    return HttpResponse.ok("File deleted successfully")
  }
}
