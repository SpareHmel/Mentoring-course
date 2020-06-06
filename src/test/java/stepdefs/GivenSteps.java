package stepdefs;

import cucumber.api.java.en.Given;
import entities.User;

public class GivenSteps extends BaseStep {

  private final String login = propertyReader.readPropertyFile("login");
  private final String password = propertyReader.readPropertyFile("password");

  @Given("I open {string} page")
  public void iOpenPage(String linkMain) {
    driver.get(linkMain);
  }

  @Given("I login to the account")
  public void iLoginToAccount() {
    homePage.signIn(new User(login, password));
  }
}
