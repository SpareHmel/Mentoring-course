package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            setDriver();
        }
        return driver;
    }

    private static void setDriver() {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        capabilities.setCapability("platform", Platform.WINDOWS);
        capabilities.setCapability("browserName", BrowserType.CHROME);
        capabilities.setCapability("javascriptEnabled", true);
//    try {
//      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//    } catch (MalformedURLException e) {
//      e.printStackTrace();
//    }
        driver = new ChromeDriver(capabilities);
    }
}
