package tests;

import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static com.epam.jdi.light.elements.init.UIFactory.$;
import static sections.MailRuStaticSite.homePage;

import entities.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sections.MailRuStaticSite;
import utils.PropertyReader;

public class JdiTestsBase {

  private static final PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  private final String login = propertyReader.readPropertyFile("login");
  private final String password = propertyReader.readPropertyFile("password");

  @BeforeSuite
  protected void setUp() {
    killAllSeleniumDrivers();
    initSite(MailRuStaticSite.class);
  }

  @BeforeMethod
  protected void signIn() {
    homePage.open();
    homePage.signIn(new User(login, password));
  }

  @AfterMethod
  protected void logOff() {
    $("#PH_logoutLink").click();
  }

  @AfterSuite
  protected void tearDown() {
    killAllSeleniumDrivers();
  }
}
