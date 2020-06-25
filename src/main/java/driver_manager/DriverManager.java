package driver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import utils.PropertyReader;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            setDriver();
        }
        PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader
            .readPropertyFile("implicitlyWaitDefault")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private static void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.setCapability("platform", Platform.WINDOWS);
        options.setCapability("javascriptEnabled", true);
        driver = new ChromeDriver(options);
    }
}
