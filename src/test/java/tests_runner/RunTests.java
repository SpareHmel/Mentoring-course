package tests_runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import driver_manager.DriverManager;
import hooks.TestContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
    strict = true,
    features = {"src/test/resources/features"},
    glue = {"hooks", "stepdefs"},
    tags = {"@all"}
)
public class RunTests extends AbstractTestNGCucumberTests {

    private static WebDriver driver;

    @BeforeClass
    protected void setUp() {
        driver = DriverManager.getDriver();
        TestContext.setDriver(driver);
    }

    @AfterClass
    protected void tearDown() {
        driver.quit();
    }
}
