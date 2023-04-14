package the.primer

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties('primer.config')
class ThePrimerConfig {
  String qrstoragepath = "./build/tmp/"
  int qrFileWidth = 400
  int qrFileHeight = 400
  S3 s3 = new S3()

  @ConfigurationProperties('s3')
  static class S3 {
    String bucketName = "the-primer"
    String access_key
    String secret_key
    String region
  }
}
