package the.primer

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties('primer.config')
class ThePrimerConfig {
  String qrstoragepath = "./build/tmp/"
}
