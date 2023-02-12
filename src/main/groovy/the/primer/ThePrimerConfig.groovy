package the.primer

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties('primer.config')
class ThePrimerConfig {
  String qrstoragepath = "./build/tmp/"
  int qrFileWidth = 400
  int qrFileHeight = 400
}
