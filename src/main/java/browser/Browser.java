package browser;

import driver_manager.DriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.MyLogger;

public class Browser {

  private WebDriver driver;
  private static WebDriverWait wait;
  private static JavascriptExecutor js;
  private static Browser instance = null;

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
    WebDriver driver = DriverManager.getDriver();
    wait = new WebDriverWait(driver, 10);
    js = (JavascriptExecutor) driver;
    return new Browser(driver);
  }

  public void highlightElement(WebElement webElement) {
    js.executeScript("arguments[0].style.border='5px solid green'", webElement);
  }

  private void unHighlightElement(WebElement webElement) {
    js.executeScript("arguments[0].style.border='0px'", webElement);
  }

  public void click(final WebElement webElement) {
    MyLogger.info("Clicking element (Located: " + webElement.getLocation() + ")");
    highlightElement(webElement);
    unHighlightElement(webElement);
    webElement.click();
  }

  public void sendKeys(WebElement webElement, String keys) {
    MyLogger.info("Sending keys to element (Located: " + webElement.getLocation() + ")");
    highlightElement(webElement);
    unHighlightElement(webElement);
    webElement.sendKeys(keys);
  }

  public void alertHandling(WebElement webElement) {
    try {
      waitForPresence(webElement);
    } catch (UnhandledAlertException f) {
      try {
        Alert alert = driver.switchTo().alert();
        alert.accept();
      } catch (NoAlertPresentException ignore) {
      }
    }
  }

  public void acceptAlertIfPresent() {
    try {
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (TimeoutException ignore) {
      MyLogger.warn("No alerts were detected");
    }
  }

  public WebElement waitForPresence(WebElement webElement) {
    return wait.until(ExpectedConditions.visibilityOf(webElement));
  }

  public void waitPageForLoad() {
    wait.until((ExpectedCondition<Boolean>) driver ->
            js.executeScript("return document.readyState").equals("complete"));
  }

  public void scrollToElement(WebElement webElement) {
    MyLogger.info("Scrolling to the element located: " + webElement.getLocation());
    js.executeScript("arguments[0].scrollIntoView();", webElement);
  }

  public void jsClick(WebElement webElement) {
    MyLogger.info("Performing js click to the element" + webElement);
    js.executeScript("arguments[0].click();", webElement);
  }

  public void takeScreenshot() {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      String screenshotName = "screenshots/scr" + System.nanoTime();
      String scrPath = screenshotName + ".jpg";
      File copy = new File(scrPath);
      FileUtils.copyFile(screenshot, copy);
      MyLogger.attach(scrPath, "Screenshot");
    } catch (IOException e) {
      MyLogger.error("Failed to make screenshot");
    }
  }
}
