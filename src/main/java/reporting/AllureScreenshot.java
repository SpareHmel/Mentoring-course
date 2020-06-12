package reporting;

import driver_manager.DriverManager;
import io.qameta.allure.Attachment;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureScreenshot {

  private final WebDriver driver = DriverManager.getDriver();
  private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

  private void highlightElement(By locator) {
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].style.border='5px solid green'", driver.findElement(locator));
  }

  private void unHighlightElement(By locator) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
  }

  private void takeScreenshot() {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
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
