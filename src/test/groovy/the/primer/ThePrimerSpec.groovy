package the.primer

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import jakarta.inject.Inject

import the.primer.gems.QrGem

@MicronautTest
class ThePrimerSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application

    void 'test qr read and write'() {
        given:
        def data = "Data to be embedded in QR"
        def path = "build/tmp/myqr.png"
        def charset = 'UTF-8'

        when:
        QrGem.writeQR(data, path, charset, 400, 400)
        
        then:
        QrGem.readQR(path, charset) == data
    }

}
