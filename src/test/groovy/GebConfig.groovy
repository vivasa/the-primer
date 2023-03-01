import org.openqa.selenium.chrome.ChromeOptions


driver = {
  ChromeOptions options = new ChromeOptions()
  options.addArguments("start-maximized")

  def driverPath = "/Users/harikishore/sources/vivasa/2022/chromedriver" // Update this with the path to your chromedriver executable
  System.setProperty("webdriver.chrome.driver", driverPath)

  new org.openqa.selenium.chrome.ChromeDriver(options)
}

//baseUrl = "https://www.google.com"
//reportsDir = new File("build/geb-reports")


environments {
  chrome {
    driver = {
      ChromeOptions options = new ChromeOptions()
      options.addArguments("start-maximized")

      def driverPath = "/path/to/chromedriver" // Update this with the path to your chromedriver executable
      System.setProperty("webdriver.chrome.driver", driverPath)

      new org.openqa.selenium.chrome.ChromeDriver(options)
    }
  }
}
