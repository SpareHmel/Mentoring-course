package tests;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.PropertyReader;

public class JdiTestsBase {

  private static final PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  private final String login = propertyReader.readPropertyFile("login");
  private final String password = propertyReader.readPropertyFile("password");

  @BeforeSuite
  protected void setUp() {
//    killAllSeleniumDrivers();
//    initSite(MailRuStaticSite.class);
//    homePage.open();
//    homePage.signIn(new User(login, password));
  }

  @AfterSuite
  protected void tearDown() {
    killAllSeleniumDrivers();
  }
}
