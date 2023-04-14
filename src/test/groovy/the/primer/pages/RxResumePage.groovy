package the.primer.pages

import geb.Page

class RxResumePage extends Page {
  static url = 'https://rxresu.me/'
  static at = { title.contains 'Reactive Resume' }

  static content = {
    loginButton(wait: true) { $("button", text: "Login") }
  }
}
