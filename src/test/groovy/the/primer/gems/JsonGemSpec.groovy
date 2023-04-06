package the.primer.gems

import spock.lang.Specification
import spock.lang.Unroll

class JsonGemSpec extends Specification {

  @Unroll
  void "parse JSON string #input"() {
    when: "I parse a JSON string"
    def result = JsonGem.parseJson(input)

    then: "The result should match the expected outcome"
    result == expected

    where:
    input                                                             | expected
    '{"name": "John", "age": 30, "city": "New York"}'                | '{\n    "name": "John",\n    "age": 30,\n    "city": "New York"\n}'
    '{"invalid": "json", "missing": "closing brace"'                 | null
    //'{"unicode": "你好", "list": [1, 2, 3], "boolean": true}'          | '{\n    "unicode": "你好",\n    "list": [\n        1,\n        2,\n        3\n    ],\n    "boolean": true\n}'
    '{"nested": {"object": {"with": "multiple", "levels": "deep"}}}' | '{\n    "nested": {\n        "object": {\n            "with": "multiple",\n            "levels": "deep"\n        }\n    }\n}'
  }
}
