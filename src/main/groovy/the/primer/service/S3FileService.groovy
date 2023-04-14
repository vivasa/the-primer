package the.primer.service

import io.micronaut.http.multipart.StreamingFileUpload
import jakarta.inject.Inject
import jakarta.inject.Singleton
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.*
import the.primer.ThePrimerConfig

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

@Singleton
class S3FileService {

  private final S3Client s3Client

  @Inject
  ThePrimerConfig config

  S3FileService(S3Client s3Client) {
    this.s3Client = s3Client
  }

  List<Map> listFiles() {
    ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
        .bucket(config.s3.bucketName)
        .build()
    ListObjectsV2Response response = s3Client.listObjectsV2(listObjectsRequest)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    ZoneId zoneId = ZoneId.systemDefault()
    return response.contents().stream()
        .map(s3Object -> {
          ZonedDateTime zonedDateTime = s3Object.lastModified().atZone(zoneId)
          [key: s3Object.key(), size: s3Object.size() / 1024.0, lastModified: formatter.format(zonedDateTime)]
        })
        .collect(Collectors.toList())
  }

  void uploadFile(String key, StreamingFileUpload file) {
    file.transferTo(File.createTempFile(key, "temp")).blockingGet()
    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(config.s3.bucketName)
        .key(key)
        .build()
    s3Client.putObject(putObjectRequest, RequestBody.fromFile(new File("temp/$key")))
  }

  InputStream downloadFile(String key) {
    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
        .bucket(config.s3.bucketName)
        .key(key)
        .build()
    return s3Client.getObject(getObjectRequest)
  }

  void deleteFile(String key) {
    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
        .bucket(config.s3.bucketName)
        .key(key)
        .build()
    s3Client.deleteObject(deleteObjectRequest)
  }
}