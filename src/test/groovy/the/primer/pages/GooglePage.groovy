package the.primer.pages

import geb.Page

class GooglePage extends Page{
  static url = '/'
  static at = { title.contains 'Google' }
}
