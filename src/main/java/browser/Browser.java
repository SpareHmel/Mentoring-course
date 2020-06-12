package browser;

import driver_manager.DriverManager;
import io.qameta.allure.Attachment;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reporting.MyLogger;

public class Browser {

  private WebDriver driver;
  private static Browser instance = null;
  private static final String SCREENSHOTS_NAME = "screenshots/scr";

  private Browser(WebDriver driver) {
    this.driver = driver;
  }

  public static Browser getInstance() {
    if (instance != null) {
      return instance;
    }
    return instance = init();
  }

  private static Browser init() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    WebDriver driver = DriverManager.getDriver();
    return new Browser(driver);
  }

  private void highlightElement(WebElement webElement) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", webElement);
  }

  private void unHighlightElement(WebElement webElement) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", webElement);
  }

  public void click(final WebElement webElement) {
    MyLogger.info("Clicking element '" + webElement.getText() + "' (Located: " + webElement.getLocation() + ")");
    highlightElement(webElement);
    takeScreenshot();
    unHighlightElement(webElement);
    webElement.click();
  }

  public void takeScreenshot() {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      String screenshotName = SCREENSHOTS_NAME + System.nanoTime();
      String scrPath = screenshotName + ".jpg";
      File copy = new File(scrPath);
      FileUtils.copyFile(screenshot, copy);
      captureScreenshot(this.driver);
    } catch (IOException e) {
      MyLogger.error("Failed to make screenshot");
    }
  }

  @Attachment(value = "Screenshot", type = "image/png")
  private static byte[] captureScreenshot(WebDriver driver) {
    byte[] screenshot = null;
    screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    MyLogger.info("Screenshot has been made");
    return screenshot;
  }
}
