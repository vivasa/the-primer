package the.primer.gems

import spock.lang.Specification

class HashGemSpec extends Specification {

  void "can generate hash"() {
    given:
    def text = "Hashing is useful to ensure the authenticity of a piece of data " +
        "and that it has not been tampered with since even a small change in the message " +
        "will create an entirely different hash"
    def algorithm = "MD5"

    when:
    def hash = HashGem.hash(text, algorithm)

    then: "Some non null string is generated"
    hash != null
    !hash.equalsIgnoreCase("")

    and: "It is not the same as input"
    !hash.equalsIgnoreCase(text)
  }

  void "hash lengths tend to be constant"() {
    given:
    def algorithm = "SHA-256"
    HashGem hash1 = new HashGem("Hashing is the one-way act of converting data into the output", algorithm)
    HashGem hash2 = new HashGem("A hash function is a mathematical function that converts any digital data into an output string with a fixed number of characters", algorithm)
    expect:
    hash1.hashedValue.length() == hash2.hashedValue.length()
    hash1.algorithm == "SHA-256"
    and:
    hash1.plainData == "Hashing is the one-way act of converting data into the output"

  }

}
