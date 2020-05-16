package jdi_pages;

import com.epam.jdi.light.elements.composite.WebPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage extends WebPage {

  protected final WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));

  protected void acceptAlertIfPresent() {
    try {
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = driver().switchTo().alert();
      alert.accept();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
