package the.primer.service

import jakarta.inject.Singleton

import the.primer.gems.HashGem

@Singleton
class HashService {

  def hash(String input, String algo){
    HashGem.hash(input, algo)
  }

}
