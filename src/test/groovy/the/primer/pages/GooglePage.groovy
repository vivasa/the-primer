package the.primer.pages

import geb.Page

class GooglePage extends Page{
  static url = 'https://google.com'
  static at = { title.contains 'Google' }

  static content = {
    searchField { $("input[name='q']") }
    searchButton(wait: true) { $("input[value='Google Search']") }
  }
}
