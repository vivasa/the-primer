package the.primer.gems

import org.apache.commons.codec.digest.DigestUtils

class HashGem {

  public static String md5Hash(String input)  {
    String md5Hex = DigestUtils
      .md5Hex(input).toUpperCase()
    return md5Hex
  }

  public static void main(String[] args) {
    String input = "A short String"
    System.out.println("MD5 hash of $input is " + md5Hash(input))
  }

}
