package the.primer.gems

import groovy.json.JsonBuilder
import groovy.json.JsonException
import groovy.json.JsonSlurper

class JsonGem {

  static String parseJson(String input) {
    try {
      def jsonSlurper = new JsonSlurper()
      def jsonObject = jsonSlurper.parseText(input)
      def jsonBuilder = new JsonBuilder(jsonObject)
      return prettyPrint(jsonBuilder.toString())
    } catch (JsonException e) {
      println "Invalid JSON input: ${e.message}"
      return null
    }
  }

  static String prettyPrint(String jsonString) {
    def pretty = new StringBuilder()
    def indent = 0

    for (int i = 0; i < jsonString.length(); i++) {
      def onechar = jsonString[i]
      switch (onechar) {
        case '[':
        case '{':
          pretty.append(onechar)
          pretty.append('\n')
          indent += 4
          pretty.append(' ' * indent)
          break
        case ']':
        case '}':
          pretty.append('\n')
          indent -= 4
          pretty.append(' ' * indent)
          pretty.append(onechar)
          break
        case ',':
          pretty.append(onechar)
          pretty.append('\n')
          pretty.append(' ' * indent)
          break
        case ':':
          pretty.append(onechar)
          pretty.append(' ')
          break
        default:
          pretty.append(onechar)
      }
    }

    return pretty.toString()
  }

  static String parseJson1(String input) {
    try {
      def jsonSlurper = new JsonSlurper()
      def jsonObject = jsonSlurper.parseText(input)
      def jsonBuilder = new JsonBuilder(jsonObject)
      return jsonBuilder.toPrettyString()
    } catch (JsonException e) {
      println "Invalid JSON input: ${e.message}"
      return null
    }
  }

  public static void main(String[] args) {
    String validJson = '{"name": "John", "age": 30, "city": "New York"}'
    String invalidJson = '{"name": "John", "age": 30, "city": "New York",}'
    String unicodeJson = '{"unicode": "你好", "list": [1, 2, 3], "boolean": true}'

    println "Valid JSON:\n${parseJson(validJson)}"
    println "Invalid JSON:\n${parseJson(invalidJson)}"
    println "Unicode JSON:\n${parseJson(unicodeJson)}"
  }
}
