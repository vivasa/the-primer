package the.primer.config

import io.micronaut.context.annotation.Factory
import jakarta.inject.Inject
import jakarta.inject.Singleton
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import the.primer.ThePrimerConfig

@Factory
class ThePrimerFactory {

  @Inject
  ThePrimerConfig config

  @Singleton
  S3Client s3Client() {
    S3Client.builder()
        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(config.s3.access_key, config.s3.secret_key)))
        .region(Region.of(config.s3.region))
        .build()
  }
}
