package the.primer.gems

import org.apache.commons.codec.digest.DigestUtils

class HashGem {

  public static Map hash(String input, String algo)  {
    String hash
    if (algo.equalsIgnoreCase('MD5')) {
      hash = DigestUtils
        .md5Hex(input).toUpperCase()
    } else if (algo.equalsIgnoreCase('SHA-1')) {
      hash = DigestUtils.sha1Hex(input).toUpperCase()
    } else if (algo.equalsIgnoreCase('SHA-256')) {
      hash = DigestUtils.sha256Hex(input).toUpperCase()
    } else {
      hash = "Unknown Algo!!"
    }

    return [hash: hash, algo: algo, input: input]
  }

  public static void main(String[] args) {
    String input = 'A short String'
    System.out.println("MD5 hash of $input is " + hash(input, 'MD5'))
    System.out.println("SHA-1 hash of $input is " + hash(input, 'SHA-1'))
    System.out.println("SHA-256 hash of $input is " + hash(input, 'SHA-256'))
  }

}
