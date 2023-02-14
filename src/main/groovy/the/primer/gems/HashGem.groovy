package the.primer.gems

import org.apache.commons.codec.digest.DigestUtils

class HashGem extends Gem{

  String plainData
  String algorithm
  String hashedValue

  public HashGem() {

  }

  public HashGem(String input, String algo) {
    this.plainData = input
    this.algorithm = algo
    this.hashedValue = hash(this.plainData, this.algorithm)
  }

  public static String hash(String input, String algo) {
    String hash
    if (algo.equalsIgnoreCase('MD5')) {
      hash = DigestUtils
          .md5Hex(input).toUpperCase()
    } else if (algo.equalsIgnoreCase('SHA-1')) {
      hash = DigestUtils.sha1Hex(input).toUpperCase()
    } else if (algo.equalsIgnoreCase('SHA-256')) {
      hash = DigestUtils.sha256Hex(input).toUpperCase()
    } else {
      hash = "Unknown Algorithm!!"
    }

    return hash
  }

  @Override
  boolean equals(Object obj) {
    return this.hashedValue == ((HashGem) obj).hashedValue
  }

  public static void main(String[] args) {
    String input = "Hashing is done for indexing and locating items in databases " +
        "because it is easier to find the shorter hash value than the longer string"
    System.out.println("MD5 hash of [$input] is: " + hash(input, 'MD5'))
    System.out.println("SHA-1 hash of [$input] is: " + hash(input, 'SHA-1'))
    System.out.println("SHA-256 hash of [$input] is: " + hash(input, 'SHA-256'))
  }

}
