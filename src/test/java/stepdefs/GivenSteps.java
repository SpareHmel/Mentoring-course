package stepdefs;

import cucumber.api.java.en.Given;
import entities.User;
import enums.WebsiteInfo;
import hooks.TestContext;

public class GivenSteps extends BaseStep {

  private final String login = propertyReader.readPropertyFile("login");
  private final String password = propertyReader.readPropertyFile("password");

  @Given("^I open mail site '([^\"]*)'")
  public void iOpenMailSite(WebsiteInfo linkMain) {
    TestContext.getDriver().get(linkMain.getText());
  }

  @Given("^I login to the account")
  public void iLogin() {
    homePage.signIn(new User(login, password));
  }
}
